package com.nihao.dao;

import java.util.List;

import com.nihao.model.Resource;


public interface ResourceMapper {
	List<Resource> selectListByRoleId(Integer roleId);
}