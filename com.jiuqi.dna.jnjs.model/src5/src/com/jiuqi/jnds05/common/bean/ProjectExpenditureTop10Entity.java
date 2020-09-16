package com.jiuqi.jnds05.common.bean;

import java.io.Serializable;

public class ProjectExpenditureTop10Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String project;
	private double zcje;
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public double getZcje() {
		return zcje;
	}
	public void setZcje(double zcje) {
		this.zcje = zcje;
	}
	
}
