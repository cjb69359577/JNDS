package com.jiuqi.dna.jnjs.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.jnjs.entity.XmzcEntry;
import com.jiuqi.dna.jnjs.util.PropertyUtil;
/**
 * 项目支出分析
 * 例如取项目A支出数逻辑为：
 * 凭证主表（GL_VOUCHER）中年度为2019年，单位为“000223单位”（单位编号为：000223）,
 * 期间为1到12的凭证关联的凭证子表（GL_VOUCHERITEM）中科目代码以（科目基础数据的STDCODE字段）
 * “720102”或“730102”或“740102” 或“750102” 或“790102”开头并且关联的辅助组合表（GL_ASSISTCOMB）中项目为A的数据，其“借方金额”字段合计值减去“贷方金额”字段合计值。
 * @author Administrator
 *
 */
public class  XmzcService{
	
	
	/**
	 * 获取单位为000223，获取项目支出分析情况
	 * @param conn
	 * @return
	 */
	public static Map<String,XmzcEntry> getXmzc(Context conn,int acctyear){
		long start = System.currentTimeMillis();
		Map<String,XmzcEntry> mapSR = new HashMap<String,XmzcEntry>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getXmzc(@acctyear int) begin  ");
		sb.append(" select p.stdcode as xmdm,p.stdname as xmmc,f.stdcode as gnfldm,  ");
		sb.append(" f.stdname as gnflmc,substr(j.stdcode,0,5) as jjfldm ,               ");
		sb.append(" sum(s.debit-s.credit) as je,p.recid as xmrecid,f.recid as gnrecid from gl_voucheritem as s                ");
		sb.append(" join gl_voucher as t on s.vchrid=t.recid                      ");
		sb.append(" join md_accountsubject as  a on s.subjectid=a.recid            ");
		sb.append(" join gl_assistcomb as g on g.recid=s.asscombid                ");
		sb.append(" join md_expendfuncclass as f on f.recid=g.expendfuncclassid   ");
		sb.append(" join md_project as p on p.recid=g.projectid                   ");
		sb.append(" join md_org as o on o.recid=t.unitid                          ");
		sb.append(" join md_expendeconclass as j on j.recid=g.expendeconclassid   ");
		sb.append(" where t.acctyear=2019 and o.stdcode='000223'                     ");
		sb.append(" and (a.stdcode like '72010102%' or a.stdcode like '72010202%'    ");
		sb.append(" or a.stdcode like '72010302%' or a.stdcode like '79010102%')     ");
		sb.append(" group by  f.stdcode,p.stdcode,p.stdname,f.stdname,f.recid,p.recid,               ");
		sb.append(" substr(j.stdcode,0,5) having sum(s.debit-s.credit)<>0            ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			System.out.println("查询耗时：" + (System.currentTimeMillis() - start) + "毫秒");
			while (rs.next()) {
			    if(mapSR.get(rs.getFields().get(0).getString()+"$"+rs.getFields().get(1).getString()+"$"+rs.getFields().get(2).getString()+"$"+rs.getFields().get(3).getString()+"$"+rs.getFields().get(6).getString()+"$"+rs.getFields().get(7).getString())==null){
			    	XmzcEntry entry = new XmzcEntry();
			    	/*entry.setJe30101(0d);
			    	entry.setJe30102(0d);
			    	entry.setJe30103(0d);
			    	entry.setJe30106(0d);
			    	entry.setJe30107(0d);
			    	entry.setJe30108(0d);
			    	entry.setJe30109(0d);
			    	entry.setJe30110(0d);
			    	entry.setJe30111(0d);
			    	entry.setJe30112(0d);
			    	entry.setJe30113(0d);
			    	entry.setJe30114(0d);
			    	entry.setJe30199(0d);
			    	entry.setJe30201(0d);
			    	entry.setJe30202(0d);
			    	entry.setJe30203(0d);
			    	entry.setJe30204(0d);
			    	entry.setJe30205(0d);
			    	entry.setJe30206(0d);
			    	entry.setJe30207(0d);
			    	entry.setJe30208(0d);
			    	entry.setJe30209(0d);
			    	entry.setJe30211(0d);
			    	entry.setJe30212(0d);
			    	entry.setJe30213(0d);
			    	entry.setJe30214(0d);
			    	entry.setJe30215(0d);
			    	entry.setJe30216(0d);
			    	entry.setJe30217(0d);
			    	entry.setJe30218(0d);
			    	entry.setJe30224(0d);
			    	entry.setJe30225(0d);
			    	entry.setJe30226(0d);
			    	entry.setJe30227(0d);
			    	entry.setJe30228(0d);
			    	entry.setJe30229(0d);
			    	entry.setJe30231(0d);
			    	entry.setJe30239(0d);
			    	entry.setJe30240(0d);
			    	entry.setJe30299(0d);
			    	entry.setJe30301(0d);
			    	entry.setJe30302(0d);
			    	entry.setJe30303(0d);
			    	entry.setJe30304(0d);
			    	entry.setJe30305(0d);
			    	entry.setJe30306(0d);
			    	entry.setJe30307(0d);
			    	entry.setJe30308(0d);
			    	entry.setJe30309(0d);
			    	entry.setJe30310(0d);
			    	entry.setJe30399(0d);
			    	entry.setJe30701(0d);
			    	entry.setJe30702(0d);
			    	entry.setJe30703(0d);
			    	entry.setJe30704(0d);
			    	entry.setJe30901(0d);
			    	entry.setJe30902(0d);
			    	entry.setJe30903(0d);
			    	entry.setJe30905(0d);
			    	entry.setJe30906(0d);
			    	entry.setJe30907(0d);
			    	entry.setJe30908(0d);
			    	entry.setJe30913(0d);
			    	entry.setJe30919(0d);
			    	entry.setJe30921(0d);
			    	entry.setJe30922(0d);
			    	entry.setJe30999(0d);
			    	entry.setJe31001(0d);
			    	entry.setJe31002(0d);
			    	entry.setJe31003(0d);
			    	entry.setJe31005(0d);
			    	entry.setJe31006(0d);
			    	entry.setJe31007(0d);
			    	entry.setJe31008(0d);
			    	entry.setJe31009(0d);
			    	entry.setJe31010(0d);
			    	entry.setJe31011(0d);
			    	entry.setJe31012(0d);
			    	entry.setJe31013(0d);
			    	entry.setJe31019(0d);
			    	entry.setJe31021(0d);
			    	entry.setJe31022(0d);
			    	entry.setJe31099(0d);
			    	entry.setJe31101(0d);
			    	entry.setJe31199(0d);
			    	entry.setJe31201(0d);
			    	entry.setJe31203(0d);
			    	entry.setJe31204(0d);
			    	entry.setJe31205(0d);
			    	entry.setJe31299(0d);
			    	entry.setJe31302(0d);
			    	entry.setJe31303(0d);
			    	entry.setJe39906(0d);
			    	entry.setJe39907(0d);
			    	entry.setJe39908(0d);
			    	entry.setJe39999(0d);
			    	*/
			    	entry.setXmdm(rs.getFields().get(0).getString());
			    	entry.setXmmc(rs.getFields().get(1).getString());
			    	entry.setXmrecid(rs.getFields().get(6).getString());
			    	
			    	entry.setGnfldm(rs.getFields().get(2).getString());
			    	entry.setGnflmc(rs.getFields().get(3).getString());
			    	entry.setGnflrecid(rs.getFields().get(7).getString());
			    	PropertyUtil.setProperty(entry, "je"+rs.getFields().get(4).getString(), rs.getFields().get(5).getDouble());
			    	
			    	mapSR.put(rs.getFields().get(0).getString()+"$"+rs.getFields().get(1).getString()+"$"+rs.getFields().get(2).getString()+"$"+rs.getFields().get(3).getString()+"$"+rs.getFields().get(6).getString()+"$"+rs.getFields().get(7).getString(), entry);
			    }else{
			    	PropertyUtil.setProperty(mapSR.get(rs.getFields().get(0).getString()+"$"+rs.getFields().get(1).getString()+"$"+rs.getFields().get(2).getString()+"$"+rs.getFields().get(3).getString()+"$"+rs.getFields().get(6).getString()+"$"+rs.getFields().get(7).getString()), "je"+rs.getFields().get(4).getString(), rs.getFields().get(5).getDouble());
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			//释放资源
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapSR;
	}

	
	public static void main(String[] args) {
		char[] entry = null;
		new BigDecimal(entry);
	}
}
