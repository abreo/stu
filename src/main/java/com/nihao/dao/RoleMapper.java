package com.nihao.dao;

import java.util.List;
import java.util.Map;

import com.nihao.model.Role;
import com.nihao.model.dto.User2RoleDTO;

public interface RoleMapper {
    List<Role> selectListByUserId(Integer userId);
    int deleteUser2RoleByUserId(Integer userId);
    int batchInsertUser2Role(List<User2RoleDTO> list);
    List<Role> selectListByUserIdPagination(Map map);
    int update(Role role);
}