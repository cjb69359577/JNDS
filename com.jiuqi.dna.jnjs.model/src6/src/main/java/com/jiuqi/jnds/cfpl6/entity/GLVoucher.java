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
 * 凭证主表
 * @author xiongkang
 *
 */
@Entity
@Table(name="gl_voucher")
public class GLVoucher {
	@Id
	@Type(type = "uuid-binary")
	@Column(name = "recid", length = 16)
	private UUID recid;
	
	@Version
	@Column(name = "recver")
	private Long recver;
	
	/**
	 * 凭证编号
	 */
	@Column(name = "vchrnum")
	private Integer vchrnum;
	
	/**
	 * 凭证年度
	 */
	@Column(name = "acctyear")
	private Integer acctyear;
	
	/**
	 * 凭证期间
	 */
	@Column(name = "acctperiod")
	private Integer acctperiod;
	
	/**
	 * 单位ID
	 */
	@Column(name = "unitid")
	private UUID unitid;
	
	/**
	 * 凭证日期
	 */
	@Column(name = "createdate")
	private Timestamp createdate;
	
	/**
	 * 创建日期
	 */
	@Column(name = "createtime")
	private Timestamp createtime;

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

	public Integer getVchrnum() {
		return vchrnum;
	}

	public void setVchrnum(Integer vchrnum) {
		this.vchrnum = vchrnum;
	}

	public Integer getAcctyear() {
		return acctyear;
	}

	public void setAcctyear(Integer acctyear) {
		this.acctyear = acctyear;
	}

	public Integer getAcctperiod() {
		return acctperiod;
	}

	public void setAcctperiod(Integer acctperiod) {
		this.acctperiod = acctperiod;
	}

	public UUID getUnitid() {
		return unitid;
	}

	public void setUnitid(UUID unitid) {
		this.unitid = unitid;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
}
