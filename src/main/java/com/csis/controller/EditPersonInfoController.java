package com.csis.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.UserAccount;

public class EditPersonInfoController {
	@Autowired
	UserAccountDaoImpl userDaoImpl;
		
	@RequestMapping(method = RequestMethod.GET)
		public String newProfile(ModelMap model, HttpServletRequest request) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map=((Map<String, Object>) RequestContextUtils.getInputFlashMap(request));
			UserAccount user = (UserAccount) map.get("account");
			model.addAttribute("editPerson", user);
			return "editPerson";
	}
		
	@RequestMapping(method = RequestMethod.POST)
		public String saveProfile(UserAccount user, BindingResult result, ModelMap model) {
			if (result.hasErrors()) {
				return "editPerson";
			}
			userDaoImpl.registerUser(user);
			model.addAttribute("messages", "Account successfully created");
			
		 return "redirect:login";
	}
		
}
