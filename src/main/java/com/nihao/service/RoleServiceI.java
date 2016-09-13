package com.nihao.service;

import java.util.List;

import com.nihao.model.view.RoleVO;

public interface RoleServiceI {
	/**
	 * 根据用户id查询角色
	 * @param userId
	 * @return
	 */
	List<RoleVO> selectListByUserId(Integer userId);
}
