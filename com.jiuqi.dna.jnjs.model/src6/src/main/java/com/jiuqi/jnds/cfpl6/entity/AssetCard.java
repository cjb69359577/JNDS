package com.jiuqi.jnds.cfpl6.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

/**
 * 卡片表
 * @author xiongkang
 *
 */
@Entity
@Table(name="gams_assetcard")
public class AssetCard {
	@Id
	@Type(type = "uuid-binary")
	@Column(name = "recid", length = 16)
	private UUID recid;
	
	@Version
	@Column(name = "recver")
	private Long recver;
	
	@Column(name = "billcode")
	private String billcode;
	
	@Column(name = "objectid")
	private UUID objectid;
	
	@Column(name = "orgunit")
	private UUID orgunit;
	
	@Column(name = "jiaz")
	private Double jiaz;
	
	@Column(name = "leijzj")
	private Double leijzj;
	
	@Column(name = "jingz")
	private Double jingz;
	
	@Column(name = "shul")
	private Integer shul;
	
	@Column(name = "mianj")
	private Double mianj;
	
	@Column(name = "zymj")
	private Double zymj;
	
	@Column(name = "xzmj")
	private Double xzmj;
	
	@Column(name = "czjmj")
	private Double czjmj;
	
	@Column(name = "dczmj")
	private Double dczmj;
	
	@Column(name = "zyjz")
	private Double zyjz;
	
	@Column(name = "xzjz")
	private Double xzjz;
	
	@Column(name = "czjjz")
	private Double czjjz;
	
	@Column(name = "dczjz")
	private Double dczjz;
	
	@Column(name = "jizrq")
	private Timestamp jizrq;
	
	@Column(name = "syzk")
	private UUID syzk;
	
	@Column(name = "zifl")
	private UUID zifl;
	
	@Column(name = "clyt")
	private UUID clyt;
	
	@Column(name = "cheph")
	private String cheph;
	
	@Column(name = "cardstate")
	private String cardstate;
	
	public UUID getRecid() {
		return recid;
	}

	public void setRecid(UUID recid) {
		this.recid = recid;
	}

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public UUID getObjectid() {
		return objectid;
	}

	public void setObjectid(UUID objectid) {
		this.objectid = objectid;
	}

	public UUID getOrgunit() {
		return orgunit;
	}

	public void setOrgunit(UUID orgunit) {
		this.orgunit = orgunit;
	}

	public Double getJiaz() {
		return jiaz;
	}

	public void setJiaz(Double jiaz) {
		this.jiaz = jiaz;
	}

	public Double getLeijzj() {
		return leijzj;
	}

	public void setLeijzj(Double leijzj) {
		this.leijzj = leijzj;
	}

	public Double getJingz() {
		return jingz;
	}

	public void setJingz(Double jingz) {
		this.jingz = jingz;
	}

	public Integer getShul() {
		return shul;
	}

	public void setShul(Integer shul) {
		this.shul = shul;
	}

	public Double getMianj() {
		return mianj;
	}

	public void setMianj(Double mianj) {
		this.mianj = mianj;
	}

	public Double getZymj() {
		return zymj;
	}

	public void setZymj(Double zymj) {
		this.zymj = zymj;
	}

	public Double getXzmj() {
		return xzmj;
	}

	public void setXzmj(Double xzmj) {
		this.xzmj = xzmj;
	}

	public Double getCzjmj() {
		return czjmj;
	}

	public void setCzjmj(Double czjmj) {
		this.czjmj = czjmj;
	}

	public Double getDczmj() {
		return dczmj;
	}

	public void setDczmj(Double dczmj) {
		this.dczmj = dczmj;
	}

	public Double getZyjz() {
		return zyjz;
	}

	public void setZyjz(Double zyjz) {
		this.zyjz = zyjz;
	}

	public Double getXzjz() {
		return xzjz;
	}

	public void setXzjz(Double xzjz) {
		this.xzjz = xzjz;
	}

	public Double getCzjjz() {
		return czjjz;
	}

	public void setCzjjz(Double czjjz) {
		this.czjjz = czjjz;
	}

	public Double getDczjz() {
		return dczjz;
	}

	public void setDczjz(Double dczjz) {
		this.dczjz = dczjz;
	}

	public Timestamp getJizrq() {
		return jizrq;
	}

	public void setJizrq(Timestamp jizrq) {
		this.jizrq = jizrq;
	}

	public UUID getSyzk() {
		return syzk;
	}

	public void setSyzk(UUID syzk) {
		this.syzk = syzk;
	}

	public UUID getZifl() {
		return zifl;
	}

	public void setZifl(UUID zifl) {
		this.zifl = zifl;
	}

	public UUID getClyt() {
		return clyt;
	}

	public void setClyt(UUID clyt) {
		this.clyt = clyt;
	}

	public String getCheph() {
		return cheph;
	}

	public void setCheph(String cheph) {
		this.cheph = cheph;
	}

	public String getCardstate() {
		return cardstate;
	}

	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}
	
}
