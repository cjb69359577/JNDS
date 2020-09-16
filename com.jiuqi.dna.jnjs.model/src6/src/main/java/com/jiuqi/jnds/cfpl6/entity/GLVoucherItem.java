package com.jiuqi.jnds.cfpl6.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

/**
 * 凭证子表
 * @author xiongkang
 *
 */
@Entity
@Table(name="gl_voucheritem")
public class GLVoucherItem {
	@Id
	@Type(type = "uuid-binary")
	@Column(name = "recid", length = 16)
	private UUID recid;
	
	@Version
	@Column(name = "recver")
	private Long recver;
	
	/**
	 * 凭证主表ID
	 */
	@Column(name = "vchrid")
	private UUID vchrid;
	
	/**
	 * 序号
	 */
	@Column(name = "itemorder")
	private Integer itemorder;
	
	/**
	 * 科目ID
	 */
	@Column(name = "subjectid")
	private UUID subjectid;
	
	/**
	 * 借方金额
	 */
	@Column(name = "debit")
	private Double debit;
	
	/**
	 * 贷方金额
	 */
	@Column(name = "credit")
	private Double credit;
	
	/**
	 * 辅助组合ID
	 */
	@Column(name = "asscombid")
	private UUID asscombid;

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

	public UUID getVchrid() {
		return vchrid;
	}

	public void setVchrid(UUID vchrid) {
		this.vchrid = vchrid;
	}

	public Integer getItemorder() {
		return itemorder;
	}

	public void setItemorder(Integer itemorder) {
		this.itemorder = itemorder;
	}

	public UUID getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(UUID subjectid) {
		this.subjectid = subjectid;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public UUID getAsscombid() {
		return asscombid;
	}

	public void setAsscombid(UUID asscombid) {
		this.asscombid = asscombid;
	}
	
}
