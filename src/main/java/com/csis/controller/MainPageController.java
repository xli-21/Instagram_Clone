package com.csis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.UserAccount;

@Controller
public class MainPageController {

	@Autowired
	UserAccountDaoImpl userDaoImpl;
	
	@RequestMapping(value = "mainPage", method = RequestMethod.GET)
	 public String showMainPage(ModelMap model,HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		model.addAttribute("name", user.getFirstname());
		model.addAttribute("mainPage", user);
		return "mainPage";
	 }
	
	/*@RequestMapping(value = "mainPage", method = RequestMethod.POST)
	 public String mainPage(@ModelAttribute("mainPage")UserAccount user, BindingResult result, ModelMap model,HttpSession session) {
		if (result.hasErrors()) {
		 return "mainPage";
		}
		
		return "redirect:editPerson";
	 }
	 */
	
}
