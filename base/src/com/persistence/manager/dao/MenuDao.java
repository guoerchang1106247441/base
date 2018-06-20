package com.persistence.manager.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.persistence.core.util.SqlSessionFactoryUtil;
import com.persistence.manager.entity.Menu;
import com.persistence.manager.entity.User;

@Repository
public class MenuDao {
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Menu> findList(){
		List<Menu> list = new ArrayList<Menu>();
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		try {
			String sql = "com.persistence.manager.mapper.MenuMapper.list";
			list = session.selectList(sql);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}
	
	/**
	 * 根据菜单目录id查询
	 * @return
	 */
	public List<Menu> findListByMenuDirId(int menuDirId){
		List<Menu> list = new ArrayList<Menu>();
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		try {
			String sql = "com.persistence.manager.mapper.MenuMapper.listByMenuDirId";
			list = session.selectList(sql, menuDirId);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}
}
