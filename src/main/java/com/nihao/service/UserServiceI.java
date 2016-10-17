package com.nihao.service;

import com.nihao.model.User;
import com.nihao.model.view.UserInfoVO;
import com.nihao.model.view.UserVO;

public interface UserServiceI {
	UserVO login(String loginname,String pwd);
	Integer update(UserInfoVO vo);
	Integer deleteById(Integer id);
	Integer updateOrganizationById(User user);
}
