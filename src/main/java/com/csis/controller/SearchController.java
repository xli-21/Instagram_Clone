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
import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.Follow;
import com.csis.model.Image;
import com.csis.model.UserAccount;

@Controller

public class SearchController {

	@Autowired
	UserAccountDaoImpl userDaoImpl;
	@Autowired
	ImageDaoImpl imageDaoImpl;
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	 public String searchPage(ModelMap model,HttpSession session) {
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		model.addAttribute("search", user);
		return "search";
	 }
	
	@RequestMapping(value = "search", method = RequestMethod.POST)
	 public String searchUser(@RequestParam("search") String search, ModelMap model,HttpSession session) {
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		List<UserAccount> searchedUsers = userDaoImpl.searchLikeUser(search);
		for (int i=0; i<searchedUsers.size();i++) {
			if(searchedUsers.get(i).getUsername().equals(user.getUsername())){
				searchedUsers.remove(i);
			}
        }
		model.addAttribute("userList", searchedUsers);
		model.addAttribute("search", user);
		return "search";
	 }
}
