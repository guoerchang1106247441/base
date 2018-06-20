package com.persistence.manager.service;

import java.util.List;

import com.persistence.manager.entity.Menu;
import com.persistence.manager.entity.MenuDir;
import com.persistence.manager.entity.User;

public interface MenuService {

	
	/**
	 * 获得用户的菜单
	 * @param usertype
	 * @return
	 */
	List<Menu> getMenuUser(String usertype);
	
	List<MenuDir> getMenuDir();
}
