package com.csis.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.UserAccount;

@Controller
@SessionAttributes("mainPage")
@RequestMapping("/mainPage")
public class MainPageController {

	@Autowired
	UserAccountDaoImpl userDaoImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	 public String showMainPage(ModelMap model,HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map=((Map<String, Object>) RequestContextUtils.getInputFlashMap(request));
		UserAccount user = (UserAccount) map.get("account");
		model.addAttribute("name", user.getFirstname());
		model.addAttribute("mainPage", user);
		return "mainPage";
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	 public String mainPage(@ModelAttribute("mainPage")UserAccount user, BindingResult result, ModelMap model,RedirectAttributes attr) {
		if (result.hasErrors()) {
		 return "mainPage";
		}
		
		attr.addFlashAttribute("account", user);
		return "redirect:editPerson";
		 
	
	 }
}
