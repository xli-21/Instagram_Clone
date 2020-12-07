package com.csis.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csis.dao.ImageDaoImpl;
import com.csis.dao.PageContentDaoImpl;
import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.Follow;
import com.csis.model.Image;
import com.csis.model.UserAccount;

@Controller
public class MainPageController {

	@Autowired
	UserAccountDaoImpl userDaoImpl;
	
	@Autowired
	ImageDaoImpl imageDaoImpl;
	
	@Autowired
	PageContentDaoImpl pageContentDaoImpl;
	

	@RequestMapping(value = "mainPage", method = RequestMethod.GET)
	 public String showMainPage(ModelMap model,HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		
		model.addAttribute("name", user.getFirstname());
		model.addAttribute("mainPage", user);
		
		
		
		List<Follow> followingList = pageContentDaoImpl.followingList(user.getUsername());	
		if(followingList.size()>0) {
			List<Image> showList = new ArrayList<Image>();		
			for (Follow temp : followingList) {
				List<Image> imageList = imageDaoImpl.loadImage(temp.getHostusername());
				if(imageList.size()>0) {
					Image tempImage = imageList.get(imageList.size()-1);
					showList.add(tempImage);
				}
	        }
			showList.sort(Comparator.comparing(Image::getUploadDate).reversed());
			model.addAttribute("showList", showList);
		}
		
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
