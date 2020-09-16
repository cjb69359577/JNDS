package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.jnjs.util.MapToJson;
/**
 * 具体解释为：展示指定单位：“000223单位”
 * （单位编号为：000223）
 * 2019年度1至12月份的收入及支出情况。
 * @author Administrator
 *
 */
public class  MySZqkfxService{
	
    
	
	/**
	 * 1月收入数据=凭证主表（GL_VOUCHER）
	 * 中年度为2019年，单位为：“000223单位”（单位编号为：000223），
	 *   期间为1（对应一月）的凭证关联的凭证子表（GL_VOUCHERITEM）
	 *   中科目代码以 “6”开头的数据，
	 *   其“贷方金额”字段合计值减去“借方金额”字段合计值。
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getSZqkfxBySR(Context conn,int acctyear){
		Map<String,Double> mapSR = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getSZqkfxBySR(@acctyear int) begin  ");
		sb.append("  SELECT a.acctperiod  as acctperiod,(sum(item.credit)-sum(item.debit)) as hj ");
		sb.append("  FROM gl_voucher as a ");
		sb.append("  join md_org as org ");
		sb.append("      on org.recid = a.unitid ");
		sb.append("      and org.stdcode = '000223'");
		sb.append("  join gl_voucheritem as item ");
		sb.append("      on item.vchrid = a.recid ");
		sb.append("  join md_accountsubject as subject ");
		sb.append("      on subject.recid=item.subjectid ");
		sb.append("      and subject.stdcode like '6%' ");
		sb.append("  where a.acctyear=@acctyear ");
		sb.append("   group by a.acctperiod ");
		sb.append("  order by a.acctperiod ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			while (rs.next()) {
				mapSR.put(rs.getFields().get(0).getInt()+"",MapToJson.getDoubleFormat(rs.getFields().get(1).getDouble()/10000));
			}
		} catch (Exception e) {
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapSR;
	}

	
	/**
	 * 1月支出数据=凭证主表（GL_VOUCHER）中年度为2019年，
	 *    单位为：“000223单位”（单位编号为：000223），
	 *    期间为1（对应一月）的凭证关联的凭证子表（GL_VOUCHERITEM）
	 *    中科目代码以 “7”开头的数据，
	 *    其“借方金额”字段合计值减去“贷方金额”字段合计值。
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getSZqkfxByZC(Context conn,int acctyear){
		Map<String,Double> mapZC = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getSZqkfxByZC(@acctyear int) begin  ");
		sb.append("  SELECT a.acctperiod  as acctperiod,(sum(item.debit)-sum(item.credit)) as hj ");
		sb.append("  FROM gl_voucher as a ");
		sb.append("  join md_org as org ");
		sb.append("      on org.recid = a.unitid ");
		sb.append("      and org.stdcode = '000223'");
		sb.append("  join gl_voucheritem as item ");
		sb.append("      on item.vchrid = a.recid ");
		sb.append("  join md_accountsubject as subject ");
		sb.append("      on subject.recid=item.subjectid ");
		sb.append("      and subject.stdcode like '7%' ");
		sb.append("  where a.acctyear=@acctyear ");
		sb.append("   group by a.acctperiod ");
		sb.append("  order by a.acctperiod ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			while (rs.next()) {
				mapZC.put(rs.getFields().get(0).getInt()+"",MapToJson.getDoubleFormat(rs.getFields().get(1).getDouble()/10000));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapZC;
	}
}
