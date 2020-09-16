package com.jiuqi.jnds05.common.bean;
/**
 * 
 * @author sunyang
 *
 */
public class CarSituationIntf {
	//资产分类
	private String zcfl;
	//资产编号
	private String zcbh;
	//车牌号
	private String cph;
	//价值
	private String cvalue;
	//累计折旧
	private String ljzj;
	//净值
	private String jvalue;
	//使用情况
	private String syqk;
	//车辆用途
	private String clyt;
	public String getZcfl() {
		return zcfl;
	}
	public void setZcfl(String zcfl) {
		this.zcfl = zcfl;
	}
	public String getZcbh() {
		return zcbh;
	}
	public void setZcbh(String zcbh) {
		this.zcbh = zcbh;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public String getCvalue() {
		return cvalue;
	}
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}
	public String getLjzj() {
		return ljzj;
	}
	public void setLjzj(String ljzj) {
		this.ljzj = ljzj;
	}
	public String getJvalue() {
		return jvalue;
	}
	public void setJvalue(String jvalue) {
		this.jvalue = jvalue;
	}
	public String getSyqk() {
		return syqk;
	}
	public void setSyqk(String syqk) {
		this.syqk = syqk;
	}
	public String getClyt() {
		return clyt;
	}
	public void setClyt(String clyt) {
		this.clyt = clyt;
	}
	@Override
	public String toString() {
		return "CarSituationIntf [zcfl=" + zcfl + ", zcbh=" + zcbh + ", cph=" + cph + ", cvalue=" + cvalue + ", ljzj="
				+ ljzj + ", jvalue=" + jvalue + ", syqk=" + syqk + ", clyt=" + clyt + "]";
	}	
	
}
