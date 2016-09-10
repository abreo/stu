package com.nihao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nihao.model.view.UserVO;
import com.nihao.service.ResourceServiceI;
import com.nihao.service.UserServiceI;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceI userService;
	@Autowired
	private ResourceServiceI resourceService;
	
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public String login(HttpServletRequest request){
		String loginname=request.getParameter("loginname");
		String pwd=request.getParameter("pwd");
		UserVO user=userService.login(loginname, pwd);
		return "main";
	}
}
