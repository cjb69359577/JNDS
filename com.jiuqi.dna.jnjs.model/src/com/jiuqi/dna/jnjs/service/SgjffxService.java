package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
/**
 * 三公经费：
 * 是指“因公出国（境）费用”、
 * “公务接待费”、“公务车购置及运行费”
 * @author Administrator
 *
 */
public class  SgjffxService{
	
	
	/**
	 * 因公出国（境）费用 = 凭证主表（GL_VOUCHER）中年度为2019年，单位为“000223单位”（单位编号为：000223）,
	 *    期间为1到12的关联的凭证子表（GL_VOUCHERITEM）中科目代码以 “72010102”或“72010202”或“72010302”或“79010102”开头并且关联的辅助组合表（GL_ASSISTCOMB）中经济分类代码以30212开头的数据中，
	 *     其“借方金额”字段合计值减去“贷方金额”字段合计值。
	 *  公务接待费 = 凭证主表（GL_VOUCHER）中年度为2019年，单位为“000223单位”（单位编号为：000223）,
	 *    期间为1到12的关联的凭证子表（GL_VOUCHERITEM）中科目代码以 “72010102”或“72010202”或“72010302”或“79010102”
	 *    开头并且关联的辅助组合表（GL_ASSISTCOMB）中经济分类代码以30217开头的数据中，其“借方金额”字段合计值减去“贷方金额”字段合计值。
	 *  公务车购置及运行费 = 凭证主表（GL_VOUCHER）中年度为2019年，单位为“000223单位”（单位编号为：000223）,
	 *  期间为1到12的关联的凭证子表（GL_VOUCHERITEM）中科目代码以（科目基础数据的STDCODE字段）“72010102”或“72010202”或“72010302”或“79010102”
	 *  开头并且关联的辅助组合表（GL_ASSISTCOMB）中经济分类代码以“30231”或“30913”或“31013”开头的数据中，其“借方金额”字段合计值减去“贷方金额”字段合计值。
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getSgjffx(Context conn,int acctyear){
		Map<String,Double> mapSG = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getSgjffx(@acctyear int) begin  ");
		sb.append(" select SUM( case when ( conclass.STDCODE like '30212%') then (item.DEBIT - item.CREDIT) else 0 end) as  sgjf, ");
		sb.append("       SUM( case when ( conclass.STDCODE like '30217%') then (item.DEBIT - item.CREDIT) else 0 end) as  gwjdf, ");
		sb.append("      SUM( case when (conclass.STDCODE like '30231%' or conclass.STDCODE like '30913%' or conclass.STDCODE like '31013%') then (item.DEBIT - item.CREDIT) else 0 end) as  gwcgz ");
		sb.append("   from GL_VOUCHER as a ");
		sb.append("   join MD_ORG  as org ");
		sb.append("       on org.RECID = a.UNITID ");
		sb.append("   join GL_VOUCHERITEM as item ");
		sb.append("       on item.VCHRID = a.RECID ");
		sb.append("   join MD_ACCOUNTSUBJECT as subject ");
		sb.append("       on subject.RECID = item.SUBJECTID ");
		sb.append("   join GL_ASSISTCOMB as comb ");
		sb.append("      on item.ASSCOMBID = comb.RECID ");
		sb.append("   join MD_EXPENDECONCLASS  as conclass ");
		sb.append("      on conclass.RECID = comb.EXPENDECONCLASSID ");
		sb.append(" where a.ACCTYEAR = 2019 ");
		sb.append("   and item.ASSCOMBID is not null ");
		sb.append("   and comb.EXPENDECONCLASSID is not null ");
		sb.append("   and org.STDCODE = '000223' ");
		sb.append("   and (subject.STDCODE like '72010102%' or  subject.STDCODE like '72010202%' or subject.STDCODE like '72010302%' or  subject.STDCODE like '79010102%') ");
		  
		sb.append("	end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			while (rs.next()) {
				mapSG.put("因公出国（境）费用",rs.getFields().get(0).getDouble());
				mapSG.put("公务接待费",rs.getFields().get(1).getDouble());
				mapSG.put("公务车购置及运行费",rs.getFields().get(2).getDouble());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapSG;
	}

}
