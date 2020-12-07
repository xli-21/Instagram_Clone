package com.csis.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csis.model.Follow;
import com.csis.model.FollowMapper;


@Component
@Repository
public class PageContentDaoImpl {

	JdbcTemplate jdbcTemplate;
	
	private final String SQL_SEARCH_ID = "SELECT * FROM FOLLOW WHERE FOLLOWERUSERNAME = ?";
	private final String SQL_SEARCH_FOLLOW = "SELECT * FROM FOLLOW WHERE FOLLOWERUSERNAME = ? AND HOSTUSERNAME = ?";
	private final String SQL_INSERT_FOLLOW = "INSERT INTO FOLLOW(FOLLOWERUSERNAME,HOSTUSERNAME) VALUES (?,?)";
	private final String SQL_DELETE_FOLLOW = "DELETE FROM FOLLOW WHERE FOLLOWERUSERNAME = ? AND HOSTUSERNAME = ?";
	
	@Autowired
	public PageContentDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Follow> followingList(String followerUsename) {
		return jdbcTemplate.query(SQL_SEARCH_ID, new FollowMapper(), new Object[] {followerUsename});
	}
	
	public boolean followingOrNot(String followerusername, String hostusername) {
		List<Follow> follow = jdbcTemplate.query(SQL_SEARCH_FOLLOW, new FollowMapper(), new Object[]{followerusername, hostusername});
		return follow.size() > 0;
	}
	
	public boolean followingInsert(String followerusername, String hostusername) {
		return jdbcTemplate.update(SQL_INSERT_FOLLOW, followerusername, hostusername) > 0;
	}
	
	public boolean followingDelete(String followerusername, String hostusername) {
		return jdbcTemplate.update(SQL_DELETE_FOLLOW, followerusername, hostusername) > 0;
	}
		
}
