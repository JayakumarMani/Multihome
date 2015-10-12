package com.tradenet.dashboard.model.master;

import java.sql.Timestamp;


public class LogEntryTypeDTO {
	private long logEntryTypeId;
	private String logEntryTypeCode;
	private String logEntryTypeDesc;
	private String status;
	private String createdBy;
	private String lastUpdatedBy;
	private Timestamp createdTime;
	private Timestamp lastUpdatedTime;
	public long getLogEntryTypeId() {
		return logEntryTypeId;
	}
	public void setLogEntryTypeId(long logEntryTypeId) {
		this.logEntryTypeId = logEntryTypeId;
	}
	public String getLogEntryTypeCode() {
		return logEntryTypeCode;
	}
	public void setLogEntryTypeCode(String logEntryTypeCode) {
		this.logEntryTypeCode = logEntryTypeCode;
	}
	public String getLogEntryTypeDesc() {
		return logEntryTypeDesc;
	}
	public void setLogEntryTypeDesc(String logEntryTypeDesc) {
		this.logEntryTypeDesc = logEntryTypeDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
}
