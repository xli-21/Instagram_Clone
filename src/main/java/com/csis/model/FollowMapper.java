package com.csis.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FollowMapper implements RowMapper<Follow> {
	
	public Follow mapRow(ResultSet resultSet, int i) throws SQLException	{
	
		Follow follow = new Follow();
		follow.setFollowerusername(resultSet.getString("followerusername"));
		follow.setHostusername(resultSet.getString("hostusername"));
		return follow;
	}
}