package com.nihao.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.nihao.dao.LogindataMapper;
import com.nihao.dao.TestMapper;
import com.nihao.model.Test;
import com.nihao.service.TestServiceI;

@Service
public class TestServiceImpl implements TestServiceI {
	
	@Autowired  
	private TestMapper testMapper;  
	@Autowired  
	private LogindataMapper logindataMapper; 


	@Override
	public String getTestById(Integer id) {
		Set<String> set=new HashSet<String>();  
		        Test test=testMapper.selectByPrimaryKey(id);
		        String s=JSON.toJSONString(test);
		        return JSON.toJSONString(s);

	}

}
