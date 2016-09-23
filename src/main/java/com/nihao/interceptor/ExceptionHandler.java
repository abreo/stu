package com.nihao.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler implements HandlerExceptionResolver {
	private Logger logger= LoggerFactory.getLogger(ExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("\r\n请求路径:"+httpServletRequest.getServletPath()
                +",类:"+((HandlerMethod)o).getBean().getClass().getName()
                +",方法:"+((HandlerMethod)o).getMethod().getName()
                +",错误信息:"+e.getMessage(), e);
        return new ModelAndView("exception");
    }
}
