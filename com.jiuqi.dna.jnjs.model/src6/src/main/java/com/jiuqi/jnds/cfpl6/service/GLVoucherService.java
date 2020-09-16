package com.jiuqi.jnds.cfpl6.service;

import java.util.List;

import com.jiuqi.jnds.cfpl6.entity.LSRecalculation;

/**
 * 凭证重算逻辑相关
 * @author  houchenglong
 * @create  2020/9/8 22:37
 * @desc
 **/
public interface GLVoucherService {
    /**
     * 根据单位、凭证年度、凭证编号 对 凭证主表 分组查询
     * @return
     */
    List<?> queryGLVoucherByGroup();
    /**
     * 获取需要重算的数据
     * @param unitId 单位id
     * @param acctYear 凭证年度
     * @param acctPeriod 凭证编号
     * @return
     */
    List<?> queryNeedRecalculation(String unitId, String acctYear, String acctPeriod);
    /**
     * 修改凭证编号
     * @param recId 凭证主表 id
     * @param vchrNum 凭证编号
     */
    int updateGLVoucher(String recId,String vchrNum);
    /**
     * 凭证主表 重算
     * @param list
     * @return
     */
    void glVoucherRecalculation(List<?> list);
    /**
     * 查询单位实体
     * @return
     */
    List<?> queryMDOrgById(String unitId);

    /**
     * 查询 重算结果表 数据
     * @return
     */
    List<?> queryLSRecalculation();
    /**
     * 新增 重算结果表 数据
     * @return
     */
    int insertLSRecalculation(LSRecalculation lsRecalculation);
    /**
     * 删除 重算结果表 数据
     * @return
     */
    int deleteLSRecalculation();
}
