package com.nihao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class Test {
	@RequestMapping("/qwe/doNotNeedSecurity_qq")
	public void qq(HttpServletRequest request){
		throw new RuntimeException("exception------------");
//		@SuppressWarnings("unused")
//		String Agent = request.getHeader("User-Agent"); 
//		StringTokenizer st = new StringTokenizer(Agent,";");
//		st.nextToken(); 
//		String userbrowser = st.nextToken(); 
//		String useros = st.nextToken(); 
//		String host=request.getRemoteHost();
//		String protocal=request.getProtocol();
//		String pathInfo=request.getPathInfo();
//		System.out.println("------------");
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
