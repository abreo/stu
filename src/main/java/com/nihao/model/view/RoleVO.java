package com.nihao.model.view;

import java.util.Date;
import java.util.List;

import com.nihao.model.Role;

public class RoleVO implements Comparable<RoleVO>{

	private Integer id;

    private String rolename;

    private String iconcls;

    private String description;

    private Integer seq;

    private Date cdatetime;

    private Date udatetime;
    
    private List<ResourceVO> resources;
    
    public RoleVO(){}
    
    public RoleVO(Role r){
    	this.id=r.getId();
    	this.rolename=r.getRolename();
    	this.iconcls=r.getIconcls();
    	this.description=r.getDescription();
    	this.seq=r.getSeq();
    	this.cdatetime=r.getCdatetime();
    	this.udatetime=r.getUdatetime();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public List<ResourceVO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceVO> resources) {
		this.resources = resources;
	}

	@Override
	public int compareTo(RoleVO o) {
		if(this.getSeq()==null&&o.getSeq()!=null){
			return -1;
		}
		else if(this.getSeq()!=null&&o.getSeq()==null){
			return 1;
		}
		else{
			return this.getSeq()-o.getSeq();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RoleVO){
			RoleVO roleVO=(RoleVO)obj;
			if(roleVO.getId().equals(this.getId()))
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if(this.id!=null){
			return this.id.hashCode();
		}
		else{
			return super.hashCode();
		}
	}
}
