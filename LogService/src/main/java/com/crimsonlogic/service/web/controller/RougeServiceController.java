package com.crimsonlogic.service.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crimsonlogic.service.web.constants.Constants;
import com.crimsonlogic.service.web.constants.RequestMappingConst;
import com.crimsonlogic.service.web.delegate.RougeServiceDelegate;
import com.crimsonlogic.service.web.dto.LogRequest;
import com.crimsonlogic.service.web.dto.RougeRequest;
import com.crimsonlogic.service.web.dto.ResponseDto;

@Controller
public class RougeServiceController extends BaseController {

	@Autowired
	private RougeServiceDelegate rougeServiceDelegate;
	
	@RequestMapping(value=RequestMappingConst.LOG_MESSAGE_ROUGE,method=RequestMethod.POST, produces=Constants.JSON_FORMAT, consumes = Constants.JSON_FORMAT )
	public @ResponseBody ResponseEntity<ResponseDto> logMessageRouge(@RequestBody RougeRequest rougeRequest){
		System.out.println(rougeRequest.getMessage());
		return rougeServiceDelegate.logMessageRouge(rougeRequest);
	}	
	
}
