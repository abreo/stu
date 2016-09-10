package com.nihao.service;

import java.util.List;

import com.nihao.model.view.OrganizationVO;

public interface OrganizationServiceI {
	public List<OrganizationVO> selectListByUserId(Integer userId);
}
