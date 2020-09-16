package com.jiuqi.jnds.cfpl6.util;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <h1>DataImportEntity</h1>
 *
 * <p>导入数据的实体类</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 10:41
 */
public class DataImportEntity implements Serializable {
    private static final long serialVersionUID = -1220546757432186581L;
    /**
     * 凭证标识
     */
    @ExcelProperty(index = 0)
    private String credentialId;
    /**
     * 凭证号
     */
    @ExcelProperty(index = 1)
    private String credentialNum;
    /**
     * 年度
     */
    @ExcelProperty(index = 2)
    private String year;
    /**
     * 期间
     */
    @ExcelProperty(index = 3)
    private String period;
    /**
     * 单位代码
     */
    @ExcelProperty(index = 4)
    private String unitCode;
    /**
     * 单位名称
     */
    @ExcelProperty(index = 5)
    private String unitName;
    /**
     * 凭证日期
     */
    @ExcelProperty(index = 6)
    private Date credentialDate;
    /**
     * 借方金额
     */
    @ExcelProperty(index = 7)
    private String debitAmount;
    /**
     * 贷方金额
     */
    @ExcelProperty(index = 8)
    private String lenderAmount;
    /**
     * 科目代码
     */
    @ExcelProperty(index = 9)
    private String subjectCode;
    /**
     * 科目名称
     */
    @ExcelProperty(index = 10)
    private String subjectName;
    /**
     * 项目代码
     */
    @ExcelProperty(index = 11)
    private String projectCode;
    /**
     * 项目名称
     */
    @ExcelProperty(index = 12)
    private String projectName;
    /**
     * 功能分类代码
     */
    @ExcelProperty(index = 13)
    private String functionTypeCode;
    /**
     * 功能分类名称
     */
    @ExcelProperty(index = 14)
    private String functionTypeName;
    /**
     * 经济分类代码
     */
    @ExcelProperty(index = 15)
    private String economicTypeCode;
    /**
     * 经济分类名称
     */
    @ExcelProperty(index = 16)
    private String economicTypeName;


    public DataImportEntity() {
    }

    public DataImportEntity(String credentialId, String credentialNum, String year, String period, String unitCode, String unitName, Date credentialDate, String debitAmount, String lenderAmount, String subjectCode, String subjectName, String projectCode, String projectName, String functionTypeCode, String functionTypeName, String economicTypeCode, String economicTypeName) {
        this.credentialId = credentialId;
        this.credentialNum = credentialNum;
        this.year = year;
        this.period = period;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.credentialDate = credentialDate;
        this.debitAmount = debitAmount;
        this.lenderAmount = lenderAmount;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.functionTypeCode = functionTypeCode;
        this.functionTypeName = functionTypeName;
        this.economicTypeCode = economicTypeCode;
        this.economicTypeName = economicTypeName;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialNum() {
        return credentialNum;
    }

    public void setCredentialNum(String credentialNum) {
        this.credentialNum = credentialNum;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Date getCredentialDate() {
        return credentialDate;
    }

    public void setCredentialDate(Date credentialDate) {
        this.credentialDate = credentialDate;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getLenderAmount() {
        return lenderAmount;
    }

    public void setLenderAmount(String lenderAmount) {
        this.lenderAmount = lenderAmount;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFunctionTypeCode() {
        return functionTypeCode;
    }

    public void setFunctionTypeCode(String functionTypeCode) {
        this.functionTypeCode = functionTypeCode;
    }

    public String getFunctionTypeName() {
        return functionTypeName;
    }

    public void setFunctionTypeName(String functionTypeName) {
        this.functionTypeName = functionTypeName;
    }

    public String getEconomicTypeCode() {
        return economicTypeCode;
    }

    public void setEconomicTypeCode(String economicTypeCode) {
        this.economicTypeCode = economicTypeCode;
    }

    public String getEconomicTypeName() {
        return economicTypeName;
    }

    public void setEconomicTypeName(String economicTypeName) {
        this.economicTypeName = economicTypeName;
    }

    @Override
    public String toString() {
        return "DataImportEntity{" +
                "credentialId='" + credentialId + '\'' +
                ", credentialNum='" + credentialNum + '\'' +
                ", year='" + year + '\'' +
                ", period='" + period + '\'' +
                ", unitCode='" + unitCode + '\'' +
                ", unitName='" + unitName + '\'' +
                ", credentialDate=" + credentialDate +
                ", debitAmount='" + debitAmount + '\'' +
                ", lenderAmount='" + lenderAmount + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", functionTypeCode='" + functionTypeCode + '\'' +
                ", functionTypeName='" + functionTypeName + '\'' +
                ", economicTypeCode='" + economicTypeCode + '\'' +
                ", economicTypeName='" + economicTypeName + '\'' +
                '}';
    }
}
