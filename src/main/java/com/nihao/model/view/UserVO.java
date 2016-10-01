package com.nihao.model.view;

import java.util.List;

import com.nihao.model.User;

public class UserVO {
	
	private User info;
	
	private String ip;
	
	private String userAgent;
	
	private List<RoleVO> roles;
	
	private OrganizationVO organization;
	
	private List<ResourceVO> resources;

	public User getInfo() {
		return info;
	}

	public void setInfo(User info) {
		this.info = info;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public List<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}

	public OrganizationVO getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationVO organization) {
		this.organization = organization;
	}

	public List<ResourceVO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceVO> resources) {
		this.resources = resources;
	}
	
}
