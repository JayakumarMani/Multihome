package com.cl.services;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

public class JsonInput {

	    private Integer appId;
	    private String sourceIp;
	    private String moduleName;
	    private String functionName;
	    private String transactionRefId;
	    private String subTransactionRefId;
	    private Integer logLevel;
	    private XMLGregorianCalendar logDateTime;
	    private String logBy;
	    private String logMessage;
	    
		public Integer getAppId() {
			return appId;
		}
		public void setAppId(Integer appId) {
			this.appId = appId;
		}
		public String getSourceIp() {
			return sourceIp;
		}
		public void setSourceIp(String sourceIp) {
			this.sourceIp = sourceIp;
		}
		public String getModuleName() {
			return moduleName;
		}
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
		public String getFunctionName() {
			return functionName;
		}
		public void setFunctionName(String functionName) {
			this.functionName = functionName;
		}
		public String getTransactionRefId() {
			return transactionRefId;
		}
		public void setTransactionRefId(String transactionRefId) {
			this.transactionRefId = transactionRefId;
		}
		public String getSubTransactionRefId() {
			return subTransactionRefId;
		}
		public void setSubTransactionRefId(String subTransactionRefId) {
			this.subTransactionRefId = subTransactionRefId;
		}
		public Integer getLogLevel() {
			return logLevel;
		}
		public void setLogLevel(Integer logLevel) {
			this.logLevel = logLevel;
		}
		public XMLGregorianCalendar getLogDateTime() {
			return logDateTime;
		}
		public void setLogDateTime(XMLGregorianCalendar logDateTime) {
			this.logDateTime = logDateTime;
		}
		public String getLogBy() {
			return logBy;
		}
		public void setLogBy(String logBy) {
			this.logBy = logBy;
		}
		public String getLogMessage() {
			return logMessage;
		}
		public void setLogMessage(String logMessage) {
			this.logMessage = logMessage;
		}


}
