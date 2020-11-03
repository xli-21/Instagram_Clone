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

@Controller
@RequestMapping("/editPerson")
public class EditPersonInfoController {
	@Autowired
	UserAccountDaoImpl userDaoImpl;
		
	@RequestMapping(method = RequestMethod.GET)
		public String newProfile(ModelMap model, HttpServletRequest request) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map=((Map<String, Object>) RequestContextUtils.getInputFlashMap(request));
			UserAccount user = (UserAccount) map.get("account");
			model.addAttribute("editPerson", user);
			model.addAttribute("username", user.getUsername());
			model.addAttribute("firstname", user.getFirstname());
			model.addAttribute("lastname", user.getLastname());
			model.addAttribute("email", user.getEmail());
			return "editPerson";
	}
		
	@RequestMapping(method = RequestMethod.POST)
		public String saveProfile(UserAccount user, BindingResult result, ModelMap model) {
			if (result.hasErrors()) {
				return "editPerson";
			}
			userDaoImpl.editUser(user);
			model.addAttribute("messages", "Account successfully edited");
			
		 return "redirect:login";
	}
		
}
