package com.nihao.dao;

import java.util.List;

import com.nihao.model.Role;

public interface RoleMapper {
    List<Role> selectListByUserId(Integer userId);
}