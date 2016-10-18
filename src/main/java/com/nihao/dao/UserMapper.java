package com.nihao.dao;

import org.apache.ibatis.annotations.Param;

import com.nihao.model.User;
import com.nihao.model.view.UserInfoVO;

public interface UserMapper {
    User selectOneByLoginnameAndPwd(@Param("loginname") String loginname,@Param("pwd") String pwd);
    Integer update(UserInfoVO vo);
    Integer deleteById(Integer id);
    Integer updateOrganizationById(User user);
}