package com.jiuqi.jnds05.common.bean;

import java.io.Serializable;

public class ProjectAndExpAndJjflEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jjfldm;//经济分类代码
	private String jjflmc;//经济分类名称
	private String gnfl;//功能分类
	private String xm;//项目
	private double zcje;//金额
	public String getJjfldm() {
		return jjfldm;
	}
	public void setJjfldm(String jjfldm) {
		this.jjfldm = jjfldm;
	}
	public String getJjflmc() {
		return jjflmc;
	}
	public void setJjflmc(String jjflmc) {
		this.jjflmc = jjflmc;
	}
	public String getGnfl() {
		return gnfl;
	}
	public void setGnfl(String gnfl) {
		this.gnfl = gnfl;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public double getZcje() {
		return zcje;
	}
	public void setZcje(double zcje) {
		this.zcje = zcje;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
