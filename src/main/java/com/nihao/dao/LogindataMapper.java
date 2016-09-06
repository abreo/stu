package com.nihao.dao;

import com.nihao.model.Logindata;

public interface LogindataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logindata record);

    int insertSelective(Logindata record);

    Logindata selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logindata record);

    int updateByPrimaryKey(Logindata record);
    
    long countAll();
}