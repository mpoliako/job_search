package com.mpoliako.dao;

import com.mpoliako.model.User;

public interface UserDao {
	
	public void addUser(User user);
	public User getUser(String userName);

}
