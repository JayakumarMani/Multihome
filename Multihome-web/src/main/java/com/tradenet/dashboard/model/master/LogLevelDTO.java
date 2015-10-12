package com.tradenet.dashboard.model.master;

import java.sql.Timestamp;

public class LogLevelDTO {
	private long logLevelId;
	private String logLevelCode;
	private String logLevelDesc;
	private String status;
	private String createdBy;
	private String lastUpdatedBy;
	private Timestamp createdTime;
	private Timestamp lastUpdatedTime;
	public long getLogLevelId() {
		return logLevelId;
	}
	public void setLogLevelId(long logLevelId) {
		this.logLevelId = logLevelId;
	}
	public String getLogLevelCode() {
		return logLevelCode;
	}
	public void setLogLevelCode(String logLevelCode) {
		this.logLevelCode = logLevelCode;
	}
	public String getLogLevelDesc() {
		return logLevelDesc;
	}
	public void setLogLevelDesc(String logLevelDesc) {
		this.logLevelDesc = logLevelDesc;
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
