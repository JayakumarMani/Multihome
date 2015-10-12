package com.tradenet.dashboard.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LoggingMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoggingDTO loggingDTO = new LoggingDTO();
		loggingDTO.setLogId(rs.getString("log_id"));
		loggingDTO.setAppId(rs.getLong("app_id"));
		loggingDTO.setSourceIp(rs.getString("source_ip"));
		loggingDTO.setModuleName(rs.getString("module_name"));
		loggingDTO.setFunctionName(rs.getString("function_name"));
		loggingDTO.setTransactionRefId(rs.getString("transaction_ref_id"));
		loggingDTO.setSubTransactionId(rs.getString("sub_transaction_id"));
		loggingDTO.setLogLevel(rs.getInt("log_level"));
		loggingDTO.setLogDateTime(rs.getTimestamp("log_datetime"));
		loggingDTO.setLogBy(rs.getString("log_by"));
		loggingDTO.setLogEntryLevel(rs.getString("log_createddatetime"));
		loggingDTO.setLogMessage(rs.getString("log_message"));
		loggingDTO.setAppDesc(rs.getString("APP_DESC"));
		loggingDTO.setLogLevelDesc(rs.getString("LOG_LEVEL_DESC"));
		return loggingDTO;
	}

}
