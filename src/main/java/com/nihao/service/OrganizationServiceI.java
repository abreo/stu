package com.nihao.service;

import com.nihao.model.view.OrganizationVO;

public interface OrganizationServiceI {
	/**
	 * 根据id获取机构(包括机构子节点数据填充完整)
	 * @param id
	 * @return
	 */
	OrganizationVO selectFullById(Integer id);
}
