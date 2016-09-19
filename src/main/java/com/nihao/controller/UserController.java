package com.nihao.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nihao.dao.BaseDao;
import com.nihao.dao.impl.CommonDao;
import com.nihao.model.User;
import com.nihao.model.view.JSONResult;
import com.nihao.model.view.UserVO;
import com.nihao.service.UserServiceI;

@Controller
@RequestMapping("")
public class UserController {
	@Autowired
	private UserServiceI userService;
	@Autowired 
    private CommonDao commonDao;

	@RequestMapping(value = "/main")
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String loginname = request.getParameter("loginname");
		String pwd = request.getParameter("pwd");
		UserVO vo = userService.login(loginname, pwd);
		if (vo == null) {
			response.sendRedirect("index.html");
			return null;
		} else {
			request.getSession().setAttribute("SESSIONINFO", vo);
			return "main";
		}
	}
	
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("SESSIONINFO");
		response.sendRedirect("index.html");
	}
	
	@RequestMapping(value="/list",produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String list(HttpServletRequest request){
		String sort=request.getParameter("sortName");
		String order=request.getParameter("sortOrder");
		Map map=new HashMap<>();
		map.put("order", order);
		map.put("sort", sort);
		Integer limit=Integer.parseInt(request.getParameter("pageSize"));
		Integer offset=Integer.parseInt(request.getParameter("pageNumber"));
		RowBounds rb=new RowBounds(offset, limit);
		List<User> list=commonDao.selectListByParamPagenation("com.nihao.dao.UserMapper.selectUserList", map, rb);
		Map m=new HashMap<>();
		m.put("total", 12);
		m.put("rows", list);
		return JSON.toJSONString(m);
	}
}
