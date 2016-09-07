package com.nihao.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nihao.model.User;
import com.nihao.service.UserServiceI;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceI userService;
	
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public String login(HttpServletRequest request){
		String loginname=request.getParameter("loginname");
		String pwd=request.getParameter("pwd");
		Map map=userService.login(loginname, pwd);
		if((Boolean)map.get("success")){
			System.out.println(((User)map.get("user")).getGender());
		}else{
			System.out.println("----------");
		}
		return "main";
	}
}
