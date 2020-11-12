package com.csis.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csis.dao.ImageDaoImpl;
import com.csis.dao.UserAccountDaoImpl;
import com.csis.model.Comment;
import com.csis.model.Image;
import com.csis.model.UserAccount;

@Controller
public class PhotoLibraryController {

	
	@Autowired
	UserAccountDaoImpl userDaoImpl;
	@Autowired
	ImageDaoImpl imageDaoImpl;
	
	@RequestMapping(value = "photoLibrary", method = RequestMethod.GET)
	 public String showPhotoLibrary(@RequestParam(required = false)String messages, ModelMap model,HttpSession session) {
		model.addAttribute("messages",messages);
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		
		List<Image> images = imageDaoImpl.loadImage(user.getUsername());
		
		model.addAttribute("images", images);
		return "photoLibrary";
	 }
	
	@RequestMapping(value = "photoLibrary", method = RequestMethod.POST, params = "back")
	 public String photoLibraryToMain(ModelMap model,HttpSession session) {
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		model.addAttribute("mainPage", user);
		
		return "redirect:mainPage";
	 }
	
	@RequestMapping(value = "editPhoto", method = RequestMethod.GET)
	 public String photoDetails(@RequestParam(required = true) int id, ModelMap model,HttpSession session) {
		
		session.setAttribute("imageId",id);
		
		Image image = imageDaoImpl.loadImageById(id);
		List<Comment> comments = imageDaoImpl.loadComment(id);
		
		model.addAttribute("image", image);
		model.addAttribute("comments", comments);
		return "editPhoto";
	 }
	
	@RequestMapping(value = "editPhoto", method = RequestMethod.POST, params = "finish")
	 public String editPhotoPage(@RequestParam("desc") String description, ModelMap model,HttpSession session) {
		int id = (int) session.getAttribute("imageId");
		imageDaoImpl.editPhoto(description, id);
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		
		List<Image> images = imageDaoImpl.loadImage(user.getUsername());
		
		model.addAttribute("images", images);
		
		return "redirect:photoLibrary";
	 }
	
	@RequestMapping(value = "editPhoto", method = RequestMethod.POST, params = "delete")
	 public String deletePhoto(ModelMap model,HttpSession session) {
		int id = (int) session.getAttribute("imageId");
		imageDaoImpl.deletePhoto(id);
		
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		
		List<Image> images = imageDaoImpl.loadImage(user.getUsername());
		
		model.addAttribute("images", images);
		return "redirect:photoLibrary";
	 }
	
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	 public String uploadPage(ModelMap model,HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		
		model.addAttribute("upload", user);
		return "upload";
	 }
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String uploading(ModelMap model,HttpSession session, HttpServletRequest request, 
			@RequestParam("file") MultipartFile file, @RequestParam("desc") String description) throws IOException {
		
		UserAccount user1 = (UserAccount) session.getAttribute("user");
		
		String localPath="D:\\File\\";
	  
	    String fileName=null;
	    
		if(!file.isEmpty()){
			
			String uuid = UUID.randomUUID().toString().replaceAll("-","");
			
			String contentType=file.getContentType();
			
			String imageSuffix=contentType.substring(contentType.indexOf("/")+1);
			

			fileName = uuid+"." + imageSuffix;
	        
			file.transferTo(new File(localPath+fileName));
			
			Date date = new Date();  
			Timestamp timeStamp = new Timestamp(date.getTime());  
			Image newImage = new Image();
			newImage.setUsername(user1.getUsername());
			newImage.setFileLocation(localPath+fileName);
			newImage.setUploadDate(timeStamp);
			newImage.setDesc(description);
			imageDaoImpl.saveImage(newImage);
			model.addAttribute("messages", "Successful post");
			return "redirect:photoLibrary";
		}
		else {
			model.addAttribute("messages", "Post failed");
			return "upload";
		}		
	}
}
