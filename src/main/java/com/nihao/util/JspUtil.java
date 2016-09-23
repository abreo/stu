package com.nihao.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nihao.model.view.ResourceVO;
import com.nihao.model.view.UserVO;

public class JspUtil{
	public static String getResources(HttpServletRequest request){
		UserVO userVo=(UserVO)request.getSession().getAttribute("SESSIONINFO");
		StringBuffer sb=new StringBuffer("");
		if(userVo!=null&&userVo.getResources()!=null&&userVo.getResources().size()>0){
			List<ResourceVO> list=userVo.getResources();
			pro(sb,list,request.getContextPath());
		}
		
		return sb.toString();
	}
	private static void pro(StringBuffer sb,List<ResourceVO> list,String contextPath){
		sb.append("<li>");
		
		for(ResourceVO vo:list){
			if(vo.getRtype()==2){
				continue;
			}
			List<ResourceVO> li=vo.getChildren();
			String icon="fa-file-text-o";
			String url="javascript:void(0);";
			String aClass="";
			if(vo.getIconcls()!=null){
				icon=vo.getIconcls();
			}
			if(vo.getUrl()!=null){
				url=contextPath+vo.getUrl();
				aClass="J_menuItem";
			}
			boolean b=false;
			for(ResourceVO v:li){
				if(v.getRtype()==1){
					b=true;
					break;
				}
			}
			if(b){
				sb.append("<a class=\""+aClass+"\" href=\""+url+"\"><i class=\"fa "+icon+"\"></i><span class=\"nav-label\">"+vo.getResourcename()+"</span><span class=\"fa arrow\"></span></a>");
				sb.append("<ul class=\"nav nav-second-level\">");
				pro(sb,li,contextPath);
				sb.append("</ul>");
			}
			else{
				sb.append("<a class=\""+aClass+"\" href=\""+url+"\"><i class=\"fa "+icon+"\"></i><span class=\"nav-label\">"+vo.getResourcename()+"</span></a>");
			}
		}
		
		sb.append("</li>");
	}
}
