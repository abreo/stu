package com.nihao.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nihao.model.enums.ResourceTypeEnum;
import com.nihao.model.view.ResourceVO;
import com.nihao.model.view.SessionInfo;

public class JspUtil{
	/**
	 * 获取菜单
	 * @param request
	 * @return
	 */
	public static String getResources(HttpServletRequest request){
		SessionInfo sessionInfo =(SessionInfo)request.getSession().getAttribute("SESSIONINFO");
		StringBuffer sb=new StringBuffer("");
		if(sessionInfo !=null&& sessionInfo.getResources()!=null&& sessionInfo.getResources().size()>0){
			List<ResourceVO> list= sessionInfo.getResources();
			pro(sb,list,request.getContextPath());
		}
		
		return sb.toString();
	}
	private static void pro(StringBuffer sb,List<ResourceVO> list,String contextPath){
		sb.append("<li>");
		for(ResourceVO parent:list){
			if(parent.getRtype()!= ResourceTypeEnum.MAIN_PAGE.getValue()){
				continue;
			}
			List<ResourceVO> children=parent.getChildren();
			String icon="fa-file-text-o";
			String url="javascript:void(0);";
			String aClass="";
			if(parent.getIconcls()!=null){
				icon=parent.getIconcls();
			}
			if(parent.getUrl()!=null){
				url=contextPath+parent.getUrl();
				aClass="J_menuItem";
			}
			boolean b=false;
			for(ResourceVO child:children){
				if(child.getRtype()==ResourceTypeEnum.MAIN_PAGE.getValue()){
					b=true;
					break;
				}
			}
			if(b){
				sb.append("<a class=\""+aClass+"\" href=\""+url+"\"><i class=\"fa "+icon+"\"></i><span class=\"nav-label\">"+parent.getResourcename()+"</span><span class=\"fa arrow\"></span></a>");
				sb.append("<ul class=\"nav nav-second-level\">");
				pro(sb,children,contextPath);
				sb.append("</ul>");
			}
			else{
				sb.append("<a class=\""+aClass+"\" href=\""+url+"\"><i class=\"fa "+icon+"\"></i><span class=\"nav-label\">"+parent.getResourcename()+"</span></a>");
			}
		}
		sb.append("</li>");
	}
}
