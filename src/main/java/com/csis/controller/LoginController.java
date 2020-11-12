package com.csis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.Login;
import com.csis.model.UserAccount;

@Controller
public class LoginController {

	@Autowired
	UserAccountDaoImpl userDaoImpl;
	

	@RequestMapping( value = "/" ,  method=RequestMethod.GET)
	public String indexPage() throws Exception {
		return "redirect:login";   
	}
	
	//controllers for login
	@RequestMapping(value = "login", method = RequestMethod.GET)
	 public String showLogin(ModelMap model,HttpSession session) {
		 session.invalidate();
		 Login login = new Login();
		 model.addAttribute("login", login);
		 return "login";
	 }
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	 public String login(Login login, BindingResult result, ModelMap model,HttpSession session) {
		 
		UserAccount user = userDaoImpl.loginUser(login);
		
		if(user == null) {
			model.addAttribute("messages", "wrong username or password");
			return "login";
		}
		else {
			session.setAttribute("user",user);
			return "redirect:mainPage";
		}
	 }
	
	
	
}
