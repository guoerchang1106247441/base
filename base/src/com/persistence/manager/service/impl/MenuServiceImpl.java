package com.persistence.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistence.base.tool.Constains;
import com.persistence.base.tool.kit.StringUtil;
import com.persistence.manager.dao.MenuDao;
import com.persistence.manager.dao.MenuDirDao;
import com.persistence.manager.entity.Menu;
import com.persistence.manager.entity.MenuDir;
import com.persistence.manager.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private MenuDirDao menuDirDao;

	@Override
	public List<Menu> getMenuUser(String usertype) {
		
		if(Constains.USER_ADMIN_TYPE.equals(usertype)){//判断是否是超级管理员，超级管理员拥有所有权限
			
		}
		return null;
	}

	@Override
	public List<MenuDir> getMenuDir() {
		List<MenuDir> dirLs = menuDirDao.findList();
		for (MenuDir bean : dirLs) {
			List<Menu> menuLs = menuDao.findListByMenuDirId(bean.getId());
			bean.setMenuLs(menuLs);
		}
		return dirLs;
	}

}
