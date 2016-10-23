package com.nihao.model.view;

import com.google.common.collect.Maps;

import java.util.Map;

public class ControllerVO {
	private Integer pageSize;
	private Integer pageNumber;
	private String sortName;
	private String sortOrder;
	private Map<String,Object> param;
	
	public Map<String, Object> getParam() {
		return param;
	}
	public void setParam(Map<String, Object> param) {
		if(this.param==null) this.param= Maps.newHashMapWithExpectedSize(7);
		this.param.putAll(param);
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
		if(this.param==null) this.param= Maps.newHashMapWithExpectedSize(7);
		param.put("sort",sortName);
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
		if(this.param==null) this.param= Maps.newHashMapWithExpectedSize(7);
		param.put("order",sortOrder);
	}
}
