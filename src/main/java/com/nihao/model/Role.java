package com.nihao.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 270456076875502410L;

	private Integer id;

    private String rolenname;

    private String iconcls;

    private String description;

    private Integer seq;

    private Date cdatetime;

    private Date udatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolenname() {
        return rolenname;
    }

    public void setRolenname(String rolenname) {
        this.rolenname = rolenname == null ? null : rolenname.trim();
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