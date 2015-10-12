package com.cl.business;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cl.hibernate.dao.LogEntries;

import com.cl.services.JsonInput;
import com.cl.services.LoggingService;
import com.cl.services.XmlController;

@Component
public class PersistLog {

	final static Logger logger = Logger.getLogger(PersistLog.class);
	

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	
	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean persistLog (LoggingService logService) throws Exception {
		
		LogEntries dtoObject = new LogEntries();
		
		dtoObject.setAppId(logService.getAppId().intValue());
		dtoObject.setSourceIp(logService.getSourceIp());
		dtoObject.setFunctionName(logService.getFunctionName());
		dtoObject.setModuleName(logService.getModuleName());
		dtoObject.setTransactionRefId(logService.getTransactionRefId());
		dtoObject.setSubTransactionId(logService.getSubTransactionRefId());
		dtoObject.setLogLevel(logService.getLogLevel().intValue());
		dtoObject.setLogEntryType("xml");
		System.out.println ("Printing the datetime " + logService.getLogDateTime().toGregorianCalendar().getTime());
		dtoObject.setLogDatetime(logService.getLogDateTime().toGregorianCalendar().getTime());
		dtoObject.setLogBy(logService.getLogBy());
		//dtoObject.setLogCreateddatetime(new java.util.Date());
		dtoObject.setLogMessage(logService.getLogMessage());
		
		Session session = sessionFactory.getCurrentSession();
		String perGuid = (String) session.save(dtoObject);
		session.flush();
		
		logger.info("Printing the return value from sessionFactory (XML) " + perGuid);
		
		return true;
		
	}
    
	@Transactional
	public boolean persistJsonLog (JsonInput jsonInput) throws Exception {

		LogEntries dtoObject = new LogEntries();
		
		dtoObject.setAppId(jsonInput.getAppId().intValue());
		dtoObject.setSourceIp(jsonInput.getSourceIp());
		dtoObject.setFunctionName(jsonInput.getFunctionName());
		dtoObject.setModuleName(jsonInput.getModuleName());
		dtoObject.setTransactionRefId(jsonInput.getTransactionRefId());
		dtoObject.setSubTransactionId(jsonInput.getSubTransactionRefId());
		dtoObject.setLogLevel(jsonInput.getLogLevel().intValue());
		dtoObject.setLogEntryType("json");
		System.out.println ("Printing the datetime " + jsonInput.getLogDateTime().toGregorianCalendar().getTime());
		dtoObject.setLogDatetime(jsonInput.getLogDateTime().toGregorianCalendar().getTime());
		dtoObject.setLogBy(jsonInput.getLogBy());
		//dtoObject.setLogCreateddatetime(new java.util.Date());
		dtoObject.setLogMessage(jsonInput.getLogMessage());
		
		Session session = sessionFactory.getCurrentSession();
		String perGuid = (String) session.save(dtoObject);
		session.flush();
		
		logger.info("Printing the return value from sessionFactory (JSON) " + perGuid);
		
		
		return true;
	}
	
}
