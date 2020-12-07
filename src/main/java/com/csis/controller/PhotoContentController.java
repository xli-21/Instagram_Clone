package com.csis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis.dao.ImageDaoImpl;
import com.csis.dao.PageContentDaoImpl;
import com.csis.model.Comment;
import com.csis.model.Image;
import com.csis.model.UserAccount;


@Controller
public class PhotoContentController {
	
	@Autowired
	ImageDaoImpl imageDaoImpl;
	
	@Autowired
	PageContentDaoImpl pageContentDaoImpl;
	
	@RequestMapping(value = "photoContent", method = RequestMethod.GET)
	 public String viewPhotoContent(@RequestParam(required = true) int id,ModelMap model,HttpSession session) {
	
		String hostname = (String) session.getAttribute("host");
		
		Image image = imageDaoImpl.loadImageById(id);
		
		List<Comment> comments = imageDaoImpl.loadComment(id);
		
		model.addAttribute("image", image);
		model.addAttribute("comments", comments);
		if(!image.getDesc().equals("")) {model.addAttribute("hostname", hostname);}
		session.setAttribute("imageId", id);
		return "photoContent";
	}
	
	@RequestMapping(value = "photoContent", method = RequestMethod.POST)
	 public String AddComment(@RequestParam("comment") String comment,ModelMap model,HttpSession session) {
	
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		int imageid = (int)session.getAttribute("imageId");
		
		imageDaoImpl.addComment(imageid, user.getUsername(), comment);
		
		return "redirect:photoContent/?id="+imageid;
	}

}
