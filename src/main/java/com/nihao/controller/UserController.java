package com.nihao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nihao.dao.impl.CommonDao;
import com.nihao.model.Organization;
import com.nihao.model.User;
import com.nihao.model.dto.UserDTO;
import com.nihao.model.view.ControllerVO;
import com.nihao.model.view.JSONResult;
import com.nihao.model.view.PageResultForBootstrap;
import com.nihao.model.view.UserInfoVO;
import com.nihao.model.view.UserVO;
import com.nihao.service.UserServiceI;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceI userService;
	@Autowired 
    private CommonDao commonDao;

	@RequestMapping(value = "/login.ajax",method= RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String loginname = request.getParameter("loginname");
		String pwd = request.getParameter("pwd");
		UserVO vo = userService.login(loginname, pwd);
		JSONResult jsonResult=new JSONResult();
		if (vo == null) {
			jsonResult.setCode(403);
			jsonResult.setMessage("用户名或密码错误");
		} else {
			vo.setIp(request.getRemoteAddr());
			vo.setUserAgent(request.getHeader("User-Agent"));
			request.getSession().setAttribute("SESSIONINFO", vo);
			jsonResult.setCode(200);
			jsonResult.setMessage("登录成功");
		}
		return JSON.toJSONString(jsonResult);
	}
	
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		response.sendRedirect("/stu");
	}
	
	@RequestMapping(value="/security/list.ajax",produces="text/html;charset=UTF-8",method= RequestMethod.POST)
	@ResponseBody
	public String list(@RequestBody ControllerVO vo){
		Long l=commonDao.countByParam("com.nihao.dao.UserMapper.selectCount", vo.getParam());
		PageResultForBootstrap bf=new PageResultForBootstrap();
		bf.setTotal(l);
		if(l>0){
			RowBounds rb=new RowBounds((vo.getPageNumber()-1)*vo.getPageSize(), vo.getPageSize());
			List<UserDTO> list=commonDao.selectListByParamPagenation("com.nihao.dao.UserMapper.selectUserList", vo.getParam(), rb);
			if(list.isEmpty()&&vo.getPageNumber()>1){
				rb=new RowBounds((vo.getPageNumber()-2)*vo.getPageSize(), vo.getPageSize());
				list=commonDao.selectListByParamPagenation("com.nihao.dao.UserMapper.selectUserList", vo.getParam(), rb);
			}
			bf.setRows(list);
		}
		else{
			bf.setRows(new ArrayList<>(0));
		}
		bf.setCode(200);
		bf.setMessage("请求成功");
		return JSON.toJSONString(bf,SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="/security/info.ajax",produces="text/html;charset=UTF-8",method= RequestMethod.POST)
	@ResponseBody
	public String getUserInfoById(Integer id){
		JSONResult jr=new JSONResult(200, "请求成功"); 
		UserInfoVO user=(UserInfoVO) commonDao.selectByPrimaryKey("com.nihao.dao.UserMapper.selectUserById", id);
		jr.setData(user);
		return JSON.toJSONString(jr,SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping(value="/security/update.ajax",produces="text/html;charset=UTF-8",method= RequestMethod.POST)
	@ResponseBody
	public String updateUser(@RequestBody UserInfoVO vo){
		JSONResult jr=new JSONResult();
		if(vo.getId()==null){
			jr.setCode(406);
			jr.setMessage("缺少请求参数:ID");
			return JSON.toJSONString(jr);
		}
		else{
			Integer i=userService.update(vo);
			jr.setCode(200);
			if(i==1){
				jr.setMessage("修改成功");
			}
			else{
				jr.setMessage("修改失败，影响条目数:"+i);
			}
			return JSON.toJSONString(jr,SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
		}
	}
	
	@RequestMapping(value="/security/delete.ajax",produces="text/html;charset=UTF-8",method= RequestMethod.POST)
	@ResponseBody
	public String delete(Integer id){
		JSONResult jr=new JSONResult();
		int i=commonDao.deleteByPrimaryKey("com.nihao.dao.UserMapper.deleteById", id);
		if(i==1){
			jr.setCode(200);
			jr.setMessage("删除成功");
		}
		else{
			jr.setCode(500);
			jr.setMessage("删除失败");
		}
		return JSON.toJSONString(jr);
	}
	
}
