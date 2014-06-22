package com.mpoliako.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mpoliako.dao.UserDao;
import com.mpoliako.model.User;

@Controller
public class UserController {
	
	public static final Logger LOG = Logger.getLogger(UserController.class);
	
	private UserDao userDao;

	@Inject
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping(value="/register/", method = GET)
	public String register(Map<String, Object> model) {
		
		LOG.info("Prepare to show register view");
		model.put("user", new User());
		LOG.info("return addUser view");

		return "addUser";
	}
	
	@RequestMapping(value="/add_user/", method = POST)
	public String addUser(@Valid User user, BindingResult bindingResult) {
		
		LOG.info("Prepare to add user, user = " + user);
		
		if (userDao.getUser(user.getEmail())!=null) {
			LOG.warn("Request is invalid. Such user already exists");
			bindingResult.rejectValue("email", "Duplicate.user.email");
			return "addUser";
		}	
		
		if(bindingResult.hasErrors()) {
			LOG.warn("Request is invalid");
			return "addUser";
		}		
		
		userDao.addUser(user);
		
		LOG.info("User added. Return addUserSuccess view");
		return "addUserSuccess";
	}
	
}
