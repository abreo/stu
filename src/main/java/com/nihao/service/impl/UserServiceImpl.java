package com.nihao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nihao.dao.UserMapper;
import com.nihao.model.User;
import com.nihao.service.UserServiceI;
import com.nihao.util.MD5Util;

@Service
public class UserServiceImpl implements UserServiceI {
	@Autowired
	private UserMapper userMapper;

	@Override
	public Map login(String loginname, String pwd) {
		Map map=new HashMap<>();
		map.put("loginname", loginname);
		map.put("pwd", MD5Util.md5(pwd));
		User user=userMapper.selectOneByLoginnameAndPwd(map);
		map.clear();
		if(user==null){
			map.put("success",false);
			return map;
		}
		map.put("success",true);
		map.put("user", user);
		return map;
	}

}
