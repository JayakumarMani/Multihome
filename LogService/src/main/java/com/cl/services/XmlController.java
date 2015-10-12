package com.cl.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cl.business.PersistLog;
import com.cl.error.ErrorDetails;
import com.cl.error.ErrorDetailsJson;
import com.cl.services.out.LogOutput;

@RestController
public class XmlController {

	@Autowired
	private PersistLog persistLog;
	

	public PersistLog getPersistLog() {
		return persistLog;
	}


	public void setPersistLog(PersistLog persistLog) {
		this.persistLog = persistLog;
	}

	final static Logger logger = Logger.getLogger(XmlController.class);
	
	@RequestMapping(value="/logging/xml", method={RequestMethod.POST}, headers="content-type=application/xml")
    public @ResponseBody LogOutput logEntry(@RequestBody LoggingService logService) throws Exception {

		logger.info("Printing the logmessage " + logService.getAppId());    	
    	logger.info("Printing the logmessage " + logService.getSourceIp());
    	logger.info("Printing the logmessage " + logService.getModuleName());
    	logger.info("Printing the logmessage " + logService.getFunctionName());
    	logger.info("Printing the logmessage " + logService.getTransactionRefId());
    	logger.info("Printing the logmessage " + logService.getSubTransactionRefId());
    	logger.info("Printing the logmessage " + logService.getLogLevel());
    	logger.info("Printing the logmessage " + logService.getLogDateTime());
    	logger.info("Printing the logmessage " + logService.getLogBy());
    	logger.info("Printing the logmessage " + logService.getLogMessage());
    	logger.info ("Printing the datetime " + logService.getLogDateTime().toGregorianCalendar().getTime());
    	
    	try {
    		boolean retValue = persistLog.persistLog(logService);
    		if(!retValue) {
    			throw new Exception ("General error");
    		}
    	} catch (Exception e) {
    		throw e;
    	}
    	
    	LogOutput lo = new LogOutput();
    	lo.setStatus("00");
    	lo.setDescription("Log Persisted Successfully");
		return lo;
    }
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorDetails handleException(Exception e) {
		e.printStackTrace();
	    ErrorDetails ed = new ErrorDetails();
	    ed.setErrorId("0101");
	    ed.setErrorDescription("General error");
	    return ed;
	}
	

}