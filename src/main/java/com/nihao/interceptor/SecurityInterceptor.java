package com.nihao.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nihao.model.view.ResourceVO;
import com.nihao.model.view.SessionInfo;

public class SecurityInterceptor implements HandlerInterceptor{
	
	private Logger logger=LoggerFactory.getLogger(SecurityInterceptor.class);
	
	private String outsideOfficeHoursPage;

	public String getOutsideOfficeHoursPage() {
		return outsideOfficeHoursPage;
	}

	public void setOutsideOfficeHoursPage(String outsideOfficeHoursPage) {
		this.outsideOfficeHoursPage = outsideOfficeHoursPage;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		String path=request.getServletPath();
		logger.info("进入安全拦截器，请求地址："+path);
		SessionInfo vo=(SessionInfo)request.getSession().getAttribute("SESSIONINFO");
		if(vo!=null){
//			if(path.startsWith("/page")){
//				logger.info("页面不拦截");
//				return true;
//			}
			if(pro(vo.getResources(),path).equals("yes")){
				return true;
			}
			logger.info("NO_SECURITY");
			if(path.contains(".ajax")){
				request.setAttribute("path", path);
				request.getRequestDispatcher("/ajax/returnsecurity").forward(request, response);
				return false;
			}
			request.setAttribute("message", "没有权限："+path);
			request.getRequestDispatcher(outsideOfficeHoursPage).forward(request, response);
			return false;
		}
		else{
			logger.info("NO_SESSION");
			if(path.contains(".ajax")){
				request.getRequestDispatcher("/ajax/returnsession").forward(request, response);
				return false;
			}
			request.setAttribute("message", "没有登录或登录已超时，请重新登录");
			request.getRequestDispatcher(outsideOfficeHoursPage).forward(request, response);
			return false;
		}
	}
	
	private String pro(List<ResourceVO> list,String path){
		for(int i=0;i<list.size();i++){
			if(path.equals(list.get(i).getUrl())){
				logger.info("SUCCESS");
				return "yes";
			}
			else if(list.get(i).getChildren()!=null){
				String s = pro(list.get(i).getChildren(),path);
				if(s.equals("yes"))
					return "yes";
			}
		}
		return "no";
	}

}
