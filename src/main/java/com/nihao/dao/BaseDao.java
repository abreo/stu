package com.nihao.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T>{
	/*增*/
	int save(String xml,T t);
	/*删*/
	int deleteByPrimaryKey(String xml,Object key);
	/*改*/
	int update(String xml,T t);
	/*查*/
	T selectByPrimaryKey(String xml,Object key);
	List<T> selectListByParam(String xml,Map param);
	Long countByParam(String xml,Map param);
}
