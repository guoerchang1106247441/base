package com.persistence.manager.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.persistence.core.util.SqlSessionFactoryUtil;
import com.persistence.manager.entity.User;

@Repository
public class UserDao {
	
	public List<User> findUserByType(String type){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		List<User> list = new ArrayList<User>();
		try {
			String sql = "com.persistence.manager.mapper.UserMapper.list";
			list = session.selectList(sql,type);
			session.commit();
		}finally{
			session.close();
		}
		return list;
	}
	
	public void addUser(User user){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		try {
			String sql = "com.persistence.manager.mapper.UserMapper.add";
			session.insert(sql,user);
			session.commit();
		}finally{
			session.close();
		}
	}
	
	public void reg(User user){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		try {
			String sql = "com.persistence.manager.mapper.UserMapper.reg";
			session.insert(sql,user);
			session.commit();
		}finally{
			session.close();
		}
	}
	
	public User findUserByUserid(String userid){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		User bean = new User();
		try {
			String sql = "com.persistence.manager.mapper.UserMapper.findUserByUserid";
			bean = session.selectOne(sql,userid);
			session.commit();
		}finally{
			session.close();
		}
		return bean;
	}
	
	public User getUserById(int id){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		User bean = new User();
		try {
			String sql = "com.persistence.manager.mapper.UserMapper.getUser";
			bean = session.selectOne(sql,id);
			session.commit();
		}finally{
			session.close();
		}
		return bean;
	}
	
	public User getUserByUserid(String userid){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		User bean = new User();
		try {
			String sql = "com.persistence.manager.mapper.UserMapper.getUserByUserid";
			bean = session.selectOne(sql,userid);
			session.commit();
		}finally{
			session.close();
		}
		return bean;
	}
	
	public void updateUser(User user){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		try {
			String sql = "com.persistence.manager.mapper.UserMapper.update";
			session.update(sql,user);
			session.commit();
		}finally{
			session.close();
		}
	}
}
