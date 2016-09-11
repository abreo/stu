package com.nihao.model.view;

import java.util.List;

import com.nihao.model.User;

public class UserVO {
	
	private User info;
	
	private String ip;
	
	private List<RoleVO> roles;
	
	private List<OrganizationVO> organizations;
	
	private List<ResourceVO> resources;

	public List<ResourceVO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceVO> resources) {
		this.resources = resources;
	}

	public User getInfo() {
		return info;
	}

	public void setInfo(User info) {
		this.info = info;
	}

	public List<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}

	public List<OrganizationVO> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<OrganizationVO> organizations) {
		this.organizations = organizations;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	

}
