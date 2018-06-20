package com.persistence.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistence.base.dao.MaxCodeDao;
import com.persistence.base.exception.BaseException;
import com.persistence.base.tool.kit.StringUtil;
import com.persistence.manager.dao.UserDao;
import com.persistence.manager.entity.User;
import com.persistence.manager.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private MaxCodeDao maxCodeDao;

	@Override
	public void add(User user) {
		userDao.addUser(user);
	}

	@Override
	public User login(String userid, String password, String random) {
		User user = userDao.findUserByUserid(userid);
		if(user == null){
			throw new BaseException("账号不存在");
		}
		String pass = StringUtil.getMd5(user.getPassword() + random);
		if(!pass.equals(password)){
			throw new BaseException("密码错误");
		}
		return user;
	}

	@Override
	public List<User> findUserByType(String type) {
		return userDao.findUserByType(type);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User getUserByUserid(String userid) {
		return userDao.getUserByUserid(userid);
	}

	@Override
	public void reg(User user) {
		user.setCtime(new Date());
		user.setPassword(StringUtil.getMd5(user.getPassword()));//密码用md5加密
		userDao.reg(user);
	}

}
