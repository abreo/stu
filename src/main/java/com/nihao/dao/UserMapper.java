package com.nihao.dao;

import java.util.Map;

import com.nihao.model.User;
import com.nihao.model.view.UserInfoVO;

public interface UserMapper {
    User selectOneByLoginnameAndPwd(Map map);
    Integer update(UserInfoVO vo);
    Integer deleteById(Integer id);
    Integer updateOrganizationById(User user);
}