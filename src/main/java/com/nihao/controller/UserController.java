package com.nihao.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nihao.model.view.UserVO;
import com.nihao.service.UserServiceI;

@Controller
public class UserController {
	@Autowired
	private UserServiceI userService;

	@RequestMapping(value = "/main")
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String loginname = request.getParameter("loginname");
		String pwd = request.getParameter("pwd");
		UserVO vo = userService.login(loginname, pwd);
		if (vo == null) {
			response.sendRedirect(request.getContextPath() + "/index.html");
			return null;
		} else {
			request.getSession().setAttribute("SESSIONINFO", vo);
			return "main";
		}
	}
}
