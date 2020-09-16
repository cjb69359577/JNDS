package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
/**
 * 项目支出分析
 * 例如取项目A支出数逻辑为：
 * 凭证主表（GL_VOUCHER）中年度为2019年，单位为“000223单位”（单位编号为：000223）,
 * 期间为1到12的凭证关联的凭证子表（GL_VOUCHERITEM）中科目代码以（科目基础数据的STDCODE字段）
 * “720102”或“730102”或“740102” 或“750102” 或“790102”开头并且关联的辅助组合表（GL_ASSISTCOMB）中项目为A的数据，其“借方金额”字段合计值减去“贷方金额”字段合计值。
 * @author Administrator
 *
 */
public class  XmzcJdService{
	
	
	/**
	 * 获取单位为000223，获取项目支出分析情况
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getXmzc(Context conn,int acctyear){
		Map<String,Double> mapSR = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getXmzc(@acctyear int) begin  ");
		sb.append(" SELECT result.stdname as stdname,result.hj as hj ");
		sb.append("  FROM (SELECT  pro.stdname as stdname, sum(item.debit) - sum(item.credit) as hj ");
		sb.append("          FROM gl_voucher as a ");
		sb.append("          join md_org  as org ");
		sb.append("            on org.recid = a.unitid ");
		sb.append("          join gl_voucheritem as item ");
		sb.append("            on item.vchrid = a.recid ");
		sb.append("          join md_accountsubject as subject ");
		sb.append("            on subject.recid = item.subjectid ");
		sb.append("          join gl_assistcomb as comb ");
		sb.append("            on item.asscombid = comb.recid ");
		sb.append("          join md_project as pro ");
		sb.append("            on pro.recid = comb.projectid ");
		sb.append("         where a.acctyear = 2019 ");
		sb.append("           and item.asscombid is not null ");
		sb.append("           and org.stdcode = '000223' ");
		sb.append("           and comb.projectid is not null ");
		sb.append("           and ((subject.stdcode like '720102%') or ");
		sb.append("               (subject.stdcode like '730102%') or ");
		sb.append("               (subject.stdcode like '740102%') or ");
		sb.append("          (subject.stdcode like '750102%') or ");
		sb.append("       (subject.stdcode like '790102%')) ");
		sb.append("     group by pro.stdname ");
		sb.append("     order by sum(item.debit) - sum(item.credit) desc) as result ");
        sb.append("    order by result.hj desc  ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			int num =0;
			while (rs.next()) {
			   if(num <=9){
				    num ++;
				    System.out.println(rs.getFields().get(0).getString()+"======"+rs.getFields().get(1).getDouble());
				 mapSR.put(rs.getFields().get(0).getString(),rs.getFields().get(1).getDouble());
			   }else{
				   break;
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

}
