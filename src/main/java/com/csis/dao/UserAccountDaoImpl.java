package com.csis.dao;

import java.util.List;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csis.model.Login;
import com.csis.model.UserAccount;
import com.csis.model.UserAccountMapper;

@Component
@Repository
public class UserAccountDaoImpl {

	JdbcTemplate jdbcTemplate;
	
	private final String SQL_INSERT = "INSERT INTO USERS (username,password,firstname,lastname,email) VALUES (?,?,?,?,?)";
	private final String SQL_SEARCH = "SELECT * FROM USERS WHERE username='" ;
	private final String SQL_UPDATE = "UPDATE USERS SET password = ? , firstname = ? , lastname = ?, email = ? WHERE username = ?";
	private final String SQL_SEARCH_LIKE = "SELECT * FROM USERS WHERE username LIKE'" ;
	
	@Autowired
	public UserAccountDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	public boolean registerUser(UserAccount newUser) {
		return jdbcTemplate.update(SQL_INSERT, newUser.getUsername(), newUser.getPassword(),newUser.getFirstname(),newUser.getLastname(),newUser.getEmail()) > 0;
	}
	
	public UserAccount loginUser(Login login) {
		List<UserAccount> users = jdbcTemplate.query(SQL_SEARCH + login.getUsername() + "' and password='" + login.getPassword()
	    + "'", new UserAccountMapper());
		return users.size() > 0 ? users.get(0) : null;
	}
	
	public int editUser(UserAccount oldUser) {
		return jdbcTemplate.update(SQL_UPDATE, oldUser.getPassword(), oldUser.getFirstname(), oldUser.getLastname(), oldUser.getEmail(), oldUser.getUsername());
	}


	public List<UserAccount> searchLikeUser(String search) {
		List<UserAccount> users = jdbcTemplate.query(SQL_SEARCH_LIKE + "%" + search + "%'", new UserAccountMapper());
		return users;
	}
}
