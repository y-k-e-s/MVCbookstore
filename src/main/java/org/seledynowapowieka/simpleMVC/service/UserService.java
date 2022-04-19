package org.seledynowapowieka.simpleMVC.service;

import org.seledynowapowieka.simpleMVC.entities.User;
import org.seledynowapowieka.simpleMVC.entities.ModelUser;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(ModelUser modelUser);
}