package com.video.domain;

public class Tag {
	private String tag;

	public Tag() {} // Constructor buit, per si de cas
	
	public Tag (String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return tag;
	}
	
	
}
