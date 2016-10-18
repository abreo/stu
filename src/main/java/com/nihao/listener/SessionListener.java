package com.nihao.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.nihao.model.enums.LogTypeEnum;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nihao.dao.impl.CommonDao;
import com.nihao.model.Logindata;
import com.nihao.model.view.SessionInfo;

public class SessionListener implements HttpSessionAttributeListener{
	private CommonDao commonDao;

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		if(arg0.getName().equals("SESSIONINFO")){
			SessionInfo vo=(SessionInfo)arg0.getValue();
			Logindata logindata=new Logindata();
			logindata.setLoginname(vo.getInfo().getLoginname());
			logindata.setCdatetime(new Date());
			logindata.setLtype(LogTypeEnum.ON_LINE.getValue());
			logindata.setIp(vo.getIp());
			logindata.setUseragent(vo.getUserAgent());
			if(commonDao==null)
				commonDao=WebApplicationContextUtils.getWebApplicationContext(arg0.getSession().getServletContext()).getBean(CommonDao.class);
			commonDao.save("com.nihao.dao.LogindataMapper.insertOne", logindata);
			arg0.getSession().getServletContext().setAttribute(logindata.getLoginname(), arg0.getSession());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		if(arg0.getName().equals("SESSIONINFO")){
			SessionInfo vo=(SessionInfo)arg0.getValue();
			Logindata logindata=new Logindata();
			logindata.setLoginname(vo.getInfo().getLoginname());
			logindata.setCdatetime(new Date());
			logindata.setLtype(LogTypeEnum.OFF_LINE.getValue());
			logindata.setIp(vo.getIp());
			logindata.setUseragent(vo.getUserAgent());
			if(commonDao==null)
				commonDao=WebApplicationContextUtils.getWebApplicationContext(arg0.getSession().getServletContext()).getBean(CommonDao.class);
			commonDao.save("com.nihao.dao.LogindataMapper.insertOne", logindata);
			arg0.getSession().getServletContext().removeAttribute(logindata.getLoginname());
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
	}

}
