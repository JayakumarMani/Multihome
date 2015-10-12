package com.crimsonlogic.service.web.delegate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.crimsonlogic.service.RougeService;
import com.crimsonlogic.service.web.dto.LogRequest;
import com.crimsonlogic.service.web.dto.RougeRequest;
import com.crimsonlogic.service.web.dto.ResponseDto;
import com.crimsonlogic.service.web.dto.helper.ResponseHelper;


@Component
public class RougeServiceDelegate extends BaseDelegate {
	
	@Autowired
	private RougeService rougeService;
	

	public ResponseEntity<ResponseDto> logMessageRouge(	RougeRequest rougeRequest) {
		try {
			rougeService.logMessage(rougeRequest);
			ResponseDto responseDto = ResponseHelper.getSuccessResponse();		
			return new ResponseEntity<ResponseDto> (responseDto, HttpStatus.OK);
		} catch (Exception e) {
			ResponseDto responseDto = ResponseHelper.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<ResponseDto> (responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public ResponseEntity<ResponseDto> logMessage(LogRequest logRequest) {
		ResponseDto responseDto = ResponseHelper.getSuccessResponse();		
		return new ResponseEntity<ResponseDto> (responseDto, HttpStatus.OK);
	}
}
