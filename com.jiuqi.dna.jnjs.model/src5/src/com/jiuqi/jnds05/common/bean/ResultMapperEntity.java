package com.jiuqi.jnds05.common.bean;

import java.io.Serializable;
import java.util.List;

public class ResultMapperEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private List<ProjectExpenditureDetailsEntity> successinfo;
	private String errorinfo;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ProjectExpenditureDetailsEntity> getSuccessinfo() {
		return successinfo;
	}
	public void setSuccessinfo(List<ProjectExpenditureDetailsEntity> successinfo) {
		this.successinfo = successinfo;
	}
	public String getErrorinfo() {
		return errorinfo;
	}
	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
