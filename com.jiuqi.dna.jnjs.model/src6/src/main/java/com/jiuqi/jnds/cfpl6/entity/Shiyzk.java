package com.jiuqi.jnds.cfpl6.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

/**
 * 使用状况
 * @author xiongkang
 *
 */
@Entity
@Table(name="gams_jc_syzk")
public class Shiyzk {
	@Id
	@Type(type = "uuid-binary")
	@Column(name = "recid", length = 16)
	private UUID recid;
	
	@Version
	@Column(name = "recver")
	private Long recver;
	
	@Column(name = "stdcode")
	private String stdcode;
	
	@Column(name = "stdname")
	private String stdname;

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
	
}
