package com.video.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.video.application.*;

public class VideoApp {
	
	static int validaTria(int min, int max, String missatge){
		
		//funció per validar tries fetes amb ints
		
		boolean valor = true;
		Scanner Obj0  = new Scanner(System.in);
		int tria      = -1;
		while (tria < min || tria > max){
			if (!valor) {
				System.out.println("Valor incorrecte!" + "\n" + missatge);
			}
			try {
				tria  = Obj0.nextInt();
				valor =  false;
				//Llegim la new-line que causa problemes amb l'scanner si no es fa:
				Obj0.nextLine();
			 }catch (InputMismatchException e) {
				 System.out.println("Format incorrecte!" + "\n" + missatge);
				 valor = true;
				 //Llegim la new-line que causa problemes amb l'scanner si no es fa:
				 Obj0.nextLine();
			 }
		}
		return tria;
	}

	public static void main(String[] args) throws Exception{

			//Inicialitzem els tres controladors - videos, usuaris i camps buits
		
			VideoController videocontroller   = new VideoController();
			UsuariController usuaricontroller = new UsuariController();
			CampsBuits campsbuits 			  = new CampsBuits();
			
			//Creem un usuari i un video de base per fer tests més fàcilment
			
			usuaricontroller.createUsuari("Joan","Josep", "123456");
			videocontroller.createVideo("videourl", "Video 1", "etiqueta1");
			
			//Preguntem a l'usuari si ja te un compte creat o en vol crear un de nou
			
			Scanner obj      = new Scanner(System.in);
			String missatge0 = "Tens un usuari (0) o en vols crear un de nou (1) ?";
			System.out.println(missatge0);
			
			//Validem l'introducció de ints mitjançant la funció validaTria, i operem en un switch segons la tria de l'usuari
			
			switch(validaTria(0,1, missatge0)){
				case 0:
					
					//Ja existeix un usuari
					
					String nom = "", pass = "";
					boolean checknom = false, checkpass = false;
					
					//Validem que els camps nom i pass no estiguin buits, siguin prou llargs i, en el cas del nom, no contingui números:
					
					while(usuaricontroller.validaUsuari(nom, pass)!= "ok"){
						checknom  = false;
						checkpass = false;
						while(!checknom) {
							System.out.println("Introdueix el teu nom:");
							nom = obj.nextLine();
							try {
								checknom = campsbuits.comprovarNom(nom);
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
						while(!checkpass) {
							System.out.println("Introdueix la teva contrassenya (mínim 6 caracters):");
							pass = obj.nextLine();
							try {
								checkpass = campsbuits.comprovarPassword(pass);
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
						System.out.println(usuaricontroller.validaUsuari(nom, pass));
					}

					break;
				case 1:
					
					//Es vol crear un usuari nou

					String nom1 = "", cognom = "", pass1 = "";
					
					boolean checkcognom = false, usuaricreat = false;
					
					while(!usuaricreat) {
						checknom    = false;
						checkpass   = false;
						checkcognom = false;
						usuaricreat = false;
						while(!checknom) {
							System.out.println("Introdueix el teu nom:");
							nom1 = obj.nextLine();
							try {
								checknom = campsbuits.comprovarNom(nom1);
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
						while(!checkcognom) {
							System.out.println("Introdueix el teu cognom:");
							cognom = obj.nextLine();
							try {
								checkcognom = campsbuits.comprovarNom(cognom);
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
						
						while(!checkpass) {
							System.out.println("Introdueix la teva contrassenya (mínim 6 caracters):");
							pass1 = obj.nextLine();
							try {
								checkpass = campsbuits.comprovarPassword(pass1);
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
						
						//Un cop hem comprovat que els camps tenen un format correcte, comprovem que l'usuari que s'està creant no 
						//existeixi ja mitjançant el métode usuariDuplicat
						
						if(usuaricontroller.usuariDuplicat(nom1, cognom)) {
							usuaricontroller.createUsuari(nom1, cognom, pass1);
							usuaricreat = true;
							System.out.println("Usuari creat amb èxit!");
						}else {
							System.out.println("Aquest usuari ja existeix!");
						}
					}				
					break;
			}
			
			//Un cop tenim l'usuari, li preguntem qué vol fer:
			
			String missatge = "Que vols fer? Crear un video nou (0), veure la llista de videos (1), reproduir un video (2) o sortir (3) ?";
			boolean seguirIterant = true;
			while (seguirIterant) {
				System.out.println(missatge);
				switch(validaTria(0,3,missatge)) {
				case 0:
					
					//Per crear un video nou li demanem un titol i una url, les quals comprovem primer:
					
					String titol = "", url = "";
					boolean checktitol = false, checkurl = false;
					while(!checktitol) {
						System.out.println("Introdueix el títol del video:");
						titol = obj.nextLine();
						try {
							checktitol = campsbuits.comprovarCampBuit(titol);
						}catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
					while(!checkurl) {
						System.out.println("Introdueix la url:");
						url = obj.nextLine();
						try {
							checkurl = campsbuits.comprovarCampBuit(url);
						}catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
					
					//Acte seguit creem el video, i afegim tants tags com l'usuari vegi adequat:
					
					videocontroller.createVideo(url,titol);
					boolean seguirAfegintTags = true, checktag = false;
					String tag = "";
					while(seguirAfegintTags) {
						checktag = false;
						while(!checktag) {
							System.out.println("Introdueix un tag pel video:");
							tag = obj.nextLine();
							try {
								checktag = campsbuits.comprovarCampBuit(tag);
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
						videocontroller.afegirTags(titol, tag);
						System.out.println("Vols introduir més tags? Si (0) o no (1)?");
						if(validaTria(0,1,"Vols introduir més tags? Si (0) o no (1)?") == 1) seguirAfegintTags = false;
					}	
					break;
				case 1:
					
					//Mostrem per pantalla els videos que tinguem creats
					
					System.out.println(videocontroller.getAllVideos());
					break;
				case 2:
					
					//Mostrem els videos creats pq l'usuari en trïi un per reproduir:
					
					System.out.println("Quin video vols veure?");
					System.out.println(videocontroller.getAllVideos());
					titol = "";
					while (!videocontroller.trobaVideo(titol)){
						checktitol = false;
						while(!checktitol) {
							System.out.println("Introdueix el títol del video:");
							titol = obj.nextLine();
							try {
								checktitol = campsbuits.comprovarCampBuit(titol);
							}catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}	
						videocontroller.trobaVideo(titol);
					}
					
					//Comprovem que el video està PUBLIC
					
					boolean dalealplay = false;
					dalealplay = videocontroller.playVideo(titol); 
					
					boolean trobat = false;
					while(!trobat && dalealplay) {
						
							System.out.println("Video en reproducció! (0) Pausa - (1) Parar");
							//System.out.println(videocontroller.getAllVideos());
							switch(obj.nextInt()) {
							case 0: //Pausa video
								boolean pausa = true;
								while(pausa) {
									System.out.println("Video en pausa! (1) Parar - (2) Play");
									int numero = obj.nextInt();
									if(numero == 1) {
										videocontroller.paraVideo(titol);
										trobat = true;
										pausa  = false;
									}else {
										videocontroller.playVideo(titol);
										pausa = false;
									}
								}
								break;
							case 1: //Para video
								videocontroller.paraVideo(titol);
								trobat = true;
								break;
							}
						}						
					
					break;
				case 3:
					
					//Sortim del programa
					
					seguirIterant = false;
					System.out.println("fins la próxima!");
					break;
				}
				
			}
							
	}

}
