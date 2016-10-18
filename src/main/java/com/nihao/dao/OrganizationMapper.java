package com.nihao.dao;

import java.util.List;

import com.nihao.model.Organization;

public interface OrganizationMapper {
	Organization selectById(Integer id);
	List<Organization> selectByParentId(Integer parentId);
}