package com.nihao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.nihao.dao.TestMapper;
import com.nihao.model.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class TestController {
	@Resource
	private TestMapper testMapper;
	@RequestMapping("/insert.ajax")
	public void insert(HttpServletRequest request){
		Test test=new Test();
		test.setId(request.getParameter("id"));
		test.setName(request.getParameter("name"));
		int i=testMapper.insert(test);
	}
	@RequestMapping("/delete.ajax")
	public void delete(HttpServletRequest request){
		int i=testMapper.deleteById(request.getParameter("id"));
	}
	@RequestMapping("/select.ajax")
	public void select(HttpServletRequest request){
		Test test=testMapper.selectById(request.getParameter("id"));
	}

	@RequestMapping("/update.ajax")
	public void update(HttpServletRequest request){
		Test test=new Test();
		test.setId(request.getParameter("id"));
		test.setName(request.getParameter("name"));
		int i=testMapper.update(test);
	}




	@RequestMapping(value="/test",produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String ttt(String sortName,String sortOrder){
		List<Map> list=new ArrayList<>();
	    for(int i=0;i<10;i++){
	    	Map map=new HashMap();
	    	map.put("id", i);
	    	map.put("name","name"+i);
	    	map.put("price", Math.random());
	    	list.add(map);
	    }
		return JSON.toJSONString(list).toString();
	}
}
