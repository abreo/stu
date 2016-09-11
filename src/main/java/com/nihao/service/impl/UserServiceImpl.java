package com.nihao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nihao.dao.UserMapper;
import com.nihao.model.User;
import com.nihao.model.view.OrganizationVO;
import com.nihao.model.view.RoleVO;
import com.nihao.model.view.UserVO;
import com.nihao.service.OrganizationServiceI;
import com.nihao.service.RoleServiceI;
import com.nihao.service.UserServiceI;
import com.nihao.util.MD5Util;

@Service
public class UserServiceImpl implements UserServiceI {
	@Autowired
	private UserMapper userMapper;


	@Override
	public UserVO login(String loginname, String pwd) {
		Map<String,Object> map=new HashMap<>();
		map.put("loginname", loginname);
		map.put("pwd", MD5Util.md5(pwd));
		User user=userMapper.selectOneByLoginnameAndPwd(map);
		UserVO vo=new UserVO();
		vo.setInfo(user);
		return vo;
	}

}
