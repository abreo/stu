package com.nihao.service;

import java.util.List;

import com.nihao.model.Role;
import com.nihao.model.dto.User2RoleDTO;
import com.nihao.model.view.RoleVO;

public interface RoleServiceI {
	/**
	 * 根据用户id查询角色
	 * @param userId
	 * @return
	 */
	List<RoleVO> selectListByUserId(Integer userId);
	
	/**
	 * 用户授权
	 * @param userId
	 * @param roleIds
	 */
	void updateUser2Role(Integer userId,Integer[] roleIds);

	int update(Role role);
}
