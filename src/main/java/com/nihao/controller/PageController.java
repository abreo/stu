package com.nihao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	@RequestMapping("/{page}")
	public String to(@PathVariable String page){
		StringBuffer sb=new StringBuffer("");
		for(String ss:page.split("_")){
			sb.append(ss+"/");
		}
		return sb.toString().substring(0, sb.toString().lastIndexOf("/"));
	}
}
