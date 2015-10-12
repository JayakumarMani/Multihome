package com.cl.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
import com.cl.services.out.LogOutputJson;

@RestController

public class JsonController {
	
	@Autowired
	private PersistLog persistLog;
	

	public PersistLog getPersistLog() {
		return persistLog;
	}


	public void setPersistLog(PersistLog persistLog) {
		this.persistLog = persistLog;
	}

	final static Logger logger = Logger.getLogger(JsonController.class);
	
	
	@RequestMapping(value="/logging/json", method={RequestMethod.POST}, headers="content-type=application/json")
    public LogOutputJson logEntryJson(@RequestBody JsonInput jInput) throws Exception {

		logger.info("Printing the logmessage " + jInput.getAppId());    	
    	logger.info("Printing the logmessage " + jInput.getSourceIp());
    	logger.info("Printing the logmessage " + jInput.getModuleName());
    	logger.info("Printing the logmessage " + jInput.getFunctionName());
    	logger.info("Printing the logmessage " + jInput.getTransactionRefId());
    	logger.info("Printing the logmessage " + jInput.getSubTransactionRefId());
    	logger.info("Printing the logmessage " + jInput.getLogLevel());
    	logger.info("Printing the logmessage " + jInput.getLogDateTime());
    	logger.info("Printing the logmessage " + jInput.getLogBy());
    	logger.info("Printing the logmessage " + jInput.getLogMessage());
    	logger.info ("Printing the datetime " + jInput.getLogDateTime().toGregorianCalendar().getTime());
    	
    	
    	try {
    		boolean retValue = persistLog.persistJsonLog(jInput);
    		if(!retValue) {
    			logger.error("Problem while persisting the log");
    			throw new Exception ("General error");
    		}
    	} catch (Exception e) {
    		throw e;
    	}
    	
    	LogOutputJson lo = new LogOutputJson();
    	lo.setStatus("00");
    	lo.setDescription("Log Persisted Successfully");
    	
        return lo;

	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorDetailsJson handleException(Exception e) {
	    ErrorDetailsJson ed = new ErrorDetailsJson();
	    ed.setErrorId("0201");
	    ed.setErrorDescription("General error");
	    return ed;
	}	
	
}