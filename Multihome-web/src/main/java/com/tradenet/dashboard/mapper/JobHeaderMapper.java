package com.tradenet.dashboard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tradenet.dashboard.model.JobHeaderDTO;

public class JobHeaderMapper implements RowMapper<JobHeaderDTO> {

	public JobHeaderDTO mapRow(ResultSet resultSet, int rowNum)
			throws SQLException {
		JobHeaderDTO jobHeaderDTO = new JobHeaderDTO();
		jobHeaderDTO.setJobNumber(resultSet.getString("JOB_NO"));
		jobHeaderDTO.setEi(resultSet.getString("EI"));
		jobHeaderDTO.setDecType(resultSet.getString("DEC_TYPE"));
		jobHeaderDTO.setPermitNumber(resultSet.getString("PERMIT_NO"));
		jobHeaderDTO.setMessageType(resultSet.getString("MSG_TYPE"));
		return jobHeaderDTO;
	}

}
