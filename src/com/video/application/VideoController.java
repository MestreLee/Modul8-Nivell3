package com.video.application;

import java.util.List;

import com.video.domain.*;
import com.video.persistence.VideoRepository;

public class VideoController {
	
	private VideoRepository repository = new VideoRepository();
	
	public VideoController(){}
		
		public void createVideo(String url, String titol, String tag) throws Exception{		
			Video video = new Video(url, titol, tag);
			repository.addVideo(video);
		}
		public void createVideo(String url, String titol) throws Exception{		
			Video video = new Video(url, titol);
			repository.addVideo(video);
		}
		
		public void afegirTags(String nomVideo, String tag) {
			List<Video> lista = repository.getAllVideos();
			for(Video e: lista) {
				if (nomVideo.toLowerCase().equals(e.getNomVideo().toLowerCase())){
					try {
						e.addOneTag(tag);
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				}
			}	
		}
		
		
		public String getAllVideos() {
			
			String res        = "";
			List<Video> lista = repository.getAllVideos();
			
			for(Video e: lista) {
				res = res + e + "\n" + "\n";
			}
			
			return res;
		}
		
		public boolean trobaVideo(String nomvideo) {
			
			String missatge   = "";
			boolean trobat    = false;
			List<Video> lista = repository.getAllVideos();
			
			for(Video e: lista) {
				if (nomvideo.toLowerCase().equals(e.getNomVideo().toLowerCase())){
					trobat = true;
				}
			}
			
			return trobat;
		}
		
		public void pausaVideo (String nomvideo) {
			List<Video> lista = repository.getAllVideos();
			for(Video e: lista) {
				if (nomvideo.toLowerCase().equals(e.getNomVideo().toLowerCase())){
					e.pausaVideo();
				}
			}
		}
		public void paraVideo (String nomvideo) {
			List<Video> lista = repository.getAllVideos();
			for(Video e: lista) {
				if (nomvideo.toLowerCase().equals(e.getNomVideo().toLowerCase())){
					e.paraVideo();
				}
			}
		}
		public boolean playVideo (String nomvideo) {
			boolean bool = false;
			List<Video> lista = repository.getAllVideos();
			for(Video e: lista) {
				if (nomvideo.toLowerCase().equals(e.getNomVideo().toLowerCase())){
					bool = e.playVideo();
				}
			}
			
			return bool;
		}
}

