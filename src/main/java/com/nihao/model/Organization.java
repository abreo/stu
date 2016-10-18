package com.nihao.model;

import java.io.Serializable;
import java.util.Date;

public class Organization implements Serializable{

	private Integer id;

    private String organizationname;

    private String iconcls;

    private String description;

    private Integer seq;
    
    private Integer parentid;

    private Date cdatetime;

    private Date udatetime;

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
        this.organizationname = organizationname == null ? null : organizationname.trim();
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
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
}