package com.nihao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nihao.dao.OrganizationMapper;
import com.nihao.model.Organization;
import com.nihao.model.view.OrganizationVO;
import com.nihao.service.OrganizationServiceI;

@Service
public class OrganizationServiceImpl implements OrganizationServiceI{
	
	@Autowired
	private OrganizationMapper organizationMapper;

	/**
	 * 组织机构填充children
	 * @param vo
	 */
	private void fillIn(OrganizationVO vo){
		if(vo!=null&&vo.getId()!=null){
			List<Organization> list=organizationMapper.selectByParentId(vo.getId());
			List<OrganizationVO> children=new ArrayList<>(list.size());
			for(Organization po:list){
				OrganizationVO child=new OrganizationVO(po);
				fillIn(child);
				children.add(child);
			}
			vo.setChildren(children);
		}
	}

	@Override
	public OrganizationVO selectFullById(Integer id) {
		Organization organization=organizationMapper.selectById(id);
		OrganizationVO vo=new OrganizationVO(organization);
		fillIn(vo);
		return vo;
	}

//	@Override
//	public List<OrganizationVO> selectListByUserId(Integer userId) {
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
//			AssembleUtil.zuZhuang(list, vo, OrganizationVO.class);
//		}
//		return voList;
////		List<Organization> list=organizationMapper.selectListByUserId(userId);
////		Iterator<Organization> it=list.iterator();
////		List<OrganizationVO> voList=new ArrayList<>();
////		while(it.hasNext()){
////			voList.add(new OrganizationVO(it.next()));
////		}
////		Collections.sort(voList);
////		return voList;
//	}

}
