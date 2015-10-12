package com.crimsonlogic.service.web.dto.helper;

import com.crimsonlogic.service.web.constants.Constants;
import com.crimsonlogic.service.web.dto.ResponseDto;
import com.crimsonlogic.service.web.dto.StatusDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {

	public static ResponseDto getSuccessResponse(){		
		StatusDto statusDto = new StatusDto();
		statusDto.setCode(HttpStatus.OK.value());
		statusDto.setStatus(Constants.SUCCESS);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(statusDto);
		return responseDto;
	}
	public static ResponseDto getErrorResponse(int statusCode){		
		StatusDto statusDto = new StatusDto();
		statusDto.setCode(statusCode);
		statusDto.setStatus(Constants.ERROR);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(statusDto);
		return responseDto;
	}
	
}
