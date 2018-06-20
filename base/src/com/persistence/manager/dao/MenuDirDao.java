package com.persistence.manager.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.persistence.core.util.SqlSessionFactoryUtil;
import com.persistence.manager.entity.MenuDir;
import com.persistence.manager.entity.User;

@Repository
public class MenuDirDao {
	
	/**
	 * 查询所有菜单目录
	 * @return
	 */
	public List<MenuDir> findList(){
		List<MenuDir> list = new ArrayList<MenuDir>();
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		try {
			String sql = "com.persistence.manager.mapper.MenuDirMapper.list";
			list = session.selectList(sql);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public MenuDir getMenuDirById(int id){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		MenuDir bean = new MenuDir();
		try {
			String sql = "com.persistence.manager.mapper.MenuDirMapper.getMenuDirById";
			bean = session.selectOne(sql,id);
			session.commit();
		}finally{
			session.close();
		}
		return bean;
	}
}
