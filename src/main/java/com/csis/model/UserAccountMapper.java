package com.csis.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class UserAccountMapper implements RowMapper<UserAccount> {
	
	public UserAccount mapRow(ResultSet resultSet, int i) throws SQLException	{
	
		UserAccount user = new UserAccount();
		user.setUsername(resultSet.getString("username"));
		user.setPassword(resultSet.getString("password"));
		user.setFirstname(resultSet.getString("firstname"));
		user.setLastname(resultSet.getString("lastname"));
		user.setEmail(resultSet.getString("email"));
		return user;
	}
}