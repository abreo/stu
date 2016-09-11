package com.nihao.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nihao.model.view.OrganizationVO;
import com.nihao.model.view.ResourceVO;
import com.nihao.model.view.RoleVO;
import com.nihao.model.view.UserVO;
import com.nihao.service.OrganizationServiceI;
import com.nihao.service.ResourceServiceI;
import com.nihao.service.RoleServiceI;
import com.nihao.service.UserServiceI;
import com.nihao.util.InsertSort;
import com.nihao.util.ZuZhuangUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceI userService;
	@Autowired
	private ResourceServiceI resourceService;
	@Autowired
	private RoleServiceI roleService;
	@Autowired
	private OrganizationServiceI organizationService;
	
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public String login(HttpServletRequest request){
		String loginname=request.getParameter("loginname");
		String pwd=request.getParameter("pwd");
		UserVO vo=userService.login(loginname, pwd);
		if (vo.getInfo() != null) {
			List<RoleVO> roleList = roleService.selectListByUserId(vo.getInfo().getId());
			vo.setRoles(roleList);
			List<OrganizationVO> orList = organizationService.selectListByUserId(vo.getInfo().getId());
			vo.setOrganizations(orList);
			Set<ResourceVO> set=new HashSet<>();
			List<ResourceVO> list=new ArrayList<>();
			for(RoleVO role:roleList){
				List<ResourceVO> li=role.getResources();
				set.addAll(li);
			}
			list.addAll(set);
			InsertSort.sort(list);
			vo.setResources(list);
			request.getSession().setAttribute("INFO", vo);
		}
		return "main";
	}
}
