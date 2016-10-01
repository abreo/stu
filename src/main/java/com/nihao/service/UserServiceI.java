package com.nihao.service;

import com.nihao.model.view.UserInfoVO;
import com.nihao.model.view.UserVO;

public interface UserServiceI {
	public UserVO login(String loginname,String pwd);
	public Integer update(UserInfoVO vo);
}
