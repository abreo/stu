package com.nihao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 *  基础DAO接口
 *  
 * @author nihao
 *
 * @param <T>
 */
public interface BaseDao<T>{
	/**
	 * 新增
	 * @param xml
	 * @param t
	 * @return
	 */
	int save(String xml,T t);
	
	/**
	 * 根据主键删除
	 * @param xml
	 * @param key
	 * @return
	 */
	int deleteByPrimaryKey(String xml,Object key);
	
	/**
	 * 修改
	 * @param xml
	 * @param t
	 * @return
	 */
	int update(String xml,T t);
	
	/**
	 * 根据主键查找
	 * @param xml
	 * @param key
	 * @return
	 */
	T selectByPrimaryKey(String xml,Object key);
	
	/**
	 * 分页查询
	 * @param xml
	 * @param param
	 * @param rowBounds
	 * @return
	 */
	List<T> selectListByParamPagenation(String xml,Map param,RowBounds rowBounds);
	
	/**
	 * 根据条件查询条目数
	 * @param xml
	 * @param param
	 * @return
	 */
	Long countByParam(String xml,Map param);
}
