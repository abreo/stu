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

    private Integer ltype;

    private Date cdatetime;
    
    private String ip;
    
    private String useragent;

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

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

    public Integer getLtype() {
        return ltype;
    }

    public void setLtype(Integer ltype) {
        this.ltype=ltype;
    }

    public Date getCdatetime() {
        return cdatetime;
    }

    public void setCdatetime(Date cdatetime) {
        this.cdatetime = cdatetime;
    }
}