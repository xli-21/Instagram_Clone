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
public class LoginController {

	@Autowired
	UserAccountDaoImpl userDaoImpl;
	

	@RequestMapping("/")
	public String indexPage() throws Exception {
		return "redirect:login";   
	}
	
	//controllers for login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	 public String showLogin(ModelMap model) {
		 Login login = new Login();
		 model.addAttribute("login", login);
		 return "login";
	 }
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	 public String login(Login login, BindingResult result, ModelMap model,RedirectAttributes attr) {
		 
		UserAccount user = userDaoImpl.loginUser(login);
		
		if(user == null) {
			model.addAttribute("messages", "wrong username or password");
			return "login";
		}
		else {
			model.addAttribute("messages", "wrong username or password");
			attr.addFlashAttribute("account", user);
			return "redirect:mainPage";
		}
	 }
	
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
