package com.video.persistence;

import java.util.ArrayList;
import java.util.List;

import com.video.domain.Video;

public class VideoRepository {
	
	private static List<Video> videos=new ArrayList<>();
	
	public VideoRepository(){} // constructor buit
	
	public List<Video> getAllVideos(){
		return new ArrayList<>(videos);
	}
	
	
	public void addVideo(Video video) throws Exception{
		if(video==null) throw new Exception();
		videos.add(video);
	}

}
