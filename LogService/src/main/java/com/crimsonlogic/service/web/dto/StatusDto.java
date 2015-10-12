package com.crimsonlogic.service.web.dto;

import java.util.ArrayList;
import java.util.List;

public class StatusDto extends BaseDto{

	private int code; //http status code
	private String status; //success (all other), fail(500-599), error(400-499)
	private List<ErrorInfoDto> errors; //error info
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ErrorInfoDto> getErrors() {
		if(errors == null)
			errors = new ArrayList<ErrorInfoDto>();
		return errors;
	}
	
}
