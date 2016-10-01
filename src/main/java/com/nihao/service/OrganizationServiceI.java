package com.nihao.service;

import com.nihao.model.view.OrganizationVO;

public interface OrganizationServiceI {
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	OrganizationVO selectFullById(Integer id);
}
