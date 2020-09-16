package com.jiuqi.jnds.cfpl6.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

/**
 * 科目信息
 * @author xiongkang
 *
 */
@Entity
@Table(name="md_accountsubject")
public class MDAccountSubject {
	@Id
	@Type(type = "uuid-binary")
	@Column(name = "recid", length = 16)
	private UUID recid;
	
	@Version
	@Column(name = "recver")
	private Long recver;
	
	@Column(name = "objectid")
	private UUID objectid;
	
	@Column(name = "acctyear")
	private Integer acctyear;
	
	@Column(name = "stdcode")
	private String stdcode;
	
	@Column(name = "stdname")
	private String stdname;

	@Column(name = "parents")
	private byte[] parents;
	
	@Column(name = "sortorder")
	private Double sortorder;

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

	public UUID getObjectid() {
		return objectid;
	}

	public void setObjectid(UUID objectid) {
		this.objectid = objectid;
	}

	public Integer getAcctyear() {
		return acctyear;
	}

	public void setAcctyear(Integer acctyear) {
		this.acctyear = acctyear;
	}

	public String getStdcode() {
		return stdcode;
	}

	public void setStdcode(String stdcode) {
		this.stdcode = stdcode;
	}

	public String getStdname() {
		return stdname;
	}

	public void setStdname(String stdname) {
		this.stdname = stdname;
	}

	public byte[] getParents() {
		return parents;
	}

	public void setParents(byte[] parents) {
		this.parents = parents;
	}

	public Double getSortorder() {
		return sortorder;
	}

	public void setSortorder(Double sortorder) {
		this.sortorder = sortorder;
	}
	
}
