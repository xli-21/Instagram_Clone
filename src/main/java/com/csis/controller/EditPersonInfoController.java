package com.csis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.UserAccount;

@Controller
@RequestMapping("/editPerson")
public class EditPersonInfoController {
	@Autowired
	UserAccountDaoImpl userDaoImpl;
		
	@RequestMapping(method = RequestMethod.GET)
		public String showEditPage(ModelMap model, HttpServletRequest request,HttpSession session) {
			
			UserAccount user = (UserAccount) session.getAttribute("user");
			model.addAttribute("editPerson", user);
			model.addAttribute("username", user.getUsername());
			model.addAttribute("firstname", user.getFirstname());
			model.addAttribute("lastname", user.getLastname());
			model.addAttribute("email", user.getEmail());
			return "editPerson";
	}
		
	@RequestMapping(method = RequestMethod.POST)
		public String edit(UserAccount user, BindingResult result, ModelMap model) {
			if (result.hasErrors()) {
				return "editPerson";
			}
			userDaoImpl.editUser(user);
			model.addAttribute("messages", "Account successfully edited");
			
		 return "redirect:login";
	}
		
}
