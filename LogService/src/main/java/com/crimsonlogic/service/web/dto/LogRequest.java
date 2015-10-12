package com.crimsonlogic.service.web.dto;

public class LogRequest {

	private String appId;
	private String sourceIp;
	private String moduleName;
	private String functionName;
	private String transactionRefId;
	private String subTransactionRefId;
	private String logLevel;
	private String logDateTime;
	private String logBy;
	private String logMessage;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
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
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getLogDateTime() {
		return logDateTime;
	}
	public void setLogDateTime(String logDateTime) {
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
	@Override
	public String toString() {
		return "LogRequest [appId=" + appId + ", sourceIp=" + sourceIp
				+ ", moduleName=" + moduleName + ", functionName="
				+ functionName + ", transactionRefId=" + transactionRefId
				+ ", subTransactionRefId=" + subTransactionRefId
				+ ", logLevel=" + logLevel + ", logDateTime=" + logDateTime
				+ ", logBy=" + logBy + ", logMessage=" + logMessage + "]";
	}
	
	
}
