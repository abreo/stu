package com.nihao.service;

import java.util.List;

import com.nihao.model.view.OrganizationVO;

public interface OrganizationServiceI {
	/**
	 * 根据用户id获取机构
	 * @param userId
	 * @return
	 */
	List<OrganizationVO> selectListByUserId(Integer userId);
}
