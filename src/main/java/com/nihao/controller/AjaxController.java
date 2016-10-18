package com.nihao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nihao.model.view.JSONResult;
/**
 * AjaxController
 * @author nihao by eclipse
 *
 */
@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	@RequestMapping(value="/returnsession",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String noSession(){
		JSONResult jr=new JSONResult(403, "未登录，或登录已超时");
		return JSON.toJSONString(jr);
	}
	
	@RequestMapping(value="/returnsecurity",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String noSecurity(HttpServletRequest request){
		JSONResult jr=new JSONResult(403, "没有权限:"+request.getAttribute("path"));
		return JSON.toJSONString(jr);
	}
}
