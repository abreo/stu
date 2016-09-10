package com.nihao.dao;

import java.util.List;

import com.nihao.model.Organization;

public interface OrganizationMapper {
    List<Organization> selectListByUserId(Integer userId);
}