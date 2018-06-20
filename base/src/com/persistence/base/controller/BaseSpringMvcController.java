package com.persistence.base.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseSpringMvcController {
	
	protected HttpServletRequest request;  
	protected HttpServletResponse response;  
	protected HttpSession session; 
	
	@ModelAttribute  
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
		this.request = request;  
		this.response = response;  
		this.session = request.getSession();  
	}
	
	protected void outString(String str){
		try{
			PrintWriter out = response.getWriter();
			String jsoncallback = getParameter("jsoncallback");
			
			if ((jsoncallback != null) && (!jsoncallback.equals(""))) {
				out.write(jsoncallback + "(" + str + ")");
			} else {
				out.write(str);
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	protected String getParameter(String name){
		return (String)request.getAttribute(name);
	}
}
