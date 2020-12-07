package com.csis.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CommentMapper implements RowMapper<Comment> {
	
	public Comment mapRow(ResultSet resultSet, int i) throws SQLException	{
	
		Comment comment = new Comment();
		comment.setId(resultSet.getInt("id"));
		comment.setImageid(resultSet.getInt("imageid"));
		comment.setUsername(resultSet.getString("username"));
		comment.setText(resultSet.getString("text"));
		
		return comment;
	}
}
