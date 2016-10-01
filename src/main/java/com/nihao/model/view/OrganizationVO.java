package com.nihao.model.view;

import java.util.Date;
import java.util.List;

import com.nihao.model.Organization;

public class OrganizationVO implements Comparable<OrganizationVO>{
	
	private Integer id;

    private String organizationname;

    private String iconcls;

    private String description;

    private Integer seq;

    private Date cdatetime;

    private Date udatetime;
    
    private List<OrganizationVO> children;
    
    private Integer parentid;

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public OrganizationVO(){}
    
    public OrganizationVO(Organization or){
    	this.id=or.getId();
    	this.organizationname=or.getOrganizationname();
    	this.iconcls=or.getIconcls();
    	this.description=or.getDescription();
    	this.seq=or.getSeq();
    	this.cdatetime=or.getCdatetime();
    	this.udatetime=or.getUdatetime();
    	this.parentid=or.getParentid();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
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

	public List<OrganizationVO> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationVO> children) {
		this.children = children;
	}

	@Override
	public int compareTo(OrganizationVO o) {
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
    
}
