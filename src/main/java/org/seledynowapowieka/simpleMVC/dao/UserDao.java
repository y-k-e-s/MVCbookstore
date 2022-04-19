package org.seledynowapowieka.simpleMVC.dao;

import org.seledynowapowieka.simpleMVC.entities.User;

public interface UserDao {
	public void save(User user);
	
	public User findByUserName(String userName);
}
