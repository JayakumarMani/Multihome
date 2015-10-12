package com.tradenet.dashboard.model;

import java.util.Date;

public class LoggingDTO {
	
	private String logId;
	private Long appId;
	private String moduleName;
	private String functionName;
	private Integer logLevel;
	private String transactionRefId;
	private String subTransactionId;
	private String logMessage;
	private String logBy;
	private Date logDateTime;
	private String sourceIp;
	private String logEntryLevel;
	private Date createdDateTime;
	private String appDesc;
	private String logLevelDesc;
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
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
	public Integer getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}
	public String getTransactionRefId() {
		return transactionRefId;
	}
	public void setTransactionRefId(String transactionRefId) {
		this.transactionRefId = transactionRefId;
	}
	public String getSubTransactionId() {
		return subTransactionId;
	}
	public void setSubTransactionId(String subTransactionId) {
		this.subTransactionId = subTransactionId;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public String getLogBy() {
		return logBy;
	}
	public void setLogBy(String logBy) {
		this.logBy = logBy;
	}
	public Date getLogDateTime() {
		return logDateTime;
	}
	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getLogEntryLevel() {
		return logEntryLevel;
	}
	public void setLogEntryLevel(String logEntryLevel) {
		this.logEntryLevel = logEntryLevel;
	}
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public String getLogLevelDesc() {
		return logLevelDesc;
	}
	public void setLogLevelDesc(String logLevelDesc) {
		this.logLevelDesc = logLevelDesc;
	}

}
