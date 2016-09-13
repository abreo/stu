package com.nihao.service;

import java.util.List;

import com.nihao.model.view.ResourceVO;

public interface ResourceServiceI {
	/**
	 * 根据角色id获取资源
	 * @param roleId
	 * @return
	 */
	List<ResourceVO> selectListByRoleId(Integer roleId);
	
	/**
	 * 根据用户id获取资源
	 * @param userId
	 * @return
	 */
	List<ResourceVO> selectListByUserId(Integer userId);
}
