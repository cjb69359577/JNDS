package com.jiuqi.jnds.cfpl6.service.impl;

import com.jiuqi.jnds.cfpl6.constant.GLVoucherConstant;
import com.jiuqi.jnds.cfpl6.entity.LSRecalculation;
import com.jiuqi.jnds.cfpl6.service.GLVoucherService;
import com.jiuqi.jnds.cfpl6.util.UUIDUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@Transactional
@SuppressWarnings({ "deprecation", "unchecked" })
public class GLVoucherServiceImpl implements GLVoucherService {
    @PersistenceContext
    private EntityManager entityManager;

	@Override
    public List<?> queryGLVoucherByGroup() {
        /**
         * select rawtohex(t.unitid) as unitid ,t.acctyear,t.acctperiod from gl_voucher t group by t.unitid ,t.acctyear,t.acctperiod order by t.acctyear;
         */
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select ");
        stringBuffer.append("rawtohex(t.unitid) as unitid,t.acctyear,t.acctperiod ");
        stringBuffer.append("from ");
        stringBuffer.append("gl_voucher t ");
        stringBuffer.append("group by t.unitid ,t.acctyear,t.acctperiod ");
        stringBuffer.append("order by t.acctyear ");
        List<?> resultList = entityManager.createNativeQuery(stringBuffer.toString()).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
        return resultList;
    }

    @Override
    public List<?> queryNeedRecalculation(String unitId, String acctYear, String acctPeriod) {
        /**
         * select
         *        t1.xuh as xuh,
         *        rawtohex(t1.recid) as recid
         *        rawtohex(t1.unitid) as unitid
         *        t1.vchrnum as vchrnum
         *   from (select row_number() over(ORDER BY t.createtime) as xuh,
         *                t.recid as recid,
         *                t.vchrnum as vchrnum,
         *                t.unitid as unitid,
         *                t.createtime as chuangjsj
         *           from gl_voucher t
         *           where t.unitid = '67F3F734C0000141C61090BD174221C4'
         *                  and t.acctyear = 2019
         *                  and t.acctperiod = 9
         *          ORDER BY t.createtime) t1
         *  where xuh != t1.vchrnum ;
         */
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select ");
        stringBuffer.append("t1.xuh as xuh,rawtohex(t1.recid) as recid,rawtohex(t1.unitid) as unitid,t1.vchrnum as vchrnum ");
        stringBuffer.append("from ");
        stringBuffer.append("( ");

        stringBuffer.append("select row_number() over(ORDER BY t.createtime) as xuh,t.recid as recid,t.vchrnum as vchrnum,t.unitid as unitid,t.createtime as chuangjsj ");
        stringBuffer.append("from gl_voucher t ");
        stringBuffer.append("where ");
        stringBuffer.append("t.unitid = '"+unitId+"' ");
        stringBuffer.append("and t.acctyear = "+acctYear+" ");
        stringBuffer.append("and t.acctperiod = "+acctPeriod+" ");
        stringBuffer.append(" ORDER BY t.createtime ");
        stringBuffer.append(" ) t1 ");

        stringBuffer.append("where ");
        stringBuffer.append(" xuh != t1.vchrnum ");
        List<?> resultList = entityManager.createNativeQuery(stringBuffer.toString()).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
        return resultList;
    }

    @Override
    public int updateGLVoucher(String recId, String vchrNum) {
        // hhibernate sql语法或者原生sql都可以。参数使用：占位，参数名称可以随便定义
        String sql = "update gl_voucher t set t.vchrnum = :vchrnum where t.recid = :recid";
        return entityManager.createNativeQuery(sql)
                .setParameter("vchrnum", vchrNum).setParameter("recid", UUIDUtils.fromStringToUuid(recId))
                .executeUpdate();
    }

    @Override
    public void glVoucherRecalculation(List<?> list) {
        //是否清空重算结果表
        Boolean ifDelete = true;
        //遍历分组后的数据
        for(Object object : list){
            LSRecalculation lsRecalculation = new LSRecalculation();
            Map<String,Object> map = (Map<String,Object>)object;
            lsRecalculation.setId(UUID.randomUUID());
            lsRecalculation.setAcctyear(map.get(GLVoucherConstant.ACCTYEAR).toString());
            lsRecalculation.setAcctperiod(map.get(GLVoucherConstant.ACCTPERIOD).toString());

            //通过单位id、凭证年度、凭证编号 获取需要重算的数据
            List<?> needRec = queryNeedRecalculation(map.get(GLVoucherConstant.UNITID).toString(),
                    map.get(GLVoucherConstant.ACCTYEAR).toString(),
                    map.get(GLVoucherConstant.ACCTPERIOD).toString());
            if(needRec != null && needRec.size() >0){
                //只要存在重算数据时，就清空结果表
                if(ifDelete){
                    deleteLSRecalculation();
                    //只需要清空一次
                    ifDelete =false;
                }

                long oldTime = System.currentTimeMillis();//相比较的时间
                //存在重算数据
                for(Object needObject : needRec){
                    Map<String,Object> needMap = (Map<String,Object>)needObject;
                    System.err.println(":::::"+needMap.get(GLVoucherConstant.XUH).toString());
                    System.err.println(":::::"+needMap.get(GLVoucherConstant.RECID).toString());
                    System.err.println(":::::"+needMap.get(GLVoucherConstant.VCHRNUM).toString());
                    lsRecalculation.setGlid(needMap.get(GLVoucherConstant.RECID).toString());

                    //根据计算规则 修改 凭证主表（返回的序号数值就是需要进行修改的值）
                    updateGLVoucher(needMap.get(GLVoucherConstant.RECID).toString(),needMap.get(GLVoucherConstant.XUH).toString());
                }
                long sysTime = System.currentTimeMillis();
                if((sysTime - oldTime) == 0){
                    //重算时间 为 0 时 设置1
                    lsRecalculation.setTime("1");
                }else{
                    //获取重算时间
                    lsRecalculation.setTime((sysTime - oldTime) + "");
                }

                //通过单位id获取单位实体
                List<?> mdOrgList = queryMDOrgById(map.get(GLVoucherConstant.UNITID).toString());
                if(mdOrgList != null && mdOrgList.size() > 0){
                    Map<String,Object> mdOrgMap = (Map<String,Object>)mdOrgList.get(0);
                    lsRecalculation.setUnitcode(mdOrgMap.get(GLVoucherConstant.STDCODE).toString());
                    lsRecalculation.setUnitname(mdOrgMap.get(GLVoucherConstant.STDNAME).toString());

                    //新增 重算结果表 数据
                    insertLSRecalculation(lsRecalculation);
                }else{
                    System.err.println("凭证主表重算------------根据单位id获取单位失败;id="+map.get(GLVoucherConstant.UNITID).toString());
                }
            }else{
                System.err.println("单位id:::::"+map.get(GLVoucherConstant.UNITID).toString());
                System.err.println("凭证年度:::::"+map.get(GLVoucherConstant.ACCTYEAR).toString());
                System.err.println("凭证期间:::::"+map.get(GLVoucherConstant.ACCTPERIOD).toString());
                System.err.println("凭证主表重算------------未获取到需要重算的数据集");
            }
        }
    }

    @Override
    public List<?> queryMDOrgById(String unitId) {
        /**
         * select t.stdcode,t.stdname from md_org t where t.recid = ''
         */
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select ");
        stringBuffer.append("t.stdcode,t.stdname ");
        stringBuffer.append("from ");
        stringBuffer.append("md_org t ");
        stringBuffer.append("where ");
        stringBuffer.append("t.recid = '"+unitId+"' ");
        List<?> resultList = entityManager.createNativeQuery(stringBuffer.toString()).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
        return resultList;
    }

	@Override
    public List<?> queryLSRecalculation() {
        /**
         * select rawtohex(t.id) as id,t.unitname as danwmc,t.acctyear as pingznd,t.acctperiod as pingzqb,t.time as chongshs from ls_recalculation t;
         */
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select ");
        stringBuffer.append("rawtohex(t.id) as id,t.unitname as danwmc,t.acctyear as pingznd,t.acctperiod as pingzqb,t.time as chongshs ");
        stringBuffer.append("from ");
        stringBuffer.append("ls_recalculation t ");
        List<Map<String, Object>> resultList = entityManager.createNativeQuery(stringBuffer.toString()).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
        List<String> list = new ArrayList<String>();
        resultList.stream().forEach(m -> {
        	StringBuffer buffer = new StringBuffer();
        	buffer.append(m.get("DANWMC")).append(" 单位 ").append(m.get("PINGZND")).append(" 年度第 ").append(m.get("PINGZQB")).append(" 期凭证执行重算，重算耗时：").append(m.get("CHONGSHS")).append(" ms");
        	list.add(buffer.toString());
        });
        return list;
    }

    @Override
    public int insertLSRecalculation(LSRecalculation lsRecalculation) {
        String sql = "insert into ls_recalculation values (:id,:acctperiod,:acctyear,:glid,:time,:unitcode,:unitname)";
        return entityManager.createNativeQuery(sql)
                .setParameter("id", lsRecalculation.getId())
                .setParameter("acctperiod", lsRecalculation.getAcctperiod())
                .setParameter("acctyear", lsRecalculation.getAcctyear())
                .setParameter("glid", lsRecalculation.getGlid())
                .setParameter("time", lsRecalculation.getTime())
                .setParameter("unitname", lsRecalculation.getUnitname())
                .setParameter("unitcode", lsRecalculation.getUnitcode())
                .executeUpdate();
    }

    @Override
    public int deleteLSRecalculation() {
        /**
         * delete from ls_recalculation
         */
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("delete ");
        stringBuffer.append("from ");
        stringBuffer.append("ls_recalculation");
        return entityManager.createNativeQuery(stringBuffer.toString()).executeUpdate();
    }
}
