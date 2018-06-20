package com.persistence.manager.controller.reg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.persistence.base.controller.BaseController;
import com.persistence.base.exception.BaseException;
import com.persistence.base.tool.kit.StringUtil;
import com.persistence.manager.entity.User;
import com.persistence.manager.service.UserService;

@Controller
@RequestMapping("/manager/reg")
public class ManagerRegController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("/manager/reg/reg");
		try {
			
		} catch (Exception e) {
			this.error("操作失败");
		}
		return mav;
	}
	
	@RequestMapping("/doReg")
	public ModelAndView doReg(User user){
		ModelAndView mav = new ModelAndView("redirect:/manager/login/index");
		try {
			if(StringUtil.isNull(user.getUserid())){
				throw new BaseException("账号不能为空");
			}
			if(StringUtil.isNull(user.getPassword())){
				throw new BaseException("密码不能为空");
			}
			User bean = userService.getUserByUserid(user.getUserid());
			if(bean != null){
				throw new BaseException("该账号已被注册");
			}
			userService.reg(user);
			this.success("注册成功");
		}catch (BaseException e) {
			this.error(e.getMessage());
			mav.setViewName("/manager/reg/reg");
		} catch (Exception e) {
			this.error("注册失败");
			e.printStackTrace();
			mav.setViewName("/manager/reg/reg");
		}
		return mav;
	}
}
