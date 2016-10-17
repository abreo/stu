package com.nihao.model.view;

import java.util.Date;
import java.util.List;

import com.nihao.model.Resource;

public class ResourceVO implements Comparable<ResourceVO>{
	
	private Integer id;

    private String resourcename;

    private String iconcls;

    private String description;

    private String url;

    private Integer seq;

    private Integer rtype;
    
    private List<ResourceVO> children;

    private Date cdatetime;

    private Date udatetime;
    
    private Integer parentid;
    
    public ResourceVO(Resource re) {
    	this.id=re.getId();
    	this.resourcename=re.getResourcename();
    	this.iconcls=re.getIconcls();
    	this.description=re.getDescription();
    	this.url=re.getUrl();
    	this.seq=re.getSeq();
    	this.rtype=re.getRtype();
    	this.cdatetime=re.getCdatetime();
    	this.udatetime=re.getUdatetime();
    	this.parentid=re.getParentid();
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public ResourceVO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public List<ResourceVO> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceVO> children) {
		this.children = children;
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

	@Override
	public int compareTo(ResourceVO o) {
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
		if(obj instanceof ResourceVO){
			ResourceVO resourceVO=(ResourceVO)obj;
			if(resourceVO.getId().equals(this.id)){
				return true;
			}
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
