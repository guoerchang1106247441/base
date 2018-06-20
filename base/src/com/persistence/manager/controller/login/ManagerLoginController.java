package com.persistence.manager.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.persistence.base.controller.BaseController;
import com.persistence.base.exception.BaseException;
import com.persistence.base.tool.Constains;
import com.persistence.base.tool.kit.StringUtil;
import com.persistence.manager.entity.Menu;
import com.persistence.manager.entity.MenuDir;
import com.persistence.manager.entity.User;
import com.persistence.manager.service.MenuService;
import com.persistence.manager.service.UserService;

@Controller
@RequestMapping("/manager/login")
public class ManagerLoginController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;

	@RequestMapping("/index")
	public String index(String url, Model model){
		//判断用户是否登录，如果已登录，则跳转到主页
		if (null != this.getUser()) {
			if(StringUtil.isNotNull(url)){
				return "redirect:" + getUrl(url);
			}else{
				return "redirect:/manager/main/index";
			}
        }
		if(StringUtil.isNotNull(url)){
			model.addAttribute("url", url);
		}
		return "/manager/login/login";
	}
	
	/**
	 * 获得url铭文
	 * @param url
	 * @return
	 */
	private String getUrl(String url) {
        if (StringUtil.isNull(url))
            return url;
        // 反序列化
        try {
            if (StringUtil.isNotNull(url)) {
                url = (String) this.unserializable(url);
            }
        } catch (Exception e) {
            
        }
        if (StringUtil.isNotNull(url)) {
            // URL解码，这样放到登录页面的url才正常
            url = StringUtil.decode(url);
            url = StringUtil.decode(url);
        }

        return url;
    }
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@RequestMapping("/doLogin")
	public ModelAndView doLogin(User user, String captcha){
		ModelAndView mav = new ModelAndView("redirect:/manager/login/index");
		try {
			if(StringUtil.isNull(user.getUserid())){
				throw new BaseException("请输入账号");
			}
			if(StringUtil.isNull(user.getPassword())){
				throw new BaseException("请输入密码");
			}
			if(StringUtil.isNull(captcha)){
				throw new BaseException("请输入验证码");
			}
			//获取验证码session
			Object key = session.getAttribute(Constains.LOGIN_RANDOM);
			if(key == null || !captcha.equals(key.toString())){
				throw new BaseException("验证码错误");
			}
			//获得页面的密码随机数
			Object random = session.getAttribute(Constains.PWD_RANDOM);
			if(random == null){
				random = "";
			}
			session.removeAttribute(Constains.PWD_RANDOM);
			//根据前台输入的用户名和密码去查询用户是否存在
			user = userService.login(user.getUserid(), user.getPassword(), random.toString());
			if(user == null){//登录失败，返回到登录页面
				throw new BaseException("账号或密码错误");
			}
			//登录成功
			//将用户信息存在session中
			request.getSession().setAttribute(Constains.USER_SESSION, user);
			//将用权限信息存在session中
			List<MenuDir> menuDirLs = menuService.getMenuDir();
			request.getSession().setAttribute(Constains.MENU_SESSION, menuDirLs);
			
		}catch (BaseException e) {
			this.error(e.getMessage());
			e.printStackTrace();
			return mav;
		} catch (Exception e) {
			this.error("登录失败");
			e.printStackTrace();
			return mav;
		}
		mav.setViewName("redirect:/manager/main/index");
		return mav;
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/extLogin")
	public String extLogin(HttpServletRequest request, HttpServletResponse response){
		try {
			//删除session中用户信息
			request.getSession().removeAttribute(Constains.USER_SESSION);
			//删除session中用户权限
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/manager/login/index";
	}
}
