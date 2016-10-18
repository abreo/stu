package com.nihao.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nihao.dao.BaseDao;

/**
 * 通用DAO
 * @author nihao
 *
 */
@Repository
public class CommonDao extends SqlSessionDaoSupport implements BaseDao{

	@Override
	public int save(String xml, Object t) {
		return getSqlSession().insert(xml, t);
	}

	@Override
	public int deleteByPrimaryKey(String xml, Object key) {
		return getSqlSession().delete(xml, key);
	}

	@Override
	public int update(String xml, Object t) {
		return getSqlSession().update(xml, t);
	}

	@Override
	public Object selectByPrimaryKey(String xml, Object key) {
		return getSqlSession().selectOne(xml, key);
	}

	@Override
	public List selectListByParamPagenation(String xml, Map param, RowBounds rowBounds) {
		return getSqlSession().selectList(xml, param, rowBounds);
	}

	@Override
	public Long countByParam(String xml, Map param) {
		List<Long> list=getSqlSession().selectList(xml, param);
		return list.get(0);
	}

}
