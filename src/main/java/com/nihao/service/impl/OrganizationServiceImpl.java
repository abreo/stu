package com.nihao.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nihao.dao.OrganizationMapper;
import com.nihao.model.Organization;
import com.nihao.model.view.OrganizationVO;
import com.nihao.service.OrganizationServiceI;
import com.nihao.util.ZuZhuangUtil;

@Service
public class OrganizationServiceImpl implements OrganizationServiceI{
	
	@Autowired
	private OrganizationMapper organizationMapper;

	@Override
	public List<OrganizationVO> selectListByUserId(Integer userId) {
//		List<Organization> list=organizationMapper.selectListByUserId(userId);
//		Iterator<Organization> it=list.iterator();
//		List<OrganizationVO> voList=new ArrayList<>();
//		while(it.hasNext()){
//			Organization or=it.next();
//			if(or.getParentid()==null){
//				voList.add(new OrganizationVO(or));
//				it.remove();
//			}
//		}
//		for(OrganizationVO vo:voList){
//			ZuZhuangUtil.zuZhuang(list, vo, OrganizationVO.class);
//		}
//		return voList;
		List<Organization> list=organizationMapper.selectListByUserId(userId);
		Iterator<Organization> it=list.iterator();
		List<OrganizationVO> voList=new ArrayList<>();
		while(it.hasNext()){
			voList.add(new OrganizationVO(it.next()));
		}
		Collections.sort(voList);
		return voList;
	}

}
