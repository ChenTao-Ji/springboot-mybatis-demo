package com.example.demo.user.service;

import java.util.List;

import com.example.demo.user.model.User;

public interface UserService {

	public int addUser(User user);
	
	public List<User> findAllUser(int pageNum, int pageSize);
	
}
