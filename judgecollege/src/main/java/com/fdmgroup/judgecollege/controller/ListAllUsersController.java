package com.fdmgroup.judgecollege.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.judgecollege.dao.UserDao;
import com.fdmgroup.judgecollege.entity.User;

@Controller
public class ListAllUsersController {
	
	@Autowired
	@Qualifier(value = "userDAO")
	UserDao userDAO;
	
	public void setUserDao(UserDao userDAO) {
		this.userDAO = userDAO;
	}
	
	@RequestMapping("/users")
	public String listAllUsers(Model model){
		List<User> users = userDAO.listAll();
		model.addAttribute("usersList", users);
		return "users";
	}

}
