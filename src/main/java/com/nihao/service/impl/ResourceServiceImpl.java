package com.nihao.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nihao.dao.ResourceMapper;
import com.nihao.model.Resource;
import com.nihao.model.view.ResourceVO;
import com.nihao.service.ResourceServiceI;
import com.nihao.util.ZuZhuangUtil;

@Service
public class ResourceServiceImpl implements ResourceServiceI {
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<ResourceVO> selectListByRoleId(Integer roleId) {
		List<Resource> list=resourceMapper.selectListByRoleId(roleId);
		Iterator<Resource> it=list.iterator();
		List<ResourceVO> voList=new ArrayList<>();
//		while(it.hasNext()){
//			Resource re=it.next();
//			if(re.getParentid()==null){
//				voList.add(new ResourceVO(re));
//				it.remove();
//			}
//		}
//		
//		for(ResourceVO vo:voList){
//			ZuZhuangUtil.zuZhuang(list, vo, ResourceVO.class);
//		}
		while(it.hasNext()){
			voList.add(new ResourceVO(it.next()));
		}
		return voList;
	}

}
