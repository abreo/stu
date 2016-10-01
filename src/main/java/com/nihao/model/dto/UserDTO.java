package com.nihao.model.dto;

import java.util.Date;

import com.nihao.model.Organization;

public class UserDTO {
	private Integer id;
	private String loginname;
	private String username;
	private String gender;
	private String birthday;
	private String phonenum;
	private String email;
	private String photo;
	private Date cdatetime;
	private Date udatetime;
	private Organization organization;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getCdatetime() {
		return cdatetime;
	}
	public void setCdatetime(Date cdatetime) {
		this.cdatetime = cdatetime;
	}
	public Date getUdatetime() {
		return udatetime;
	}
	public void setUdatetime(Date udatetime) {
		this.udatetime = udatetime;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
