package com.persistence.manager.controller.menber;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.persistence.base.controller.BaseController;
import com.persistence.manager.entity.User;
import com.persistence.manager.service.UserService;


@Controller
@RequestMapping("/manager/menber")
public class ManagerMenberController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("info")
	public ModelAndView info(){
		ModelAndView mav = new ModelAndView("/manager/menber/info");
		User u = this.getUser();
		User user = userService.getUserByUserid(u.getUserid());
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("editUser")
	public ModelAndView editUser(User user,@RequestParam("fileImg") CommonsMultipartFile fileImg){
		ModelAndView mav = new ModelAndView("/manager/menber/info");
		if(fileImg != null){
			upload(fileImg);
		}
		userService.updateUser(user);
		mav.addObject("user", user);
		return mav;
	} 
	
}
