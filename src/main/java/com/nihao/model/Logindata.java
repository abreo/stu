package com.nihao.model;

import java.io.Serializable;
import java.util.Date;

public class Logindata implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6505861223664630618L;

	private Integer id;

    private String loginname;

    private String ltype;

    private Date cdatetime;

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
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getLtype() {
        return ltype;
    }

    public void setLtype(String ltype) {
        this.ltype = ltype == null ? null : ltype.trim();
    }

    public Date getCdatetime() {
        return cdatetime;
    }

    public void setCdatetime(Date cdatetime) {
        this.cdatetime = cdatetime;
    }
}