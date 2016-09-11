package com.nihao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nihao.model.view.ResourceVO;
import com.nihao.model.view.RoleVO;
import com.nihao.model.view.UserVO;

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
		UserVO vo=(UserVO)request.getSession().getAttribute("INFO");
		if(vo!=null){
			for(RoleVO role:vo.getRoles()){
				for(ResourceVO re:role.getResources()){
					if(path.equals(re.getUrl())){
						logger.info("SUCCESS");
						return true;
					}
				}
			}
			logger.info("NO_SECURITY");
			request.setAttribute("message", "没有权限："+path);
			request.getRequestDispatcher(outsideOfficeHoursPage).forward(request, response);
			return false;
		}
		else{
			logger.info("NO_SESSION");
			request.setAttribute("message", "没有登录或登录已超时，请重新登录");
			request.getRequestDispatcher(outsideOfficeHoursPage).forward(request, response);
			return false;
		}
	}

}
