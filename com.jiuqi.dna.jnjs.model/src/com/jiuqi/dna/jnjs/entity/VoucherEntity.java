package com.jiuqi.dna.jnjs.entity;

import java.sql.Date;

import com.jiuqi.dna.core.type.GUID;

public class VoucherEntity {
  
	  private GUID vrecid;//凭证recid
	  private int vchrnum;//凭证号
	  private int acctyear;//会计年
	  private int acctperiod;//会计月
	  private GUID unitid;//单位RECID
	  private String unitName;//单位名称
	  private Date createdate;//创建时间
	  private double debit;//借方
	  private double credit;//贷方
	  private GUID  subjectid;//科目
	  private GUID  asscombid;//辅助组合
	  private GUID  projectid;//项目
	  private GUID expendfuncclassid;//支出功能 
	  private GUID expendeconclassid;//支出经济
	public GUID getVrecid() {
		return vrecid;
	}
	public void setVrecid(GUID vrecid) {
		this.vrecid = vrecid;
	}
	public int getVchrnum() {
		return vchrnum;
	}
	public void setVchrnum(int vchrnum) {
		this.vchrnum = vchrnum;
	}
	public int getAcctyear() {
		return acctyear;
	}
	public void setAcctyear(int acctyear) {
		this.acctyear = acctyear;
	}
	public int getAcctperiod() {
		return acctperiod;
	}
	public void setAcctperiod(int acctperiod) {
		this.acctperiod = acctperiod;
	}
	public GUID getUnitid() {
		return unitid;
	}
	public void setUnitid(GUID unitid) {
		this.unitid = unitid;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public GUID getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(GUID subjectid) {
		this.subjectid = subjectid;
	}
	public GUID getAsscombid() {
		return asscombid;
	}
	public void setAsscombid(GUID asscombid) {
		this.asscombid = asscombid;
	}
	public GUID getProjectid() {
		return projectid;
	}
	public void setProjectid(GUID projectid) {
		this.projectid = projectid;
	}
	public GUID getExpendfuncclassid() {
		return expendfuncclassid;
	}
	public void setExpendfuncclassid(GUID expendfuncclassid) {
		this.expendfuncclassid = expendfuncclassid;
	}
	public GUID getExpendeconclassid() {
		return expendeconclassid;
	}
	public void setExpendeconclassid(GUID expendeconclassid) {
		this.expendeconclassid = expendeconclassid;
	}
	  
}
