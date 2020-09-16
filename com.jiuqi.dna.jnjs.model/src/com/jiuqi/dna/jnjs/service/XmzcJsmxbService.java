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
public class  XmzcJsmxbService{
	
	
	/**
	 * 获取单位为000223，获取项目支出分析情况
	 * @param conn
	 * @return
	 */
	public static Map<String,Map<String,Double>> getXmzc(Context conn,int acctyear){
		Map<String,Map<String,Double>> mapSR = new HashMap<String,Map<String,Double>>();
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
			while (rs.next()) {
//			    System.out.println(rs.getFields().get(0).getString()+"$"+rs.getFields().get(1).getString()+"$"+rs.getFields().get(2).getString()+"$"+rs.getFields().get(3).getString()+"$"+rs.getFields().get(4).getString()+"$"+rs.getFields().get(5).getDouble());
			    if(mapSR.get(rs.getFields().get(0).getString()+"$"+rs.getFields().get(1).getString()+"$"+rs.getFields().get(2).getString()+"$"+rs.getFields().get(3).getString()+"$"+rs.getFields().get(6).getString()+"$"+rs.getFields().get(7).getString())==null){
			    	Map<String,Double> map = new HashMap<String, Double>();
			    	map.put("30101",0d);
			    	map.put("30102",0d);
			    	map.put("30103",0d);
			    	map.put("30106",0d);
			    	map.put("30107",0d);
			    	map.put("30108",0d);
			    	map.put("30109",0d);
			    	map.put("30110",0d);
			    	map.put("30111",0d);
			    	map.put("30112",0d);
			    	map.put("30113",0d);
			    	map.put("30114",0d);
			    	map.put("30199",0d);
			    	map.put("30201",0d);
			    	map.put("30202",0d);
			    	map.put("30203",0d);
			    	map.put("30204",0d);
			    	map.put("30205",0d);
			    	map.put("30206",0d);
			    	map.put("30207",0d);
			    	map.put("30208",0d);
			    	map.put("30209",0d);
			    	map.put("30211",0d);
			    	map.put("30212",0d);
			    	map.put("30213",0d);
			    	map.put("30214",0d);
			    	map.put("30215",0d);
			    	map.put("30216",0d);
			    	map.put("30217",0d);
			    	map.put("30218",0d);
			    	map.put("30224",0d);
			    	map.put("30225",0d);
			    	map.put("30226",0d);
			    	map.put("30227",0d);
			    	map.put("30228",0d);
			    	map.put("30229",0d);
			    	map.put("30231",0d);
			    	map.put("30239",0d);
			    	map.put("30240",0d);
			    	map.put("30299",0d);
			    	map.put("30301",0d);
			    	map.put("30302",0d);
			    	map.put("30303",0d);
			    	map.put("30304",0d);
			    	map.put("30305",0d);
			    	map.put("30306",0d);
			    	map.put("30307",0d);
			    	map.put("30308",0d);
			    	map.put("30309",0d);
			    	map.put("30310",0d);
			    	map.put("30399",0d);
			    	map.put("30701",0d);
			    	map.put("30702",0d);
			    	map.put("30703",0d);
			    	map.put("30704",0d);
			    	map.put("30901",0d);
			    	map.put("30902",0d);
			    	map.put("30903",0d);
			    	map.put("30905",0d);
			    	map.put("30906",0d);
			    	map.put("30907",0d);
			    	map.put("30908",0d);
			    	map.put("30913",0d);
			    	map.put("30919",0d);
			    	map.put("30921",0d);
			    	map.put("30922",0d);
			    	map.put("30999",0d);
			    	map.put("31001",0d);
			    	map.put("31002",0d);
			    	map.put("31003",0d);
			    	map.put("31005",0d);
			    	map.put("31006",0d);
			    	map.put("31007",0d);
			    	map.put("31008",0d);
			    	map.put("31009",0d);
			    	map.put("31010",0d);
			    	map.put("31011",0d);
			    	map.put("31012",0d);
			    	map.put("31013",0d);
			    	map.put("31019",0d);
			    	map.put("31021",0d);
			    	map.put("31022",0d);
			    	map.put("31099",0d);
			    	map.put("31101",0d);
			    	map.put("31199",0d);
			    	map.put("31201",0d);
			    	map.put("31203",0d);
			    	map.put("31204",0d);
			    	map.put("31205",0d);
			    	map.put("31299",0d);
			    	map.put("31302",0d);
			    	map.put("31303",0d);
			    	map.put("39906",0d);
			    	map.put("39907",0d);
			    	map.put("39908",0d);
			    	map.put("39999",0d);
			    	map.put(rs.getFields().get(4).getString(), rs.getFields().get(5).getDouble());
			    	mapSR.put(rs.getFields().get(0).getString()+"$"+rs.getFields().get(1).getString()+"$"+rs.getFields().get(2).getString()+"$"+rs.getFields().get(3).getString()+"$"+rs.getFields().get(6).getString()+"$"+rs.getFields().get(7).getString(), map);
			    }else{
			    	mapSR.get(rs.getFields().get(0).getString()+"$"+rs.getFields().get(1).getString()+"$"+rs.getFields().get(2).getString()+"$"+rs.getFields().get(3).getString()+"$"+rs.getFields().get(6).getString()+"$"+rs.getFields().get(7).getString()).put(rs.getFields().get(4).getString(), rs.getFields().get(5).getDouble());
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
