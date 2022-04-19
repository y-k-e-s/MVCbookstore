package org.seledynowapowieka.simpleMVC.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.seledynowapowieka.simpleMVC.config.SecurityConfig;
import org.seledynowapowieka.simpleMVC.dao.RoleDao;
import org.seledynowapowieka.simpleMVC.dao.UserDao;
import org.seledynowapowieka.simpleMVC.entities.Role;
import org.seledynowapowieka.simpleMVC.entities.User;
import org.seledynowapowieka.simpleMVC.entities.ModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(){
		if(passwordEncoder == null) {
			passwordEncoder = SecurityConfig.passwordEncoder();
		}
	}
	


	@Override
	@Transactional
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(ModelUser modelUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(modelUser.getUserName());
		user.setPassword(passwordEncoder.encode(modelUser.getPassword()));
		user.setFirstName(modelUser.getFirstName());
		user.setLastName(modelUser.getLastName());
		user.setEmail(modelUser.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_CLIENT")));

		 // save user in the database
		userDao.save(user);
		
		
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
