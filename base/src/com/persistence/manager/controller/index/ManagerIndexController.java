package com.persistence.manager.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.persistence.base.controller.BaseController;


@Controller
@RequestMapping("/manager/main")
public class ManagerIndexController extends BaseController{
	
	@RequestMapping("/index")
	public ModelAndView index(String userid){
		ModelAndView mav = new ModelAndView("/manager/index/main");
		return mav;
	}
}
