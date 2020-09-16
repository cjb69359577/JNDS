package com.jiuqi.jnds05.common.bean;

import java.util.List;

public class ResultShowAssetsBean<T> {
	
	private String code;
	
	private List<T> successinfo;
	
	private String errorinfo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public List<T> getSuccessinfo() {
		return successinfo;
	}

	public void setSuccessinfo(List<T> successinfo) {
		this.successinfo = successinfo;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}
	
	

}
