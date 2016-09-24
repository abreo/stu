package com.nihao.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nihao.dao.impl.CommonDao;
import com.nihao.model.User;
import com.nihao.model.view.ControllerVO;
import com.nihao.model.view.PageResultForBootstrap;
import com.nihao.model.view.UserVO;
import com.nihao.service.UserServiceI;

@Controller
public class UserController {
	@Autowired
	private UserServiceI userService;
	@Autowired 
    private CommonDao commonDao;

	@RequestMapping(value = "/main",method= RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String loginname = request.getParameter("loginname");
		String pwd = request.getParameter("pwd");
		UserVO vo = userService.login(loginname, pwd);
		if (vo == null) {
			request.setAttribute("MES", "用户名或密码错误");
			return "index";
		} else {
			vo.setIp(request.getRemoteAddr());
			vo.setUserAgent(request.getHeader("User-Agent"));
			request.getSession().setAttribute("SESSIONINFO", vo);
			return "main";
		}
	}
	
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		response.sendRedirect("/stu");
	}
	
	@RequestMapping(value="/security/user/list",produces="text/html;charset=UTF-8",method= RequestMethod.POST)
	@ResponseBody
	public String list(@RequestBody ControllerVO vo){
		Long l=commonDao.countByParam("com.nihao.dao.UserMapper.selectCount", vo.getParam());
		PageResultForBootstrap bf=new PageResultForBootstrap();
		bf.setTotal(l);
		RowBounds rb=new RowBounds((vo.getPageNumber()-1)*vo.getPageSize(), vo.getPageSize());
		List<User> list=commonDao.selectListByParamPagenation("com.nihao.dao.UserMapper.selectUserList", vo.getParam(), rb);
		bf.setRows(list);
		return JSON.toJSONString(bf);
	}
	
}
