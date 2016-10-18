package com.nihao.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nihao.model.view.BootstrapTree;
import com.nihao.model.view.OrganizationVO;
import com.nihao.model.view.SessionInfo;

/**
 * 机构Controller
 * @author nihao by eclipse
 *
 */
@Controller
public class OrganizationController {
	
	private void fillIn(BootstrapTree tree,OrganizationVO vo,Set<Integer> ids){
		tree.setId(vo.getId());
		ids.add(vo.getId());
		tree.setSeq(vo.getSeq());
		tree.setText(vo.getOrganizationname());
		tree.setIcon(vo.getIconcls());
		for(OrganizationVO v:vo.getChildren()){
			BootstrapTree tr=new BootstrapTree();
			tr.setId(v.getId());
			ids.add(v.getId());
			tr.setSeq(v.getSeq());
			tr.setText(v.getOrganizationname());
			tr.setIcon(v.getIconcls());
			if(v.getChildren()!=null&&v.getChildren().size()>0){
				fillIn(tr,v,ids);
			}
			tree.addNode(tr);
		}
	}
	
	@RequestMapping(value="/organization/owntree.ajax",produces="text/html;charset=UTF-8",method= RequestMethod.POST)
	@ResponseBody
	public String ownTree(HttpServletRequest request){
		SessionInfo vo=(SessionInfo) request.getSession().getAttribute("SESSIONINFO");
		List<BootstrapTree> list=new ArrayList<>(0);
		Set<Integer> organizationIds=new HashSet<>(0);
		if(vo!=null&&vo.getOrganization()!=null){
			OrganizationVO organization=vo.getOrganization();
			for(OrganizationVO v:organization.getChildren()){
				BootstrapTree tree=new BootstrapTree();
				fillIn(tree,v,organizationIds);
				list.add(tree);
			}
		}
		Collections.sort(list,new Comparator<BootstrapTree>(){
			@Override
			public int compare(BootstrapTree o1, BootstrapTree o2) {
				return o1.getSeq()-o2.getSeq();
			}
		});
		Map<String,Object> map=new HashMap<>();
		map.put("organizations", list);
		map.put("ids", organizationIds);
		return JSON.toJSONString(map);
	}
}
