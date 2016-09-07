package com.nihao.dao;

import java.util.Map;

import com.nihao.model.User;

public interface UserMapper {
    User selectOneByLoginnameAndPwd(Map map);
}