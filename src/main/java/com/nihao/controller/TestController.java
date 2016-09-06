package com.nihao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nihao.service.TestServiceI;

@Controller
public class TestController {
	
	@Autowired  
	private TestServiceI testService;
	
	@RequestMapping(value="/getTestById",produces="text/html;charset=UTF-8" )
	@ResponseBody
	private String getTestById(){
		String json=testService.getTestById(2);
		return json;
	}
}
