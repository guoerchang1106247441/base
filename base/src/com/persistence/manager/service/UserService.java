package com.persistence.manager.service;

import java.util.List;

import com.persistence.manager.entity.User;

public interface UserService {

	void add(User user);
	
	User login(String userid, String username, String random);
	
	List<User> findUserByType(String type);
	
	User getUserById(int id);
	
	User getUserByUserid(String userid);
	
	void updateUser(User user);
	
	void reg(User user);
}
