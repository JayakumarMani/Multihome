package com.tradenet.dashboard.model.master;

import java.sql.Timestamp;

public class ApplicationDTO {
private long applicationId;
private String applicationCode;
private String applicationDesc;
private String status;
private String createdBy;
private String lastUpdatedBy;
private Timestamp createdTime;
private Timestamp lastUpdatedTime;
public long getApplicationId() {
	return applicationId;
}
public void setApplicationId(long applicationId) {
	this.applicationId = applicationId;
}
public String getApplicationCode() {
	return applicationCode;
}
public void setApplicationCode(String applicationCode) {
	this.applicationCode = applicationCode;
}
public String getApplicationDesc() {
	return applicationDesc;
}
public void setApplicationDesc(String applicationDesc) {
	this.applicationDesc = applicationDesc;
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
