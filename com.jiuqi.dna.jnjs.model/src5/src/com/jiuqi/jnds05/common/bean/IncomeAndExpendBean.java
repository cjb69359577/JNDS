package com.jiuqi.jnds05.common.bean;

public class IncomeAndExpendBean {
	private String mounth;// 月份
	private double income;// 收入
	private double expend;// 支出
	private double goabroadcost;// 因公出国费用
	private double officialreceptcost;// 公务接待费用
	private double officialcarcost;// 公务车购置及运行费
	public String getMounth() {
		return mounth;
	}
	public void setMounth(String mounth) {
		this.mounth = mounth;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getExpend() {
		return expend;
	}
	public void setExpend(double expend) {
		this.expend = expend;
	}
	public double getGoabroadcost() {
		return goabroadcost;
	}
	public void setGoabroadcost(double goabroadcost) {
		this.goabroadcost = goabroadcost;
	}
	public double getOfficialreceptcost() {
		return officialreceptcost;
	}
	public void setOfficialreceptcost(double officialreceptcost) {
		this.officialreceptcost = officialreceptcost;
	}
	public double getOfficialcarcost() {
		return officialcarcost;
	}
	public void setOfficialcarcost(double officialcarcost) {
		this.officialcarcost = officialcarcost;
	}
}
