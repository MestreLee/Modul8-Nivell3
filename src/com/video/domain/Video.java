package com.video.domain;


import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Video {
	
	protected String URL;
	protected String titol;
	
	private LocalDateTime date;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	//Data i hora de creació del video formatejada sense milisegons
	
	protected LocalDateTime dataCreacio;
	
	//Llista de tags de cada video
	
	protected List<Tag> tags = new ArrayList<Tag>();
	
	//Variable tipus LocalTime amb la durada del video
	//Aquesta durada es genera de forma aleatòria cada cop que es crea un video mitjançant el métode creaDuradaVideo()
	
	LocalTime duradaVideo;
	
	//Variable tipus LocalTime amb el temps de reproducció del video
	
	LocalTime videoEnReproduccio;
	
	//Vairable tipus enum EstatVideo - Uploading, Verifying o Public
	
	EstatVideo estat = null;
	
	//Variable tipus enum ReproduccioVideo - Parat, Pausat o Reproduint
	
	ReproduccioVideo reproduccio = null;
	
	public Video() {} // Constructor buit, per si de cas
	
	public Video (String url, String titol, String tag) {
		
		this.URL         = url;
		this.titol       = titol;
		
		this.date        = LocalDateTime.now();
		String text      = date.format(formatter);
		this.dataCreacio = LocalDateTime.parse(text, formatter);
		
		this.duradaVideo = this.creaDuradaVideo();
		
		this.reproduccio = ReproduccioVideo.PARAT;
		
		try {
			this.addOneTag(tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Video (String url, String titol) {
		
		this.URL  		 = url;
		this.titol 		 = titol;
		
		this.date        = LocalDateTime.now();
		String text      = date.format(formatter);
		this.dataCreacio = LocalDateTime.parse(text, formatter);
		
		this.duradaVideo = this.creaDuradaVideo();
		
		this.reproduccio = ReproduccioVideo.PARAT;
	}
	
	public String getNomVideo() {
		
		return this.titol;
	}
	
	public void addOneTag(String tag) throws Exception {
		
		Tag etiqueta = new Tag(tag);
		this.tags.add(etiqueta);
	}

	@Override
	public String toString() {
		
		estat = comprovaEstat();
		
		return "Títol: " + titol + " URL: " + URL + "\n" + 
				"Data i hora de creació :" + dataCreacio + "\n" + 
				"Estat: " + estat + "\n" +
				"Reproduccio: " + reproduccio + "\n" +
				"Durada: " + duradaVideo + "\n" +
				"Etiquetes del video: " + tags;

	}

	public LocalDateTime getData() {
		
		return this.dataCreacio;
	}
	
	public EstatVideo comprovaEstat() {
		LocalDateTime dataActual = LocalDateTime.now();

		long diff = ChronoUnit.SECONDS.between(dataCreacio, dataActual);
		
		if(diff <= 20) {
			//System.out.println(diff);
			estat = EstatVideo.UPLOADING;
		}else if ((diff > 20) && (diff <= 30)) {
			//System.out.println(diff);
			estat = EstatVideo.VERIFYING;
		}else {
			//System.out.println(diff);
			estat = EstatVideo.PUBLIC;
		}
		
		return estat;
		
	}
	
	private LocalTime creaDuradaVideo() {
		int sec  = tornaNum(59);
		int min  = tornaNum(59);
		int hora = tornaNum(3);
		
		String segons = "";
		String minuts = "";
		String hores  = "";
		
		if (hora < 10){
        	hores = "0";            	
        }else {
        	hores = "";
        }
        
        if (min < 10){
        	minuts = "0";            	
        }else {
        	minuts = "";
        }
        if (sec < 10){
        	segons = "0";            	
        }else {
        	segons = "";
        }
        segons = segons + sec;
        minuts = minuts + min;
        hores  = hores + hora;
        
        LocalTime localTime = LocalTime.parse((hores+":"+minuts+":"+segons));
        
        return localTime;
	}
	
	private int tornaNum(int max) {
		
		double aleatori = Math.random() * max;
		
		int torna = (int) aleatori;
		
		return torna;
	}
	
	public void pausaVideo() {
		this.reproduccio = ReproduccioVideo.PAUSAT;
	}
	public void paraVideo() {
		this.reproduccio = ReproduccioVideo.PARAT;
	}
	
	public boolean playVideo() {
		boolean reproduir = true;
		
		//Comprovem si el video encara s'està pujant o verificant:
		
		if(this.comprovaEstat()==EstatVideo.UPLOADING) {
			System.out.println("El video encara s'està pujant i no es pot reproduir. Espera uns instants, si us plau.");
			reproduir = false;
		}else if (this.comprovaEstat()==EstatVideo.VERIFYING) {
			System.out.println("El video encara s'està verificant i no es pot reproduir. Espera uns instants, si us plau.");
			reproduir = false;
		}else {
			this.reproduccio = ReproduccioVideo.REPRODUINT;
			int seg 	  = 0;
			String segons = "";
		    int min 	  = 0;
		    String minuts = "";
		    int hora 	  = 0;
		    String hores  = "";
		    boolean bool  = true;
		    while(bool) {
		    	try {
		    		Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		        seg++;
		        
		        if (seg == 60) {
		        	seg = 0;
		            min++;	
		        }
		        if (min == 60) {
		            min = 0;
		            hora++;	
		        }
		            
		        if (hora < 10){
		            hores = "0";            	
		        }else {
		            hores = "";
		        }
		            
		        if (min < 10){
		            minuts = "0";            	
		        }else {
		            minuts = "";
		        }
		        if (seg < 10){
		            segons = "0";            	
		        }else {
		            segons = "";
		        }
		        
		        segons = segons + seg;
		        minuts = minuts + min;
		        hores  = hores + hora;
		            
		        this.videoEnReproduccio = LocalTime.parse((hores + ":" + minuts + ":" + segons));
		        System.out.println(hores + ":" + minuts + ":" + segons);
		        long diff 				= ChronoUnit.SECONDS.between(this.videoEnReproduccio, this.duradaVideo);
		       
		        if (diff == 0) {
		        	this.reproduccio = ReproduccioVideo.PARAT;
		            bool 			 = false;
		        }
		    }
		}
		return reproduir;
	}

}
