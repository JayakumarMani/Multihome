package com.tradenet.dashboard;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tradenet.dashboard.model.LoggingDTO;
import com.tradenet.dashboard.model.PartyDTO;
import com.tradenet.dashboard.model.PermitDTO;
import com.tradenet.dashboard.model.TaskExtDTO;
import com.tradenet.dashboard.model.master.LoginDTO;


public interface DashboardDAO {
	
	/*public JsonNode getById(Long Id);
	
	public List<JsonNode> getAll();
	
	public List<TestDTO> getAllRotationNumber();
	
	public List<JobHeaderDTO> getRecentJobHeaders();
	
	public String getJobHeadersForPie();*/
	
	public String getRiskAllStats();
	
	public String getRiskList();
	
	public String getRiskPermitStats(PermitDTO permitDTO);
	
	public String getRiskPermitDetails(PermitDTO permitDTO);
	
	public String getRiskPermitIEDetails(PermitDTO permitDTO);
	
	public String getPermitItemsList(PermitDTO permitDTO);
	
	public String getPermitAllPartyList(PermitDTO permitDTO);
	
	public String getPermitContainersList(PermitDTO permitDTO);
	
	public String getPermitInvoicesList(PermitDTO permitDTO);
	
	public String getPermitSupDocList(PermitDTO permitDTO);
	
	public String getPermitList();
	
	public String getPermitStatsList();
	
	public String getTopHScode();
	
	public String getTopTraderRed();
	
	public String getTopTraderRedPermit(PartyDTO partyDTO);
	
	public String getPermitAuditDetails(PermitDTO permitDTO);
	
	public void updateCAApprovalCondition(TaskExtDTO taskExtDTO);
	
	public void insertCAApprovalDetails(TaskExtDTO taskExtDTO);
	
	public LoggingDTO createLog(LoggingDTO loggingDTO);
	
	public String getLoggingList(LoggingDTO loggingDTO);
	
	public void purgeLogging(LoggingDTO loggingDTO);
	
	public String getLogStatsList();
	
	public String getLogDateTimeList();
	
	public String getAppCountList();
	
	public String getModuleCountList();
	
	public String getFunctionCountList();
	
	public String getAllFunctionDetails();
	
	public String getAllApplicationDetails();
	
	public String getAllLogLevelDetails();
	
	public String getAllModuleDetails();
	
	public ResponseEntity<byte[]> getPdf(LoggingDTO loggingDTO);
	
	public List<LoggingDTO> getLoggingListForPDF(LoggingDTO loggingDTO);
	
	public Boolean login(LoginDTO loginDTO);
	
	public String getTransactionBased();
}
