package com.nihao.service;

import java.util.List;

import com.nihao.model.view.ResourceVO;

public interface ResourceServiceI {
	public List<ResourceVO> selectListByRoleId(Integer roleId);
}
