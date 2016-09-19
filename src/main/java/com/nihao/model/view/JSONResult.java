package com.nihao.model.view;

public class JSONResult {
	private Integer code;
	private String message;
	private Object data;
	public JSONResult(){
		
	}
    public JSONResult(Integer code,String message){
		this.code=code;
		this.message=message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
