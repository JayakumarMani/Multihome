package com.crimsonlogic.service.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDto extends BaseDto {
	private StatusDto status;	
	private List<LinkDto> links;
	private Object data;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
		
	public List<LinkDto> getLinks() {
		if(links == null)
			links = new ArrayList<LinkDto>();
		return links;
	}
	public StatusDto getStatus() {
		return status;
	}
	public void setStatus(StatusDto status) {
		this.status = status;
	}
	
}
