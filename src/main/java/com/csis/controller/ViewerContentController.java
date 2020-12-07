package com.csis.controller;

import java.util.Collections;
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
import com.csis.model.Image;
import com.csis.model.UserAccount;

@Controller
public class ViewerContentController {
	
	@Autowired
	ImageDaoImpl imageDaoImpl;
	
	@Autowired
	PageContentDaoImpl pageContentDaoImpl;

	@RequestMapping(value = "userContent", method = RequestMethod.GET)
	 public String viewHostphotos(@RequestParam(required = true) String username,ModelMap model,HttpSession session) {
	
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		boolean fo = pageContentDaoImpl.followingOrNot(user.getUsername(), username);
		if(fo) {
			model.addAttribute("follow", "unfollow");
		}
		else {
			model.addAttribute("follow", "follow");
		}
		model.addAttribute("mainPage", user);
		List<Image> images = imageDaoImpl.loadImage(username);
		Collections.reverse(images); 
		model.addAttribute("images", images);
		model.addAttribute("hostname", username);
		session.setAttribute("host", username);
		return "userContent";
	}
	
	@RequestMapping(value = "userContent", method = RequestMethod.POST, params = "back")
	 public String userContentToMain(ModelMap model,HttpSession session) {
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		model.addAttribute("mainPage", user);
		
		return "redirect:mainPage";
	 }
	
	@RequestMapping(value = "userContent", method = RequestMethod.POST, params = "follow")
	 public String userContentFollow(ModelMap model,HttpSession session) {
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		String hostname = (String) session.getAttribute("host");
		
		boolean fo = pageContentDaoImpl.followingOrNot(user.getUsername(), hostname);
		if(fo) {
			pageContentDaoImpl.followingDelete(user.getUsername(), hostname);
		}
		else {
			pageContentDaoImpl.followingInsert(user.getUsername(), hostname);
		}
		model.addAttribute("userContent", user);
		
		return "redirect:userContent/?username="+hostname;
	 }
}
