package com.nihao.service;

import java.util.List;

import com.nihao.model.view.RoleVO;

public interface RoleServiceI {
	public List<RoleVO> selectListByUserId(Integer userId);
}
