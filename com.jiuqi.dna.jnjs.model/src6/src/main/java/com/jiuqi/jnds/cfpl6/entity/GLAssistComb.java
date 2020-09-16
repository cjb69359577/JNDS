package com.jiuqi.jnds.cfpl6.entity;

import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * 辅助组合表
 * @author xiongkang
 *
 */
@Entity
@Table(name="gl_assistcomb")
// @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GLAssistComb {
	@Id
	@Type(type = "uuid-binary")
	@Column(name = "recid", length = 16)
    // @GeneratedValue(generator = "jpa-uuid")
	private UUID recid;
	
	@Version
	@Column(name = "recver")
	private Long recver;
	
	@Column(name = "acctyear")
	private Integer acctyear;
	
	@Column(name = "projectid")
	private UUID projectid;
	
	@Column(name = "expendfuncclassid")
	private UUID expendfuncclassid;
	
	@Column(name = "expendeconclassid")
	private UUID expendeconclassid;

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

	public Integer getAcctyear() {
		return acctyear;
	}

	public void setAcctyear(Integer acctyear) {
		this.acctyear = acctyear;
	}

	public UUID getProjectid() {
		return projectid;
	}

	public void setProjectid(UUID projectid) {
		this.projectid = projectid;
	}

	public UUID getExpendfuncclassid() {
		return expendfuncclassid;
	}

	public void setExpendfuncclassid(UUID expendfuncclassid) {
		this.expendfuncclassid = expendfuncclassid;
	}

	public UUID getExpendeconclassid() {
		return expendeconclassid;
	}

	public void setExpendeconclassid(UUID expendeconclassid) {
		this.expendeconclassid = expendeconclassid;
	}
	
}
