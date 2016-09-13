package com.nihao.dao;

import java.util.List;

import com.nihao.model.Resource;


public interface ResourceMapper {
	/**
	 * 根据角色id查询资源
	 * @param roleId
	 * @return
	 */
	List<Resource> selectListByRoleId(Integer roleId);
	
	/**
	 *  根据用户id查询资源
	 * @param userId
	 * @return
	 */
	List<Resource> selectListByUserId(Integer userId);
}