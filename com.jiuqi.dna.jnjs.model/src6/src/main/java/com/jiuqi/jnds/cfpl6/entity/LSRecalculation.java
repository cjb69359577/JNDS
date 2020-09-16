package com.jiuqi.jnds.cfpl6.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * 重算完成结果记录表
 * @author  houchenglong
 * @create  2020/9/8 21:39
 * @desc
 **/
@Entity
@Table(name="ls_recalculation")
public class LSRecalculation {
    @Id
    @Type(type = "uuid-binary")
    @Column(name = "id", length = 16)
    private UUID id;

    /**
     * 凭证编号
     */
    @Column(name = "glid")
    private String glid;

    /**
     * 单位名称
     */
    @Column(name = "unitcode")
    private String unitcode;

    /**
     * 单位名称
     */
    @Column(name = "unitname")
    private String unitname;

    /**
     * 凭证年度
     */
    @Column(name = "acctyear")
    private String acctyear;

    /**
     * 凭证期间
     */
    @Column(name = "acctperiod")
    private String acctperiod;

    /**
     * 重审时间
     */
    @Column(name = "time")
    private String time;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getAcctyear() {
        return acctyear;
    }

    public void setAcctyear(String acctyear) {
        this.acctyear = acctyear;
    }

    public String getAcctperiod() {
        return acctperiod;
    }

    public void setAcctperiod(String acctperiod) {
        this.acctperiod = acctperiod;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
