package com.nihao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.nihao.model.Role;
import com.nihao.model.dto.UserDTO;
import com.nihao.model.view.ControllerVO;
import com.nihao.model.view.JSONResult;
import com.nihao.model.view.PageResultForBootstrap;
import com.nihao.model.view.RoleVO;
import com.nihao.model.view.User2RoleVO;
import com.nihao.model.view.UserVO;
import com.nihao.service.RoleServiceI;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleServiceI roleService;
	@Autowired 
    private CommonDao commonDao;
	
	@RequestMapping(value = "/security/getUserRoles.ajax",method= RequestMethod.POST)
	@ResponseBody
	public String getRolesByUserId(HttpServletRequest request){
		List<RoleVO> list=roleService.selectListByUserId(Integer.parseInt(request.getParameter("userId")));
		List<RoleVO> sessionList=((UserVO)request.getSession().getAttribute("SESSIONINFO")).getRoles();
		Set<RoleVO> set=new HashSet<>(list);
		Set<RoleVO> ownSet=new HashSet<>(sessionList);
		ownSet.removeAll(set);
		JSONResult jr=new JSONResult();
		jr.setCode(200);
		jr.setMessage("成功");
		Map<String,Set<RoleVO>> map=new HashMap<String, Set<RoleVO>>(ownSet.size()+set.size());
		map.put("left", ownSet);
		map.put("right", set);
		jr.setData(map);
		return JSON.toJSONString(jr);
	}
	
	@RequestMapping(value = "/security/authorize.ajax",produces="text/html;charset=UTF-8",method= RequestMethod.POST)
	@ResponseBody
	public String authorize(@RequestBody User2RoleVO userRoleVO){
		roleService.updateUser2Role(userRoleVO.getUserId(),userRoleVO.getRoleIds());
		JSONResult jr=new JSONResult();
		jr.setCode(200);
		jr.setMessage("成功");
		return JSON.toJSONString(jr);
	}

	@RequestMapping(value = "/security/list.ajax",produces="text/html;charset=UTF-8",method= RequestMethod.POST)
	@ResponseBody
	public String list(@RequestBody ControllerVO vo,HttpSession session){
		Integer userId=((UserVO)session.getAttribute("SESSIONINFO")).getInfo().getId();
		vo.getParam().put("userid", userId);
		Long l=commonDao.countByParam("com.nihao.dao.RoleMapper.selectCount", vo.getParam());
		PageResultForBootstrap bf=new PageResultForBootstrap();
		bf.setTotal(l);
		if(l>0){
			RowBounds rb=new RowBounds((vo.getPageNumber()-1)*vo.getPageSize(), vo.getPageSize());
			List<UserDTO> list=commonDao.selectListByParamPagenation("com.nihao.dao.RoleMapper.selectListByUserIdPagination", vo.getParam(), rb);
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
		Role role=(Role) commonDao.selectByPrimaryKey("com.nihao.dao.RoleMapper.selectRoleById", id);
		jr.setData(role);
		return JSON.toJSONString(jr,SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
	}
}
