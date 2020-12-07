package com.csis.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csis.model.Comment;
import com.csis.model.CommentMapper;
import com.csis.model.Image;
import com.csis.model.ImageMapper;

@Component
@Repository
public class ImageDaoImpl {
	
	JdbcTemplate jdbcTemplate;
	
	private final String SQL_INSERT = "INSERT INTO IMAGE(username,filelocation,uploaddate,desc) VALUES (?,?,?,?)";
	private final String SQL_SEARCH_USERNAME = "SELECT * FROM IMAGE WHERE USERNAME = ?";
	private final String SQL_SEARCH_ID = "SELECT * FROM IMAGE WHERE ID = ?";
	private final String SQL_UPDATE = "UPDATE IMAGE SET DESC = ? WHERE ID = ?";
	private final String SQL_DELETE= "DELETE FROM IMAGE WHERE ID = ?";
	private final String SQL_COMMENTS = "SELECT * FROM COMMENT WHERE IMAGEID = ?";
	private final String SQL_INSERT_COMMENT = "INSERT INTO COMMENT(IMAGEID,USERNAME,TEXT) VALUES (?,?,?)";
	
	@Autowired
	public ImageDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean saveImage(Image image) {
		return jdbcTemplate.update(SQL_INSERT, image.getUsername(), image.getFileLocation(), image.getUploadDate(), image.getDesc()) > 0;
	}

	public List<Image> loadImage(String username) {
		return jdbcTemplate.query(SQL_SEARCH_USERNAME, new ImageMapper(), new Object[] {username});
	}

	public Image loadImageById(int id) {
		return jdbcTemplate.queryForObject(SQL_SEARCH_ID, new ImageMapper(), id);
	}

	public boolean editPhoto(String desc, int id) {
		return jdbcTemplate.update(SQL_UPDATE, desc, id) > 0;
	}

	public boolean deletePhoto(int id) {
		return jdbcTemplate.update(SQL_DELETE, id) > 0;
		
	}
	
	public List<Comment> loadComment(int imageId) {
		return jdbcTemplate.query(SQL_COMMENTS, new CommentMapper(), new Object[] {imageId});
	}

	public boolean addComment(int imageId, String username, String text) {
		return jdbcTemplate.update(SQL_INSERT_COMMENT, imageId, username, text) > 0;
	}
}
