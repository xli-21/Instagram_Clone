package com.csis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.Login;
import com.csis.model.UserAccount;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserAccountDaoImpl userDaoImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	 public String newProfile(ModelMap model) {
		 Login login = new Login();
		 model.addAttribute("login", login);
		 return "login";
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	 public String saveProfile(Login login, BindingResult result, ModelMap model,RedirectAttributes attr) {
		 
		UserAccount user = userDaoImpl.loginUser(login);
		
		if(user == null) {
			model.addAttribute("messages", "wrong username or password");
			return "login";
		}
		else {
			model.addAttribute("messages", "wrong username or password");
			attr.addFlashAttribute("account", user);
			return "redirect:profile";
		}
	 }
}
