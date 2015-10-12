package com.tradenet.dashboard;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.tradenet.dashboard.model.LoggingDTO;
import com.tradenet.dashboard.model.PartyDTO;
import com.tradenet.dashboard.model.PermitDTO;
import com.tradenet.dashboard.model.master.LoginDTO;

@Controller
public class SpringMVCController {

	@Autowired
	DashboardDAO dashboardDAO;

	@RequestMapping(value = "/home.web", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = "/riskAllDashboard.web", method = RequestMethod.GET)
	public String riskAllDashBoard(ModelMap model) {
		return "riskall";
	}

	@RequestMapping(value = "/riskPermitPage.web", method = RequestMethod.GET)
	public String riskPermitPage(ModelMap model) {
		return "riskpermit";
	}

	@RequestMapping(value = "/permitapproval.web", method = RequestMethod.GET)
	public String permitApprovalPage(ModelMap model) {
		return "permitapprovaltask";
	}

	@RequestMapping(value = "/inspectionapproval.web", method = RequestMethod.GET)
	public String inspectionApprovalPage(ModelMap model) {
		return "inspectionapprovaltask";
	}

	@RequestMapping(value = "/createlogging.web", method = RequestMethod.GET)
	public String createLogging(ModelMap model) {
		return "createLogging";
	}
	
	@RequestMapping(value = "/login.web", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/searchlogging.web", method = RequestMethod.GET)
	public String searchLogging(ModelMap model) {
		LoggingDTO loggingDTO = new LoggingDTO();
		loggingDTO.setLogId("0");
		model.addAttribute("loggingData", loggingDTO);
		return "searchLogging";
	}

	@RequestMapping(value = "/purgelogging.web", method = RequestMethod.GET)
	public String purgeLogging(ModelMap model) {
		return "purgeLogging";
	}

	@RequestMapping(value = "/loghome.web", method = RequestMethod.GET)
	public String logHome(ModelMap model) {
		return "loghome";
	}
	
	@RequestMapping(value = "/sucesslogin.web", method = RequestMethod.POST)
	public String sucesslogin(ModelMap model) {
		return "loghome";
	}

	@RequestMapping(value = "/getRiskAllStats.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getRiskAllStats() {
		return dashboardDAO.getRiskAllStats();
	}

	@RequestMapping(value = "/getRiskPermitStats.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getRiskPermitStats(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getRiskPermitStats(permitDTO);
	}

	@RequestMapping(value = "/getRiskPermitDetails.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getRiskPermitDetails(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getRiskPermitDetails(permitDTO);
	}

	@RequestMapping(value = "/getRiskPermitIEDetails.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getRiskPermitIEDetails(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getRiskPermitIEDetails(permitDTO);
	}

	@RequestMapping(value = "/getRiskList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getRiskList() {
		return dashboardDAO.getRiskList();
	}

	@RequestMapping(value = "/getPermitItemsList.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getermitAllPartyList(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getPermitItemsList(permitDTO);
	}

	@RequestMapping(value = "/getPermitAllPartyList.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getPermitAllPartyList(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getPermitAllPartyList(permitDTO);
	}

	@RequestMapping(value = "/getPermitContainersList.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getPermitContainersList(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getPermitContainersList(permitDTO);
	}

	@RequestMapping(value = "/getPermitInvoicesList.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getPermitInvoicesList(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getPermitInvoicesList(permitDTO);
	}

	@RequestMapping(value = "/getPermitSupDocList.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getPermitSupDocList(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getPermitSupDocList(permitDTO);
	}

	@RequestMapping(value = "/getRiskPermitChartStats.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getRiskPermitChartStats(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getRiskPermitStats(permitDTO);
	}

	@RequestMapping(value = "/getPermitList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getPermitList() {
		String response = dashboardDAO.getPermitList();
		return response;
	}

	@RequestMapping(value = "/getPermitStatsList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getPermitStatsList() {
		String response = dashboardDAO.getPermitStatsList();
		return response;
	}

	@RequestMapping(value = "/getTopHScode.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getTopHScode() {
		String response = dashboardDAO.getTopHScode();
		return response;
	}

	@RequestMapping(value = "/getTopTraderRed.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getTopTraderRed() {
		String response = dashboardDAO.getTopTraderRed();
		System.out.println(response);
		return response;
	}

	@RequestMapping(value = "/getTopTraderRedPermit.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getTopTraderRedPermit(@RequestBody PartyDTO partyDTO) {
		return dashboardDAO.getTopTraderRedPermit(partyDTO);
	}

	@RequestMapping(value = "/getPermitAuditDetails.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getPermitAuditDetails(@RequestBody PermitDTO permitDTO) {
		return dashboardDAO.getPermitAuditDetails(permitDTO);
	}
/*
	@RequestMapping(value = "/getPermitApprovalTaskList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	List<TaskExtDTO> getPermitApprovalTaskList() {
		TaskQueryServiceFactory factory = new TaskQueryServiceFactory();
		TaskQueryService taskQueryService = factory
				.getTaskQueryService(TaskQueryServiceFactory.DECLARATION_TASKQUERY);
		List<Task> taskList = taskQueryService.getTasks();
		List<TaskExtDTO> taskExtList = new ArrayList<TaskExtDTO>();
		if (!CollectionUtils.isEmpty(taskList)) {
			for (Task task : taskList) {
				TaskExtDTO taskExtDTO = new TaskExtDTO();
				BeanUtils.copyProperties(task, taskExtDTO);
				taskExtList.add(taskExtDTO);
			}
		}
		System.out.println(taskExtList);
		return taskExtList;
	}

	@RequestMapping(value = "/getInspectionApprovalTaskList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	List<TaskExtDTO> getInspectionApprovalTaskList() {
		TaskQueryServiceFactory factory = new TaskQueryServiceFactory();
		TaskQueryService taskQueryService = factory
				.getTaskQueryService(TaskQueryServiceFactory.INSPECTION_TASKQUERY);
		List<Task> taskList = taskQueryService.getTasks();
		List<TaskExtDTO> taskExtList = new ArrayList<TaskExtDTO>();
		if (!CollectionUtils.isEmpty(taskList)) {
			for (Task task : taskList) {
				TaskExtDTO taskExtDTO = new TaskExtDTO();
				BeanUtils.copyProperties(task, taskExtDTO);
				taskExtList.add(taskExtDTO);
			}
		}
		return taskExtList;
	}

	@RequestMapping(value = "/submitPermitApprovalDetails.web", method = RequestMethod.POST, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	void submitPermitApprovalDetails(@RequestBody TaskExtDTO taskExtDTO) {
		dashboardDAO.updateCAApprovalCondition(taskExtDTO);
		dashboardDAO.insertCAApprovalDetails(taskExtDTO);
		TaskQueryServiceFactory factory = new TaskQueryServiceFactory();
		TaskQueryService taskQueryService = factory
				.getTaskQueryService(TaskQueryServiceFactory.DECLARATION_TASKQUERY);
		taskQueryService.updateTask(taskExtDTO);
	}

	@RequestMapping(value = "/submitInspectionApprovalDetails.web", method = RequestMethod.POST, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	void submitInspectionApprovalDetails(@RequestBody TaskExtDTO taskExtDTO) {
		System.out.println("Status :: " + taskExtDTO.getStatus());
		TaskQueryServiceFactory factory = new TaskQueryServiceFactory();
		TaskQueryService taskQueryService = factory
				.getTaskQueryService(TaskQueryServiceFactory.INSPECTION_TASKQUERY);
		taskQueryService.updateTask(taskExtDTO);
	}
*/
	@RequestMapping(value = "/createLog.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	LoggingDTO createLog(@RequestBody LoggingDTO loggingDTO) {
		System.out.println("Inside");
		return dashboardDAO.createLog(loggingDTO);
	}

	@RequestMapping(value = "/searchLog.web", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getLoggingList(@RequestBody LoggingDTO loggingDTO) {
		return dashboardDAO.getLoggingList(loggingDTO);
	}

	@RequestMapping(value = "/purgeLog.web", method = RequestMethod.POST)
	public @ResponseBody
	void purgeLogging(@RequestBody LoggingDTO loggingDTO) {
		dashboardDAO.purgeLogging(loggingDTO);
	}

	@RequestMapping(value = "/getLogStatsList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getLogStatsList() {
		String response = dashboardDAO.getLogStatsList();
		return response;
	}

	@RequestMapping(value = "/getLogDateTimeList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getLogDateTimeList() {
		String response = dashboardDAO.getLogDateTimeList();
		return response;
	}

	@RequestMapping(value = "/getAppCountList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getAppCountList() {
		String response = dashboardDAO.getAppCountList();
		return response;
	}

	@RequestMapping(value = "/getModuleCountList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getModuleCountList() {
		String response = dashboardDAO.getModuleCountList();
		return response;
	}

	@RequestMapping(value = "/getFunctionCountList.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getFunctionCountList() {
		String response = dashboardDAO.getFunctionCountList();
		return response;
	}

	

	@RequestMapping(value = "/getAllApplicationDetails.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getAllApplicationDetails() {
		return dashboardDAO.getAllApplicationDetails();
	}

	@RequestMapping(value = "/getAllModuleDetails.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getAllModuleDetails() {
		return dashboardDAO.getAllModuleDetails();
	}
	
	@RequestMapping(value = "/getAllLogLevelDetails.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getAllLogLevelDetails() {
		return dashboardDAO.getAllLogLevelDetails();
	}
	
	@RequestMapping(value = "/getAllFunctionDetails.web", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	String getAllFunctionDetails() {
		return dashboardDAO.getAllFunctionDetails();
	}
	
	@RequestMapping(value="/getpdf1.web", method=RequestMethod.POST, produces = { "application/pdf"})
	public ResponseEntity<byte[]> getPDF(@RequestBody LoggingDTO loggingDTO) {
		return dashboardDAO.getPdf(loggingDTO);
	  
	}
	
	
	@RequestMapping(value = "/getpdf.web", method = RequestMethod.POST, produces = { "application/pdf"})
	@ResponseBody
	public void handlePdf(@RequestBody LoggingDTO loggingDTO, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	    String fileName = "Logging.pdf";
	    response.setHeader("Content-disposition", "attachment; filename=" + fileName);
	    OutputStream out = response.getOutputStream();
	    //String content = printContent.replaceAll("<br>", "<br/>");
	    ITextRenderer renderer = new ITextRenderer();
	    renderer.setDocumentFromString(getPdfHtml(loggingDTO,request));
	    renderer.layout();
	    renderer.createPDF(out);
	    out.close();
	}
	 
	private String getPdfHtml(LoggingDTO loggingDTO,HttpServletRequest request) throws Exception
	{
	    StringBuilder sb = new StringBuilder();
	    sb.append("<html>");
	    sb.append("<head><title>Logging PDF</title><style language='text/css'>");
	    //if performance is bad we could ignore this big css
	    //String rcStylePath = request.getServletContext().getRealPath("/css/bootstrap/bootstrap.min.css");
	    //sb.append(Files.toString(new File(rcStylePath), StandardCharsets.UTF_8));
	    sb.append(" table{border:1px solid gray !important}; ");
	    sb.append(" td, th {border:1px solid gray !important}; ");
	    sb.append(" tr:nth-child(even) {background: #CCC}; tr:nth-child(odd) {background: #FFF}; ");
	    sb.append("</style></head>");
	    sb.append("<body>");
	    sb.append(generateTableForPDF(loggingDTO));
	    sb.append("</body>");
	    sb.append("</html>");
	    return sb.toString();
	}
	
	@RequestMapping(value = "/getexcel.web", method = RequestMethod.POST)
	@ResponseBody
	public void handleExcel(@RequestBody LoggingDTO loggingDTO,HttpServletResponse response) throws IOException
	{
	    String fileName = "Logging.xls";
	    response.setHeader("Content-disposition", "attachment; filename=" + fileName);
	    OutputStream out = response.getOutputStream();
	    out.write(getExcelHtml(loggingDTO).getBytes(StandardCharsets.UTF_8));
	    out.close();
	}
	
	@RequestMapping(value = "/getPrint.web", method = RequestMethod.POST)
	@ResponseBody
	public void handlePrint(@RequestBody LoggingDTO loggingDTO,HttpServletResponse response) throws IOException
	{
	    OutputStream out = response.getOutputStream();
	    out.write(getPrintHtml(loggingDTO).getBytes(StandardCharsets.UTF_8));
	    out.close();
	}
	 
	private String getExcelHtml(LoggingDTO loggingDTO) throws IOException
	{
	    StringBuilder sb = new StringBuilder();
	    sb.append("<html>");
	    sb.append("<head><style language='text/css'>");
	    sb.append(" table{border:1px solid gray !important}; ");
	    sb.append(" td, th {border:1px solid gray !important}; ");
	    sb.append(" tr {border:1px solid gray !important}; ");
	    sb.append("</style></head>");
	    sb.append("<body>");
	    sb.append(generateTableForPDF(loggingDTO));
	    sb.append("</body>");
	    sb.append("</html>");
	    return sb.toString();
	}
	
	private String getPrintHtml(LoggingDTO loggingDTO) throws IOException
	{
	    StringBuilder sb = new StringBuilder();
	    sb.append("<html>");
	    sb.append("<head><style language='text/css'>");
	    sb.append(" table{border:1px solid gray !important}; ");
	    sb.append(" td, th {border:1px solid gray !important}; ");
	    sb.append(" tr {border:1px solid gray !important}; ");
	    sb.append("</style></head>");
	    sb.append("<body>");
	    sb.append("<input type=\"button\" value=\"Print\" onclick=\"window.print()\" style=\"align:right\"></input>");
	    sb.append(generateTableForPDF(loggingDTO));
	    sb.append("</body>");
	    sb.append("</html>");
	    return sb.toString();
	}
	
	private String generateTableForPDF(LoggingDTO loggingDTO)
	{
		List<LoggingDTO> loggingDTOList = dashboardDAO.getLoggingListForPDF(loggingDTO);
		StringBuffer sbTable = new StringBuffer();
		sbTable.append("<table>");
		sbTable.append("<thead><tr style=\"background-color:#ABABAB\"><th>App Id</th><th>Module</th><th>Function</th><th>Log Level</th><th>Transaction ID</th><th>Log Date</th></tr></thead><tbody>");
		if(loggingDTOList!=null && loggingDTOList.size()>0)
		{
			for( LoggingDTO logging : loggingDTOList)
			{
				sbTable.append("<tr><td>");
				sbTable.append(logging.getAppDesc());
				sbTable.append("</td><td>");
				sbTable.append(logging.getModuleName());
				sbTable.append("</td><td>");
				sbTable.append(logging.getFunctionName());
				sbTable.append("</td><td>");
				sbTable.append(logging.getLogLevelDesc());
				sbTable.append("</td><td>");
				sbTable.append(logging.getTransactionRefId());
				sbTable.append("</td><td>");
				sbTable.append(logging.getLogDateTime());
				sbTable.append("</td></tr>");
			}
		}
		sbTable.append("</tbody></table>");
		return sbTable.toString();
	}
	
	@RequestMapping(value = "/login.web", method = RequestMethod.POST, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	Boolean login(@RequestBody LoginDTO loginDTO,HttpServletResponse response) {
		//response.setStatus(HttpStatus.BAD_REQUEST.value());
		return dashboardDAO.login(loginDTO);
	}
	
	@RequestMapping(value = "/getTransactionBased.web", method = RequestMethod.GET)
	public @ResponseBody
	String getTransactionBased() {
		return dashboardDAO.getTransactionBased();
	}

}
