package com.csis.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ImageMapper implements RowMapper<Image> {
	
	public Image mapRow(ResultSet resultSet, int i) throws SQLException	{
	
		Image image = new Image();
		image.setId(resultSet.getInt("id"));
		image.setUsername(resultSet.getString("username"));
		image.setFileLocation(resultSet.getString("filelocation"));
		image.setUploadDate(resultSet.getTimestamp("uploaddate"));
		image.setDesc(resultSet.getString("desc"));
		return image;
	}
}