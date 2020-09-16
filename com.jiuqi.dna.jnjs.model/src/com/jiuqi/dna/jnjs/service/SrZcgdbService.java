package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;

/**
 * 该表为收入支出表，
 * A-C列主要是提取2019年度“000223单位”（
 * 单位编号为：000223）
 * 1到12期指定科目相关的凭证数据的金额信息，
 * 从而体现出本单位本年度的收入情况
 * @author zhangwei
 *
 */
public class  SrZcgdbService{
	
	
	/**
	 * 收入  sql编写
	 * 取数规则
	 * 【C4】=凭证主表（GL_VOUCHER）中年度为2019年，“000223单位”（单位编号为：000223）,
	 * 期间为1到12的凭证关联的凭证子表（GL_VOUCHERITEM）中科目代码（科目表的STDCODE字段）
	 * 以“600101”或“600102”开头的数据，其“贷方金额”字段合计值减去“借方金额”字段合计值。
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getSrService(Context conn,int acctyear){
		Map<String,Double> mapSR = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getSrzcgdService(@acctyear int) begin  ");
		sb.append("  select SUM( case when (a.stdcode like '600101%' or a.stdcode like '600102%')  then (s.credit - s.debit) else 0 end) as ybsr, ");
		sb.append("         SUM( case when (a.stdcode like '6201%')  then (s.credit - s.debit) else 0 end) as sjsr,");
		sb.append("         SUM( case when (a.stdcode like '6101%')  then (s.credit - s.debit) else 0 end) as sysr,");
		sb.append("         SUM( case when (a.stdcode like '6401%')  then (s.credit - s.debit) else 0 end) as jysr,");
		sb.append("         SUM( case when (a.stdcode like '6301%'  )  then (s.credit - s.debit) else 0 end) as fssr,");
		sb.append("         SUM( case when (a.stdcode like '6501%' or a.stdcode like '6601%' or a.stdcode like '6602%' or a.stdcode like '6609%' )  then (s.credit - s.debit) else 0 end) as qtsr ");
		sb.append("   from gl_voucheritem as s ");
		sb.append("   join gl_voucher as t ");
		sb.append("     on s.vchrid = t.recid ");
		sb.append("   join md_accountsubject as a ");
		sb.append("     on s.subjectid = a.recid ");
		sb.append("   join md_org as o ");
		sb.append("     on o.recid = t.unitid ");
		sb.append("   where t.acctyear = 2019 ");
		sb.append("    and o.stdcode = '000223' ");
		sb.append("   end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			while (rs.next()) {
				mapSR.put("ybsr", rs.getFields().get(0).getDouble());
				mapSR.put("sjsr", rs.getFields().get(1).getDouble());
				mapSR.put("sysr", rs.getFields().get(2).getDouble());
				mapSR.put("jysr", rs.getFields().get(3).getDouble());
				mapSR.put("fssr", rs.getFields().get(4).getDouble());
				mapSR.put("qtsr", rs.getFields().get(5).getDouble());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapSR;
	}
	
	

	/**
	 * 支出sql
	 * 取数规则
	 * 即：【F4】=凭证主表（GL_VOUCHER）中年度为2019年，“000223单位”（单位编号为：000223）,
	 * 期间为1到12的凭证关联的凭证子表（GL_VOUCHERITEM）中科目代码以（科目基础数据的STDCODE字段）
	 * “7201”或“7301”或“7401”或“7501”开头并且关联的辅助组合表（GL_ASSISTCOMB）中功能分类以201开头的数据，
	 * 其“借方金额”字段合计值减去“贷方金额”字段合计值。
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getZcService(Context conn,int acctyear){
		Map<String,Double> mapZC = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getSrzcgdService(@acctyear int) begin  ");
		sb.append("  select substr(f.stdcode, 1, 3) as stdcode, sum(s.debit - s.credit) as hj ");
		sb.append("  from gl_voucheritem as s ");
		sb.append("  join gl_voucher as t ");
		sb.append("    on s.vchrid = t.recid ");
		sb.append("   join md_accountsubject as a "); 
		sb.append("     on s.subjectid = a.recid ");
		sb.append("  join gl_assistcomb as g ");
		sb.append("    on g.recid = s.asscombid ");
		sb.append("  join md_expendfuncclass as f ");
		sb.append("    on f.recid = g.expendfuncclassid ");
		sb.append("  join md_org as o ");
		sb.append("    on o.recid = t.unitid ");
		sb.append(" where t.acctyear = 2019 "); 
		sb.append("   and o.stdcode = '000223' ");
		sb.append("   and (a.stdcode like '7201%' or a.stdcode like '7301%' or ");
		sb.append("       a.stdcode like '7401%' or a.stdcode like '7501%') ");
		sb.append("  group by substr(f.stdcode, 1, 3) ");
		sb.append("  order by substr(f.stdcode, 1, 3); ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getFields().get(0).getString() +"---------"+rs.getFields().get(1).getDouble());
				mapZC.put(rs.getFields().get(0).getString(),rs.getFields().get(1).getDouble());
			}
		} catch (Exception e) {
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapZC;
	}
	

}
