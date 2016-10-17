package com.nihao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nihao.dao.UserMapper;
import com.nihao.model.User;
import com.nihao.model.view.OrganizationVO;
import com.nihao.model.view.ResourceVO;
import com.nihao.model.view.RoleVO;
import com.nihao.model.view.UserInfoVO;
import com.nihao.model.view.UserVO;
import com.nihao.service.OrganizationServiceI;
import com.nihao.service.ResourceServiceI;
import com.nihao.service.RoleServiceI;
import com.nihao.service.UserServiceI;
import com.nihao.util.MD5Util;

@Service
public class UserServiceImpl implements UserServiceI {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleServiceI roleService;
	@Autowired
	private OrganizationServiceI organizationService;
	@Autowired
	private ResourceServiceI resourceService;

	@Override
	public UserVO login(String loginname, String pwd) {
		Map<String,Object> map=new HashMap<>();
		map.put("loginname", loginname);
		map.put("pwd", MD5Util.md5(pwd));
		User user=userMapper.selectOneByLoginnameAndPwd(map);
		if(user==null)
			return null;
		UserVO vo=new UserVO();
		List<RoleVO> roles=roleService.selectListByUserId(user.getId());
		OrganizationVO organization=organizationService.selectFullById(user.getOrganizationid());
		List<ResourceVO> resources=resourceService.selectListByUserId(user.getId());
		vo.setOrganization(organization);
		vo.setResources(resources);
		vo.setRoles(roles);
		vo.setInfo(user);
		return vo;
	}

	@Override
	public Integer update(UserInfoVO vo) {
		return userMapper.update(vo);
	}

	@Override
	public Integer deleteById(Integer id) {
		return userMapper.deleteById(id);
	}

	@Override
	public Integer updateOrganizationById(User user) {
		return userMapper.updateOrganizationById(user);
	}

}
