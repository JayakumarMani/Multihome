package com.tradenet.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.tradenet.dashboard.mapper.json.JsonNodeRowMapper;
import com.tradenet.dashboard.model.LoggingDTO;
import com.tradenet.dashboard.model.LoggingMapper;
import com.tradenet.dashboard.model.PartyDTO;
import com.tradenet.dashboard.model.PermitDTO;
import com.tradenet.dashboard.model.TaskExtDTO;
import com.tradenet.dashboard.model.master.LoginDTO;
import com.tradenet.dashboard.util.PdfUtility;

public class DashboardDAOImpl implements DashboardDAO {

	private DataSource dataSource;
	private DataSource mysqldataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getMysqldataSource() {
		return mysqldataSource;
	}

	public void setMysqldataSource(DataSource mysqldataSource) {
		this.mysqldataSource = mysqldataSource;
	}

	public String getRiskAllStats() {
		String sqlQuery = "select LANE, count(LANE) as LANECOUNT from TTDS_RISK group by LANE order by LANE ASC";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sqlQuery,
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getRiskList() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select TTDS_MESSAGE_HEADER.JOB_NO, TTDS_MESSAGE_HEADER.USER_ID, TTDS_MESSAGE_HEADER.LAST_PERMIT_NO, TO_char(TTDS_RISK.PROCESSED_DATE,'dd/mm/yyyy hh:mm') as PROCESSED_ON,");
		sbSQL.append(" TTDS_RISK.LANE FROM TTDS_MESSAGE_HEADER,TTDS_RISK ");
		sbSQL.append(" WHERE TTDS_MESSAGE_HEADER.JOB_NO = TTDS_RISK.JOB_NO ");
		sbSQL.append(" AND TTDS_MESSAGE_HEADER.USER_ID = TTDS_RISK.USER_ID");
		sbSQL.append(" AND ROWNUM < 7 ORDER BY TTDS_RISK.PROCESSED_DATE DESC");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getRiskPermitStats(PermitDTO permitDTO) {
		String sqlQuery = "SELECT * FROM TTDS_RISK where USER_ID=? AND JOB_NO = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		JsonNode jsonNode = jdbcTemplate.queryForObject(sqlQuery, new Object[] {
				permitDTO.getUserId(), permitDTO.getJobNo() },
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getRiskPermitDetails(PermitDTO permitDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select TTDS_MESSAGE_HEADER.LAST_PERMIT_NO,TTDS_HEADER.TOT_ITEM, TTDS_HEADER.TOT_GROSS_WT, TTDS_HEADER.TOT_GROSS_WT_UOM, TTDS_HEADER.TOT_CHARGE, TTDS_HEADER.TOT_CUSTOMS_DUTY_AMT, TTDS_HEADER.TOT_EXCISE_DUTY_AMT,TTDS_MESSAGE_HEADER.LAST_APP_STAT  from TTDS_MESSAGE_HEADER, TTDS_HEADER ");
		sbSQL.append("WHERE TTDS_MESSAGE_HEADER.USER_ID = TTDS_HEADER.USER_ID ");
		sbSQL.append("AND TTDS_MESSAGE_HEADER.JOB_NO = TTDS_HEADER.JOB_NO  ");
		sbSQL.append("AND TTDS_MESSAGE_HEADER.USER_ID = ? AND  ");
		sbSQL.append("TTDS_MESSAGE_HEADER.JOB_NO  = ?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		JsonNode jsonNode = jdbcTemplate.queryForObject(sbSQL.toString(),
				new Object[] { permitDTO.getUserId(), permitDTO.getJobNo() },
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getRiskPermitIEDetails(PermitDTO permitDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_PARTY WHERE USER_ID = ?  AND JOB_NO  = ? AND AGENT_CODE = ?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		JsonNode jsonNode = jdbcTemplate.queryForObject(sbSQL.toString(),
				new Object[] { permitDTO.getUserId(), permitDTO.getJobNo(),
						"IM" }, new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getPermitItemsList(PermitDTO permitDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_ITEM WHERE USER_ID = ?  AND JOB_NO  = ?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new Object[] { permitDTO.getUserId(), permitDTO.getJobNo() },
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getPermitAllPartyList(PermitDTO permitDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_PARTY WHERE USER_ID = ?  AND JOB_NO  = ?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new Object[] { permitDTO.getUserId(), permitDTO.getJobNo() },
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getPermitContainersList(PermitDTO permitDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_CONTAINER WHERE USER_ID = ?  AND JOB_NO  = ?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new Object[] { permitDTO.getUserId(), permitDTO.getJobNo() },
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getPermitInvoicesList(PermitDTO permitDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_INVOICE WHERE USER_ID = ?  AND JOB_NO  = ?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new Object[] { permitDTO.getUserId(), permitDTO.getJobNo() },
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getPermitSupDocList(PermitDTO permitDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_SUPP_DOC WHERE USER_ID = ?  AND JOB_NO  = ?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new Object[] { permitDTO.getUserId(), permitDTO.getJobNo() },
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getPermitList() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select TTDS_MESSAGE_HEADER.LAST_PERMIT_NO,TTDS_HEADER.* from TTDS_MESSAGE_HEADER,TTDS_HEADER WHERE ");
		sbSQL.append(" TTDS_MESSAGE_HEADER.USER_ID = TTDS_HEADER.USER_ID AND ");
		sbSQL.append(" TTDS_MESSAGE_HEADER.JOB_NO = TTDS_HEADER.JOB_NO ");
		sbSQL.append(" AND length(TTDS_MESSAGE_HEADER.JOB_NO)>6 and  ");
		sbSQL.append(" TTDS_MESSAGE_HEADER.JOB_NO LIKE '2014%' AND TTDS_MESSAGE_HEADER.user_id like 'TDBI%' ");
		sbSQL.append(" and message_type='INPPMT' ");
		sbSQL.append(" and ROWNUM < 11 ");
		sbSQL.append(" order BY TTDS_MESSAGE_HEADER.last_app_date desc ");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getPermitStatsList() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select count(*) AS TOTAL_PROCESSED, sum(TTDS_HEADER.TOT_GST_AMT) AS GST_AMT,sum(TTDS_HEADER.TOT_CUSTOMS_DUTY_AMT) AS CUSTOMS_DUTY_AMT,sum(TTDS_HEADER.TOT_EXCISE_DUTY_AMT) AS EXCISE_AMT from TTDS_MESSAGE_HEADER,TTDS_HEADER WHERE");
		sbSQL.append(" TTDS_MESSAGE_HEADER.USER_ID = TTDS_HEADER.USER_ID AND ");
		sbSQL.append(" TTDS_MESSAGE_HEADER.JOB_NO = TTDS_HEADER.JOB_NO ");
		sbSQL.append(" AND length(TTDS_MESSAGE_HEADER.JOB_NO)>6 and  ");
		sbSQL.append(" TTDS_MESSAGE_HEADER.JOB_NO LIKE '2014%' AND TTDS_MESSAGE_HEADER.user_id like 'TDBI%' ");
		sbSQL.append(" and message_type='INPPMT' ");
		jdbcTemplate = new JdbcTemplate(dataSource);
		JsonNode jsonNode = jdbcTemplate.queryForObject(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getTopHScode() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM (SELECT count(HS_CODE) AS COUNT, HS_CODE AS CODE, ITEM_DESC AS DESCRIPTION ");
		sbSQL.append("FROM ttds_item GROUP BY HS_CODE,ITEM_DESC ORDER BY count(HS_CODE) DESC)  ");
		sbSQL.append(" WHERE ROWNUM<6 ");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getTopTraderRed() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select COUNT(TTDS_MESSAGE_HEADER.last_permit_no) AS COUNT,TTDS_PARTY.NAME AS NAME  from TTDS_PARTY, TTDS_RISK, TTDS_MESSAGE_HEADER WHERE  ");
		sbSQL.append("TTDS_PARTY.USER_ID = TTDS_RISK.USER_ID AND   ");
		sbSQL.append("TTDS_PARTY.JOB_NO = TTDS_RISK.JOB_NO AND  ");
		sbSQL.append("TTDS_MESSAGE_HEADER.USER_ID = TTDS_RISK.USER_ID AND  ");
		sbSQL.append("TTDS_MESSAGE_HEADER.JOB_NO = TTDS_RISK.JOB_NO ");
		sbSQL.append("AND TTDS_RISK.LANE='Red' ");
		sbSQL.append("and TTDS_PARTY.agent_code = 'IM' ");
		sbSQL.append("and TTDS_MESSAGE_HEADER.last_permit_no is NOT NULL ");
		sbSQL.append("GROUP BY TTDS_PARTY.NAME ");
		sbSQL.append("order by COUNT(*) desc ");
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getTopTraderRedPermit(PartyDTO partyDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select DISTINCT(TTDS_MESSAGE_HEADER.LAST_PERMIT_NO),TTDS_RISK.*  from TTDS_PARTY, TTDS_RISK, TTDS_MESSAGE_HEADER WHERE  ");
		sbSQL.append("TTDS_PARTY.USER_ID = TTDS_RISK.USER_ID AND   ");
		sbSQL.append("TTDS_PARTY.JOB_NO = TTDS_RISK.JOB_NO AND  ");
		sbSQL.append("TTDS_MESSAGE_HEADER.USER_ID = TTDS_RISK.USER_ID AND  ");
		sbSQL.append("TTDS_MESSAGE_HEADER.JOB_NO = TTDS_RISK.JOB_NO ");
		sbSQL.append("AND TTDS_RISK.LANE='Red' ");
		sbSQL.append("and TTDS_PARTY.agent_code = 'IM' ");
		sbSQL.append("and TTDS_MESSAGE_HEADER.last_permit_no is NOT NULL ");
		sbSQL.append("and TTDS_PARTY.NAME=? ");

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new Object[] { partyDTO.getName() }, new JsonNodeRowMapper(
						new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getPermitAuditDetails(PermitDTO permitDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT TO_TIMESTAMP(TRD_POLL_START_DTM,'YYYYMMDDHH24MISSFF') AS TRD_POLL_START_DTM,TO_TIMESTAMP(TRD_POLL_END_DTM,'YYYYMMDDHH24MISSFF') AS TRD_POLL_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(EDI_XML_START_DTM,'YYYYMMDDHH24MISSFF') AS EDI_XML_START_DTM,TO_TIMESTAMP(EDI_XML_END_DTM,'YYYYMMDDHH24MISSFF') AS EDI_XML_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_HOST_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_HOST_START_DTM,TO_TIMESTAMP(TN_HOST_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_HOST_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_HOST_DCS_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_HOST_DCS_START_DTM,TO_TIMESTAMP(TN_HOST_DCS_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_HOST_DCS_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_HOST_PPS_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_HOST_PPS_START_DTM,TO_TIMESTAMP(TN_HOST_PPS_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_HOST_PPS_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_CA_AUTO_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_CA_AUTO_START_DTM,TO_TIMESTAMP(TN_CA_AUTO_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_CA_AUTO_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_PCS_PCP_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_PCS_PCP_START_DTM,TO_TIMESTAMP(TN_PCS_PCP_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_PCS_PCP_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(XML_TNIBI_START_DTM,'YYYYMMDDHH24MISSFF') AS XML_TNIBI_START_DTM,TO_TIMESTAMP(XML_TNIBI_END_DTM,'YYYYMMDDHH24MISSFF') AS XML_TNIBI_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_STK_QRY_REQ_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_STK_QRY_REQ_START_DTM,TO_TIMESTAMP(TN_STK_QRY_REQ_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_STK_QRY_REQ_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_STK_QRY_RET_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_STK_QRY_RET_START_DTM,TO_TIMESTAMP(TN_STK_QRY_RET_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_STK_QRY_RET_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_STK_UPD_INSTR_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_STK_UPD_INSTR_START_DTM,TO_TIMESTAMP(TN_STK_UPD_INSTR_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_STK_UPD_INSTR_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_SC_CA_AUTO_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_SC_CA_AUTO_START_DTM,TO_TIMESTAMP(TN_SC_CA_AUTO_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_SC_CA_AUTO_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_SC_CA_ONLINE_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_SC_CA_ONLINE_START_DTM,TO_TIMESTAMP(TN_SC_CA_ONLINE_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_SC_CA_ONLINE_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(TN_HOST_OUT_START_DTM,'YYYYMMDDHH24MISSFF') AS TN_HOST_OUT_START_DTM,TO_TIMESTAMP(TN_HOST_OUT_END_DTM,'YYYYMMDDHH24MISSFF') AS TN_HOST_OUT_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(XML_EDI_START_DTM,'YYYYMMDDHH24MISSFF') AS XML_EDI_START_DTM,TO_TIMESTAMP(XML_EDI_END_DTM,'YYYYMMDDHH24MISSFF') AS XML_EDI_END_DTM, ");
		sbSQL.append("TO_TIMESTAMP(XML_XML_ECUSTOMS_START_DTM,'YYYYMMDDHH24MISSFF') AS XML_XML_ECUSTOMS_START_DTM,TO_TIMESTAMP(XML_XML_ECUSTOMS_END_DTM,'YYYYMMDDHH24MISSFF') AS XML_XML_ECUSTOMS_END_DTM ");
		sbSQL.append("FROM TTDS_AUDIT WHERE USER_ID = ?  AND JOB_NUMBER  = ?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		JsonNode jsonNode = jdbcTemplate.queryForObject(sbSQL.toString(),
				new Object[] { permitDTO.getUserId(), permitDTO.getJobNo() },
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public void updateCAApprovalCondition(TaskExtDTO taskExtDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("update TTDS_CA_APPROVAL_CONDITIONS set CONDITION_CODE=?, CONDITION_MSG=?, APPROVAL_DATE=? WHERE USER_ID = ? AND JOB_NO=? AND CA_IND=? AND ITEM_SEQ_NO=?");
		jdbcTemplate = new JdbcTemplate(dataSource);
		// jdbcTemplate.update(sbSQL.toString(), new
		// Object[]{taskExtDTO.getConditionCode(),taskExtDTO.getConditionMessage(),taskExtDTO.getUpdatedDate(),taskExtDTO.getSenderId(),taskExtDTO.getJobNumber()});

	}

	public void insertCAApprovalDetails(TaskExtDTO taskExtDTO) {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("Insert into TTDS_CA_APPROVAL_DETAILS(USER_ID,JOB_NO,ITEM_SEQ_NO,CA_IND,APPROVAL_STATUS,APPROVAL_DATE) values(?,?,?,?,?,?)");
		jdbcTemplate = new JdbcTemplate(dataSource);
		// jdbcTemplate.update(sbSQL.toString(), new
		// Object[]{taskExtDTO.getSenderId(),taskExtDTO.getJobNumber()});
	}

	public LoggingDTO createLog(LoggingDTO loggingDTO) {
		try {
			StringBuffer sbSQL = new StringBuffer();
			sbSQL.append("Insert into log_entries (log_id,app_id,source_ip,module_name,function_name,transaction_ref_id,sub_transaction_id,log_level,log_datetime,log_by,log_createddatetime,log_entry_type,log_message) values(?,?,?,?,?,?,?,?,SYSDATE(),?,SYSDATE(),?,?)");

			//sbSQL.append("Insert into log_entries (log_id,app_id,source_ip,module_name,function_name,transaction_ref_id,sub_transaction_id,log_level,log_datetime,log_by,log_createddatetime,log_entry_type,log_message) values(?,?,?,?,?,?,?,?,DATE_SUB(NOW(), INTERVAL 4 DAY),?,DATE_SUB(NOW(), INTERVAL 4 DAY),?,?)");
			jdbcTemplate = new JdbcTemplate(mysqldataSource);
			System.out.println(sbSQL.toString());
			jdbcTemplate.update(
					sbSQL.toString(),
					new Object[] { UUID.randomUUID().toString(),
							loggingDTO.getAppId(), loggingDTO.getSourceIp(),
							loggingDTO.getModuleName(),
							loggingDTO.getFunctionName(),
							loggingDTO.getTransactionRefId(),
							loggingDTO.getSubTransactionId(),
							loggingDTO.getLogLevel(), loggingDTO.getLogBy(),
							loggingDTO.getLogEntryLevel(),
							loggingDTO.getLogMessage() });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return loggingDTO;
	}

	public String getLoggingList(LoggingDTO loggingDTO) {
		StringBuffer sbSQL = new StringBuffer();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sbSQL.append("select log_entries.*,TTDS_APP_ID.APP_DESC,TTDS_LOG_LEVEL.LOG_LEVEL_DESC from log_entries,TTDS_APP_ID,TTDS_LOG_LEVEL WHERE 1=1 AND log_entries.app_id = TTDS_APP_ID.APP_ID AND TTDS_LOG_LEVEL.LOG_LEVEL_ID = log_entries.log_level ");
		if (loggingDTO.getAppId() != null) {
			sbSQL.append(" AND log_entries.app_id = :appId");
			parameters.put("appId", loggingDTO.getAppId());
		}
		if (loggingDTO.getModuleName() != null) {
			sbSQL.append(" AND log_entries.module_name like :moduleName");
			parameters.put("moduleName", "%"+loggingDTO.getModuleName()+"%");
		}
		if (loggingDTO.getFunctionName() != null) {
			sbSQL.append(" AND log_entries.function_name like :functionName");
			parameters.put("functionName", "%"+loggingDTO.getFunctionName()+"%");
		}
		if (loggingDTO.getLogLevel() != null) {
			sbSQL.append(" AND log_entries.log_level = :logLevel");
			parameters.put("logLevel", loggingDTO.getLogLevel());
		}
		if (loggingDTO.getTransactionRefId() != null) {
			sbSQL.append(" AND log_entries.transaction_ref_id like :transactionRefId");
			parameters.put("transactionRefId", "%"+loggingDTO.getTransactionRefId()+"%");
		}
		if (loggingDTO.getSubTransactionId() != null) {
			sbSQL.append(" AND log_entries.sub_transaction_id like :subTransactionId");
			parameters.put("subTransactionId", "%"+loggingDTO.getSubTransactionId()+"%");
		}
		sbSQL.append(" ORDER BY log_datetime DESC ");

		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				mysqldataSource);
		System.out.println(namedParameterJdbcTemplate);
		System.out.println(sbSQL.toString());
		List<JsonNode> jsonNode = namedParameterJdbcTemplate.query(sbSQL
				.toString(), parameters, new JsonNodeRowMapper(
				new ObjectMapper()));
		return jsonNode.toString();
	}

	public void purgeLogging(LoggingDTO loggingDTO) {
		StringBuffer sbSQL = new StringBuffer();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sbSQL.append("delete from log_entries WHERE 1=1 ");
		if (loggingDTO.getAppId() != null) {
			sbSQL.append("AND app_id = :appId");
			parameters.put("appId", loggingDTO.getAppId());
		}
		if (loggingDTO.getModuleName() != null) {
			sbSQL.append(" AND log_entries.module_name like :moduleName");
			parameters.put("moduleName", "%"+loggingDTO.getModuleName()+"%");
		}
		if (loggingDTO.getFunctionName() != null) {
			sbSQL.append(" AND log_entries.function_name like :functionName");
			parameters.put("functionName", "%"+loggingDTO.getFunctionName()+"%");
		}
		if (loggingDTO.getLogLevel() != null) {
			sbSQL.append(" AND log_entries.log_level = :logLevel");
			parameters.put("logLevel", loggingDTO.getLogLevel());
		}
		if (loggingDTO.getTransactionRefId() != null) {
			sbSQL.append(" AND log_entries.transaction_ref_id like :transactionRefId");
			parameters.put("transactionRefId", "%"+loggingDTO.getTransactionRefId()+"%");
		}
		if (loggingDTO.getSubTransactionId() != null) {
			sbSQL.append(" AND log_entries.sub_transaction_id like :subTransactionId");
			parameters.put("subTransactionId", "%"+loggingDTO.getSubTransactionId()+"%");
		}
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				mysqldataSource);

		namedParameterJdbcTemplate.update(sbSQL.toString(), parameters);
	}

	public String getLogStatsList() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select log_level, count(log_level) AS log_level_processed from log_entries where log_level is not null group by log_level");
		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getLogDateTimeList() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select DATE_FORMAT(Date(log_datetime),'%d-%b-%y') as log_datetime, count(log_datetime) AS logcount from log_entries group by DATE(log_datetime) order by log_datetime desc limit 7");

		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		System.out.println(jsonNode.toString());
		return jsonNode.toString();
	}

	public String getAppCountList() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select TTDS_APP_ID.APP_DESC AS app_id, count(log_entries.app_id) AS appcount from log_entries,TTDS_APP_ID where log_entries.app_id = TTDS_APP_ID.APP_ID group by log_entries.app_id order by appcount desc limit 7");

		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getModuleCountList() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select module_name, count(module_name) AS modulecount from log_entries where module_name is not null group by module_name order by modulecount desc limit 7");

		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();
	}

	public String getFunctionCountList() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("select function_name, count(function_name) AS functioncount from log_entries where function_name is not null group by function_name order by functioncount desc limit 7");

		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		System.out.println(jsonNode.toString());
		return jsonNode.toString();
	}

	public String getAllFunctionDetails() {

		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT FUNCTION_ID,FUNCTION_CODE,FUNCTION_DESC,STATUS,CREATED_BY,CREATED_TIME,LAST_UPDATED_BY,DATE_FORMAT(LAST_UPDATED_TIME, '%Y-%m-%d') LAST_UPDATED_TIME FROM TTDS_FUNCTION_ID ORDER BY FUNCTION_DESC");

		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();

	}

	public String getAllApplicationDetails() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_APP_ID ORDER BY APP_DESC");

		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();

	}

	public String getAllLogLevelDetails() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_LOG_LEVEL ORDER BY LOG_LEVEL_DESC");

		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();

	}

	public String getAllModuleDetails() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT * FROM TTDS_MODULE_ID ORDER BY MODULE_DESC");

		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		return jsonNode.toString();

	}
	
	public List<LoggingDTO> getLoggingListForPDF(LoggingDTO loggingDTO) {
		StringBuffer sbSQL = new StringBuffer();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sbSQL.append("select log_entries.*,TTDS_APP_ID.APP_DESC as APP_DESC,TTDS_LOG_LEVEL.LOG_LEVEL_DESC as LOG_LEVEL_DESC from log_entries,TTDS_APP_ID,TTDS_LOG_LEVEL WHERE 1=1 AND log_entries.app_id = TTDS_APP_ID.APP_ID AND TTDS_LOG_LEVEL.LOG_LEVEL_ID = log_entries.log_level ");
		if (loggingDTO.getAppId() != null) {
			sbSQL.append(" AND log_entries.app_id = :appId");
			parameters.put("appId", loggingDTO.getAppId());
		}
		if (loggingDTO.getModuleName() != null) {
			sbSQL.append(" AND log_entries.module_name like :moduleName");
			parameters.put("moduleName", "%"+loggingDTO.getModuleName()+"%");
		}
		if (loggingDTO.getFunctionName() != null) {
			sbSQL.append(" AND log_entries.function_name like :functionName");
			parameters.put("functionName", "%"+loggingDTO.getFunctionName()+"%");
		}
		if (loggingDTO.getLogLevel() != null) {
			sbSQL.append(" AND log_entries.log_level = :logLevel");
			parameters.put("logLevel", loggingDTO.getLogLevel());
		}
		if (loggingDTO.getTransactionRefId() != null) {
			sbSQL.append(" AND log_entries.transaction_ref_id like :transactionRefId");
			parameters.put("transactionRefId", "%"+loggingDTO.getTransactionRefId()+"%");
		}
		if (loggingDTO.getSubTransactionId() != null) {
			sbSQL.append(" AND log_entries.sub_transaction_id like :subTransactionId");
			parameters.put("subTransactionId", "%"+loggingDTO.getSubTransactionId()+"%");
		}
		sbSQL.append(" ORDER BY log_datetime DESC ");

		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				mysqldataSource);
		List<LoggingDTO> loggingDTOList = namedParameterJdbcTemplate.query(sbSQL
				.toString(), parameters, new LoggingMapper());
		return loggingDTOList;
	}

	public ResponseEntity<byte[]> getPdf(LoggingDTO loggingDTO) {
		// generate the file
		byte[] contents = null;
		List<LoggingDTO> logList = getLoggingListForPDF(loggingDTO);
		try {
			contents = PdfUtility.showHelp(logList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "LogData.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents,
				headers, HttpStatus.OK);
		System.out.println(response);
		return response;
	}
	
	public Boolean login(LoginDTO loginDTO)
	{
		System.out.println("User Name : " + loginDTO.getUserName());
		return Boolean.TRUE;
	}
	
	
	public String getTransactionBased() {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("SELECT SUM(DATA_FREE) FREE_DATE,CONCAT((FORMAT((DATA_LENGTH + INDEX_LENGTH) / POWER(1024,2),2)), ' Mb')AS 'SIZE',TABLE_ROWS FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'world' AND TABLE_NAME = 'log_entries'");
		jdbcTemplate = new JdbcTemplate(mysqldataSource);
		List<JsonNode> jsonNode = jdbcTemplate.query(sbSQL.toString(),
				new JsonNodeRowMapper(new ObjectMapper()));
		System.out.println(jsonNode.toString());
		return jsonNode.toString();

	}

}
