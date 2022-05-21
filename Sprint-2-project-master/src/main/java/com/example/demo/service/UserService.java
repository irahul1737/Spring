package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.userRepository;
@Service
public class UserService {
	@Autowired
	userRepository res;
	
	public User login(String name,String password) {
		User user=res.findByNameAndPassword(name, password);
		return user;
	}
	public User register(String name) {
		User user =res.findByName(name);
		return user;
	}
	

	public void save(User user) {
		// TODO Auto-generated method stub
		res.save(user);
	}
	 

}