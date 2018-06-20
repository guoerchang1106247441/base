package com.persistence.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.persistence.base.tool.Constains;
import com.persistence.base.tool.kit.StringUtil;


@Controller
@RequestMapping("/PubSelect")
public class PubSelectController extends BaseController{
	
	@RequestMapping("/getPwdRandom")
	public String getPwdRandom(){
		String pwdrandom = StringUtil.GUID();
		request.getSession().setAttribute(Constains.PWD_RANDOM, pwdrandom);
		outString(pwdrandom);
		return null;
	}
	
	@RequestMapping("/removeFlashSuccess")
	public void removeFlashSuccess(){
		session.removeAttribute(Constains.FLASH_SUCCESS_SUCCESS);
	}
	
	@RequestMapping("/removeFlashError")
	public void removeFlashError(){
		session.removeAttribute(Constains.FLASH_SUCCESS_ERROR);
	}
}
