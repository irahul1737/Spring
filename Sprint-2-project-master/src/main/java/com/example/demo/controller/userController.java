package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class userController {
	@Autowired
	UserService service;
	@GetMapping("/signup")
	public String signup(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	@RequestMapping(value="/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user) {
		User ouser=service.register(user.getName());
		if(Objects.nonNull(ouser)) {
			return "redirect:/signup";
		}
		else {
		service.save(user);
		return "login";
		}
	}
	@GetMapping("/login")
	public String login(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return "login";
	}

	@RequestMapping(value="/loginuser", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User user) {
		User ouser=service.login(user.getName(),user.getPassword());
		
		if(Objects.nonNull(ouser)) {
			return "redirect:/";
		}
		else {
			return "login";
		}
	}
	 
	}