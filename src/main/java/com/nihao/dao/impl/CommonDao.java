package com.nihao.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nihao.dao.BaseDao;

@Repository
public class CommonDao extends SqlSessionDaoSupport implements BaseDao{

	@Override
	public int save(String xml, Object t) {
		return getSqlSession().insert(xml, t);
	}

	@Override
	public int deleteByPrimaryKey(String xml, Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String xml, Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object selectByPrimaryKey(String xml, Object key) {
		return getSqlSession().selectOne(xml, key);
	}

	@Override
	public List selectListByParam(String xml, Map param) {
		return getSqlSession().selectList(xml, param);
	}

	@Override
	public Long countByParam(String xml, Map param) {
		
		return null;
	}

}
