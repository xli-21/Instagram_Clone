package com.csis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.UserAccount;

@Controller
public class RegisterController {
	
	@Autowired
	UserAccountDaoImpl userDaoImpl;

	//controllers for register
		@RequestMapping(value = "register" , method = RequestMethod.GET)
		 public String showRegister(ModelMap model) {
			 UserAccount user = new UserAccount();
			 model.addAttribute("register", user);
			 return "register";
		 }
		
		@RequestMapping(value = "register" , method = RequestMethod.POST)
		 public String register(UserAccount user, BindingResult result, ModelMap model) {
			 if (result.hasErrors()) {
				 return "register";
			 }
			 userDaoImpl.registerUser(user);
			 model.addAttribute("messages", "Account successfully created");
			
			 return "redirect:login";
		 }
}
