package com.crimsonlogic.service.web.dto;

public class LinkDto {
	private String rel; //first, last, prev, next
	private String href;//url
	
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
}
