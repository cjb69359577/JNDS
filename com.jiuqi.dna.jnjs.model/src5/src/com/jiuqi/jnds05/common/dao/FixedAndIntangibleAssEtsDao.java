package com.jiuqi.jnds05.common.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
public class FixedAndIntangibleAssEtsDao {
	/**
	 * 查询结果
	 * 并返回
	 * 结果集
	 */
	private StringBuffer sb = null;
	public List<Map<String, String>> getList(Context ctx, String orgCode,String[] assTypeS) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		Map<String, String> map = null;
		Connection  conn = ctx.get(Connection.class);
		String sql = null;
		ResultSet rs = null;
		Statement createStatement =null;
		String rowTitle[]={"合计","一、固定资产  ","  (一)土地、房屋及构筑物","  (二)通用设备(个、台、辆等)","  (三）专用设备(个、台等)",
				"  (四)文物和陈列品(个、件等)","  (五)图书档案（本、套等)","  (六)家具、用具、装具及动植物（个、套等)","二、无形资产 "};
		String colTitle[]={"start_shul","start_jiaz","start_leijzj","start_jingz",
							"end_shul","end_jiaz","end_leijzj","end_jingz",
							"end_use_shul", "end_use_jiaz","end_use_leijzj","end_use_jingz",
							"end_rent_shul","end_rent_jiaz","end_rent_leijzj","end_rent_jingz",
							"end_unuse_shul","end_unuse_jiaz", "end_unuse_leijzj","end_unuse_jingz", 
							"end_disposal_shul","end_disposal_jiaz","end_disposal_leijzj","end_disposal_jingz"};
		/**
		 * 先把 
		 * 1.土地、房屋及构筑物
		 * 2.无形资产    
		 *  期末在用、出租出借、闲置、待处置 的 价值 计算出来
		 */
		// 废弃 查询库次数太多 效率太低 废弃
/*		Map<String, Double> tdAndWxzcMap = getTdAndWxzcMap(conn, rs);
		Map<String, Double> totle = initMap();
		Map<String, Double> gdzcTotle = initMap();
		try {
			createStatement = conn.createStatement();
			for (int i = 0; i < assTypeS.length; i++) {
				*//**
				 * 期初的数据
				 *//*
				map = new HashMap<String, Double>();
				sql = String.format(totalSql(assTypeS[i]),"2018-12-31","01","02","03","04");
				rs = createStatement.executeQuery(sql);
				while (rs.next()) {
					map.put("start_shul", rs.getDouble("shul"));
					map.put("start_jiaz", rs.getDouble("jiaz"));
					map.put("start_leijzj", rs.getDouble("leijzj"));
					map.put("start_jingz", rs.getDouble("jingz"));
				}
				addDataForTotle(totle,gdzcTotle,i,map,"start_");
				*//**
				 * 期末 合计的数据
				 *//*
				 sql = String.format(totalSql(assTypeS[i]), "2019-12-31","01","02","03","04");
				 rs = createStatement.executeQuery(sql);
				 while (rs.next()) {
					map.put("end_shul", rs.getDouble("shul"));
					map.put("end_jiaz", rs.getDouble("jiaz"));
					map.put("end_leijzj", rs.getDouble("leijzj"));
					map.put("end_jingz", rs.getDouble("jingz"));
				 }
				 addDataForTotle(totle,gdzcTotle,i,map,"end_");				 
				 *//**
				  * 期末 在用的数据
				  * 
				  *//*
			     sql = String.format(totalSql(assTypeS[i]), "2019-12-31","01","01","01","01");
				 rs = createStatement.executeQuery(sql);
				 while (rs.next()) {
				    map.put("end_use_shul", rs.getDouble("shul"));
				    if(i==0 || i== 6)
				    	map.put("end_use_jiaz", tdAndWxzcMap.get(i+"end_use_jiaz"));
					else
					 map.put("end_use_jiaz", rs.getDouble("jiaz"));
					map.put("end_use_leijzj", rs.getDouble("leijzj"));
					map.put("end_use_jingz", rs.getDouble("jingz"));
				 }
				 addDataForTotle(totle,gdzcTotle,i,map,"end_use_");		
				 *//**
				  *  期末出租出借的数据
				  *//*
			     sql = String.format(totalSql(assTypeS[i]), "2019-12-31","02","02","02","02");
				 rs = createStatement.executeQuery(sql);
				 while (rs.next()) {
					map.put("end_rent_shul", rs.getDouble("shul"));
					if(i==0 || i== 6)
				    	map.put("end_rent_jiaz", tdAndWxzcMap.get(i+"end_rent_jiaz"));
					else
					 map.put("end_rent_jiaz", rs.getDouble("jiaz"));
					map.put("end_rent_leijzj", rs.getDouble("leijzj"));
					map.put("end_rent_jingz", rs.getDouble("jingz"));
				 }
				 addDataForTotle(totle,gdzcTotle,i,map,"end_rent_");		
				 *//**
				  *  期末 闲置的数据
				  *//*
			     sql = String.format(totalSql(assTypeS[i]), "2019-12-31","03","03","03","03");
				 rs = createStatement.executeQuery(sql);
				 while (rs.next()) {
					map.put("end_unuse_shul", rs.getDouble("shul"));
					if(i==0 || i== 6)
				    	map.put("end_unuse_jiaz", tdAndWxzcMap.get(i+"end_unuse_jiaz"));
					else
					 map.put("end_unuse_jiaz", rs.getDouble("jiaz"));
					map.put("end_unuse_leijzj", rs.getDouble("leijzj"));
					map.put("end_unuse_jingz", rs.getDouble("jingz"));
				 }
				 addDataForTotle(totle,gdzcTotle,i,map,"end_unuse_");
				 *//**
				  *  期末 待处置的数据
				  *//*
			     sql = String.format(totalSql(assTypeS[i]), "2019-12-31","04","04","04","04");
				 rs = createStatement.executeQuery(sql);
				 while (rs.next()) {
					 map.put("end_disposal_shul", rs.getDouble("shul"));
					if(i==0 || i== 6)
				    	map.put("end_disposal_jiaz", tdAndWxzcMap.get(i+"end_disposal_jiaz"));
					else
						map.put("end_disposal_jiaz", rs.getDouble("jiaz"));
					 map.put("end_disposal_leijzj", rs.getDouble("leijzj"));
					 map.put("end_disposal_jingz", rs.getDouble("jingz"));
				 }
				 addDataForTotle(totle,gdzcTotle,i,map,"end_disposal_");
			     result.add(map) ;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			
		}*/
		
		/*lastResult.add(totle);
		lastResult.add(gdzcTotle);
		lastResult.addAll(result);*/
		//List<Map<String,Double>> lastResult = new ArrayList<Map<String,Double>>();
		List<Double> TwentyEighteenList = new ArrayList<Double>();
		List<Double> TwentyNineteenList = new ArrayList<Double>();
		DecimalFormat dobleDf=new DecimalFormat("##0.00");
		String intCol[]={"0","4","8","12","16","20"};//整型的列
        List<String> intColist = Arrays.asList(intCol);
		try {
			createStatement = conn.createStatement();
			sql = getTwentyNineteenSql();// 2019年期末所有的单元格数据
			rs = createStatement.executeQuery(sql);
			while (rs.next()) {
				// 2019 一共180 个列 20*9
				for (int j = 1; j < 181; j++) {
					TwentyNineteenList.add(rs.getDouble(j));
				}
			}
			
			sql = getTwentyEighteenSql();// 查询2018年所有单元格数量
			rs = createStatement.executeQuery(sql);
			while (rs.next()) {
				// 2018 一共36 个girdeCell
				for (int j = 1; j < 37; j++) {// 9*4
					TwentyEighteenList.add(rs.getDouble(j));
				}
			}
			for (int j = 0; j <= rowTitle.length; j++) {
				map = new HashMap<String, String>();
				map.put("title", rowTitle[j]);
				for (int j2 = 0; j2 < colTitle.length; j2++) {
					if(j2 < 4){
						boolean notShow =TwentyEighteenList.get(j + j2*9) == 999.99;
						if(notShow)
							map.put(colTitle[j2], "NULL");
						else
							map.put(colTitle[j2], intColist.contains(j2+"")?TwentyEighteenList.get(j + j2*9).intValue()+"":dobleDf.format(TwentyEighteenList.get(j+j2*9)));
					}else{ 
						boolean notShow =TwentyNineteenList.get(j + (j2-4)*9) == 999.99;
						if(notShow)
							map.put(colTitle[j2], "NULL");
						else
						  map.put(colTitle[j2],  intColist.contains(j2+"")?TwentyNineteenList.get(j + (j2-4)*9).intValue()+"":dobleDf.format(TwentyNineteenList.get(j + (j2-4)*9)));
					}
				}
				result.add(map);
			}
		} catch (Exception e) {
			
		}
		
		return result;
	}


	public List<Map<String, String>> getShowInfo(Context ctx, String orgCode,String[] assTypeS) {
		   List<Map<String, String>> list = this.getList(ctx, orgCode, assTypeS);
		  Map< String , String > map = null;
		  Map< String , String > newMap = new HashMap<String, String>();
		  List<Map<String, String>> newList = new ArrayList<Map<String,String>>();
		  for (int i = 0; i < list.size(); i++) {
			  map = list.get(i);
			  newMap.put(i+"", map.get("end_jiaz"));
		  }
		  newList.add(newMap);// 第一行数据是  期末账面数据 价值 H 列数据
		  
		  newMap = new HashMap<String, String>();
		  map = list.get(0);
		  newMap.put("0", map.get("end_use_jiaz"));
		  newMap.put("1", map.get("end_rent_jiaz"));
		  newMap.put("2", map.get("end_unuse_jiaz"));
		  newMap.put("3", map.get("end_disposal_jiaz"));
		  /*newMap.put("end_use_jiaz", map.get("end_use_jiaz"));
		  newMap.put("end_rent_jiaz", map.get("end_rent_jiaz"));
		  newMap.put("end_unuse_jiaz", map.get("end_unuse_jiaz"));
		  newMap.put("end_disposal_jiaz", map.get("end_disposal_jiaz"));*/
		  newList.add(newMap); // 第二行数据是   【L5】、【P5】、【T5】、【X5】信息，即按照资产使用状况：“在用”、“出租借”、“闲置”、“待处置（待报废、毁损等）”展示。
		  return newList;
	 }
	
private String getTwentyEighteenSql() {
			sb = new StringBuffer();
			sb.append(" select 999.99 c5,999.99 c6,999.99 c7, sum(case when cla.stdcode like '2%' then card.shul else 0 end) c8,");
			sb.append(" sum(case when cla.stdcode like '3%' then card.shul else 0 end) c9,");
			sb.append(" sum(case when cla.stdcode like '4%' then card.shul else 0 end) c10,");
			sb.append(" sum(case when cla.stdcode like '5%' then card.shul else 0 end) c11,");
			sb.append(" sum(case when cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%' then card.shul else 0 end) c12, 999.99 c13,");
			 
			sb.append(" sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%'or cla.stdcode like '605%' then card.jiaz else 0 end) D5,");
			sb.append(" sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%' then card.jiaz else 0 end) D6,");
			sb.append(" sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' or cla.stdcode like '103%' then card.jiaz else 0 end) d7 ,");
			sb.append("  sum(case when cla.stdcode like '2%' then card.jiaz else 0 end) d8,");
			sb.append(" sum(case when cla.stdcode like '3%' then card.jiaz else 0 end) d9,");
			sb.append(" sum(case when cla.stdcode like '4%' then card.jiaz else 0 end)d10,");
			sb.append(" sum(case when cla.stdcode like '5%' then card.jiaz else 0 end) d11,");
			sb.append(" sum(case when cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%' then card.jiaz else 0 end) d12,");
			sb.append(" sum(case when cla.stdcode like '605%' then card.jiaz else 0 end) d13,");
			
			  
			sb.append(" sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%'or cla.stdcode like '605%' then card.leijzj else 0 end) E5,");
			sb.append(" sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%' then card.leijzj else 0 end) E6,");
			sb.append(" sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' or cla.stdcode like '103%' then card.leijzj else 0 end) e7 ,"); 
			sb.append("  sum(case when cla.stdcode like '2%' then card.leijzj else 0 end) e8,");
			sb.append(" sum(case when cla.stdcode like '3%' then card.leijzj else 0 end) e9,");
			sb.append(" sum(case when cla.stdcode like '4%' then card.leijzj else 0 end) e10,");
			sb.append(" sum(case when cla.stdcode like '5%' then card.leijzj else 0 end) e11,");
			sb.append(" sum(case when cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%' then card.leijzj else 0 end) e12,"); 
			sb.append("  sum(case when cla.stdcode like '605%' then card.leijzj else 0 end) e13,");
			
			sb.append("  sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%'or cla.stdcode like '605%' then card.jingz else 0 end)F5,");
			sb.append("  sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%' then card.jingz else 0 end) F6,");
			sb.append("  sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' or cla.stdcode like '103%' then card.jingz else 0 end) F7 ,");
			sb.append(" sum(case when cla.stdcode like '2%' then card.jingz else 0 end) F8,");
			sb.append(" sum(case when cla.stdcode like '3%' then card.jingz else 0 end) F9,");
			sb.append(" sum(case when cla.stdcode like '4%' then card.jingz else 0 end) F10,");
			sb.append(" sum(case when cla.stdcode like '5%' then card.jingz else 0 end) F11,");
			sb.append(" sum(case when cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%' then card.jingz else 0 end) F12,");
			sb.append(" sum(case when cla.stdcode like '605%' then card.jingz else 0 end) F13");
			 
			 sb.append("  from gams_assetcard card");
			 sb.append(" left join gams_jc_assetclass cla on card.zifl = cla.recid");
			 sb.append(" left join gams_jc_syzk syzk on card.syzk = syzk.recid  ,md_org org ,");
			 sb.append("     (select c.objectid, max(c.jizrq) jizrq");
			 sb.append("   from gams_assetcard c");
			 sb.append("    where to_char(c.jizrq,'yyyy-MM-dd') < = '2018-12-31'");
			 sb.append("      group by c.objectid) lasec");
			 sb.append(" where lasec.objectid = card.objectid");
			 sb.append(" and lasec.jizrq = card.jizrq");
			 sb.append(" and card.cardstate in ('00','02')");
			 sb.append(" and card.orgunit = org.recid");
			 sb.append("  and org.stdcode = '000223'");
			
			return sb.toString();
}
	
public String getTwentyNineteenSql(){
	sb = new StringBuffer();
	sb.append("select 999.99 g5,999.99 g6,999.99 g7,");
	sb.append("sum(case when cla.stdcode like '2%' then card.shul else 0 end) g8,");
	sb.append("sum(case when cla.stdcode like '3%' then card.shul else 0 end) g9,");
	sb.append(" sum(case when cla.stdcode like '4%' then card.shul else 0 end) g10,");
	sb.append(" sum(case when cla.stdcode like '5%' then card.shul else 0 end) g11,");
	sb.append("sum(case when cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%' then card.shul else 0 end) g12, 999.99  g13,");
	sb.append(" sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%'or cla.stdcode like '605%' then card.jiaz else 0 end) h5,");
	sb.append("sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%' then card.jiaz else 0 end) h6,");
	sb.append(" sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' or cla.stdcode like '103%' then card.jiaz else 0 end) h7,");
	sb.append("  sum(case when cla.stdcode like '2%' then card.jiaz else 0 end) h8,");
	sb.append("sum(case when cla.stdcode like '3%' then card.jiaz else 0 end) h9,");
	sb.append(" sum(case when cla.stdcode like '4%' then card.jiaz else 0 end) h10,");
	sb.append("sum(case when cla.stdcode like '5%' then card.jiaz else 0 end) h11,");
	sb.append("  sum(case when cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%'  then card.jiaz else 0 end) h12,");
	sb.append("  sum(case when cla.stdcode like '605%' then card.jiaz else 0 end) h13,");
	 
	sb.append("  sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%'or cla.stdcode like '605%' then card.leijzj else 0 end) i5,");
	sb.append("  sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%' then card.leijzj else 0 end) i6,");
	sb.append("  sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' or cla.stdcode like '103%' then card.leijzj else 0 end) i7,");
	sb.append("  sum(case when cla.stdcode like '2%' then card.leijzj else 0 end) i8,");
	sb.append("  sum(case when cla.stdcode like '3%' then card.leijzj else 0 end) i9,");
	sb.append("  sum(case when cla.stdcode like '4%' then card.leijzj else 0 end) i10,");
	sb.append("  sum(case when cla.stdcode like '5%' then card.leijzj else 0 end) i11,");
   sb.append("  sum(case when cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%'  then card.leijzj else 0 end) i12,");
   sb.append("  sum(case when cla.stdcode like '605%' then card.leijzj else 0 end) i13,");
 
   sb.append(" sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%'or cla.stdcode like '605%' then card.jingz else 0 end) j5,");
   sb.append("  sum(case when cla.stdcode like '1%'or cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%' or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%'or cla.stdcode like '604%' then card.jingz else 0 end) j6,");
   sb.append("  sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' or cla.stdcode like '103%' then card.jingz else 0 end) j7,");
   sb.append("  sum(case when cla.stdcode like '2%' then card.jingz else 0 end) j8,");
   sb.append("  sum(case when cla.stdcode like '3%' then card.jingz else 0 end) j9,");
   sb.append("  sum(case when cla.stdcode like '4%' then card.jingz else 0 end) j10,");
   sb.append("  sum(case when cla.stdcode like '5%' then card.jingz else 0 end) j11,");
   sb.append("  sum(case when cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%'  then card.jingz else 0 end) j12,");
   sb.append("  sum(case when cla.stdcode like '605%' then card.jingz else 0 end) j13,");
	 
   sb.append("  999.99 k5, 999.99 k6, 999.99 k7,"); 
   sb.append(" sum(case when cla.stdcode like '2%'and syzk.stdcode = '01' then card.shul else 0 end) k8,");
   sb.append(" sum(case when cla.stdcode like '3%'and syzk.stdcode = '01' then card.shul else 0 end) k9,");
   sb.append("  sum(case when cla.stdcode like '4%'and syzk.stdcode = '01' then card.shul else 0 end) k10,");
   sb.append("   sum(case when cla.stdcode like '5%'and syzk.stdcode = '01' then card.shul else 0 end) k11,");
   sb.append("   sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '01' then card.shul else 0 end) k12,999.99 k13,");
	 
   sb.append("  sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.zyjz else case when cla.stdcode like '103%'and syzk.stdcode = '01'  then card.jiaz else 0 end end) + ");
   sb.append(" sum(case when (cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%'or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '01'  then card.jiaz else 0 end) + ");
   sb.append(" sum(case when cla.stdcode like '6050411%' then card.zyjz else case when cla.stdcode like '605%' and syzk.stdcode = '01' then card.jiaz else  0 end end) l5,");
   sb.append(" sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.zyjz else case when cla.stdcode like '103%'and syzk.stdcode = '01'  then card.jiaz else 0 end end) + ");
   sb.append(" sum(case when (cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%'or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '01'  then card.jiaz else 0 end) l6, ");    
   sb.append(" sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.zyjz else case when cla.stdcode like '103%'and syzk.stdcode = '01'  then card.jiaz else 0 end end) l7,");
   sb.append("  sum(case when cla.stdcode like '2%'and syzk.stdcode = '01' then card.jiaz else 0 end) l8,");
   sb.append("  sum(case when cla.stdcode like '3%'and syzk.stdcode = '01' then card.jiaz else 0 end) l9,");
   sb.append("   sum(case when cla.stdcode like '4%'and syzk.stdcode = '01' then card.jiaz else 0 end) l10,");
   sb.append("  sum(case when cla.stdcode like '5%'and syzk.stdcode = '01' then card.jiaz else 0 end) l11,");
   sb.append(" sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '01'  then card.jiaz else 0 end) l12,");
   sb.append("   sum(case when cla.stdcode like '6050411%' then card.zyjz else case when cla.stdcode like '605%' and syzk.stdcode = '01' then card.jiaz else  0 end end) l13,");
	 
   sb.append("  999.99 m5,999.99 m6,999.99 m7,");
   sb.append("   sum(case when cla.stdcode like '2%'and syzk.stdcode = '01' then card.leijzj else 0 end) m8,");
   sb.append("    sum(case when cla.stdcode like '3%'and syzk.stdcode = '01' then card.leijzj else 0 end) m9,");
   sb.append("    sum(case when cla.stdcode like '4%'and syzk.stdcode = '01' then card.leijzj else 0 end) m10,");
   sb.append("    sum(case when cla.stdcode like '5%'and syzk.stdcode = '01' then card.leijzj else 0 end) m11,");
   sb.append("   sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '01' then card.leijzj else 0 end) m12, 999.99 m13,");
	 
   sb.append("   999.99 n5,999.99 n6,999.99 n7,");
   sb.append("    sum(case when cla.stdcode like '2%'and syzk.stdcode = '01' then card.jingz else 0 end) n8,");
   sb.append("     sum(case when cla.stdcode like '3%'and syzk.stdcode = '01' then card.jingz else 0 end) n9,");
   sb.append("    sum(case when cla.stdcode like '4%'and syzk.stdcode = '01' then card.jingz else 0 end) n10,");
   sb.append("     sum(case when cla.stdcode like '5%'and syzk.stdcode = '01' then card.jingz else 0 end) n11,");
   sb.append("     sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '01' then card.jingz else 0 end) n12, 999.99 n13,");
	 
   sb.append("   999.99 o5,999.99 o6,999.99 o7,");
   sb.append("     sum(case when cla.stdcode like '2%'and syzk.stdcode = '02' then card.shul else 0 end) o8,");
   sb.append("     sum(case when cla.stdcode like '3%'and syzk.stdcode = '02' then card.shul else 0 end) o9,");
   sb.append("     sum(case when cla.stdcode like '4%'and syzk.stdcode = '02' then card.shul else 0 end) o10,");
   sb.append("       sum(case when cla.stdcode like '5%'and syzk.stdcode = '02' then card.shul else 0 end) o11,");
   sb.append("    sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '02' then card.shul else 0 end) o12,999.99 o13,");
	 
   sb.append("   sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.czjjz else case when cla.stdcode like '103%'and syzk.stdcode = '02'  then card.jiaz else 0 end end) + ");
   sb.append("   sum(case when (cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%'or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '02'  then card.jiaz else 0 end) + ");
   sb.append("   sum(case when cla.stdcode like '6050411%' then card.czjjz else case when cla.stdcode like '605%' and syzk.stdcode = '02' then card.jiaz else  0 end end) p5,");
   sb.append("   sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.czjjz else case when cla.stdcode like '103%'and syzk.stdcode = '02'  then card.jiaz else 0 end end) + ");
   sb.append("   sum(case when (cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%'or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '02'  then card.jiaz else 0 end) p6,");
   sb.append("    sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.czjjz else case when cla.stdcode like '103%'and syzk.stdcode = '02'  then card.jiaz else 0 end end) p7,");
   sb.append("   sum(case when cla.stdcode like '2%'and syzk.stdcode = '02' then card.jiaz else 0 end) p8,");
   sb.append("   sum(case when cla.stdcode like '3%'and syzk.stdcode = '02' then card.jiaz else 0 end) p9,");
   sb.append("  sum(case when cla.stdcode like '4%'and syzk.stdcode = '02' then card.jiaz else 0 end) p10,");
   sb.append("   sum(case when cla.stdcode like '5%'and syzk.stdcode = '02' then card.jiaz else 0 end) p11,");
   sb.append("   sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '02'  then card.jiaz else 0 end) p12,");
   sb.append("   sum(case when cla.stdcode like '6050411%' then card.czjjz else case when cla.stdcode like '605%' and syzk.stdcode = '02' then card.jiaz else  0 end end) p13,");
 
   sb.append("   999.99 q5,999.99 q6,999.99 q7,");
   sb.append("   sum(case when cla.stdcode like '2%'and syzk.stdcode = '02' then card.leijzj else 0 end) q8,");
   sb.append("  sum(case when cla.stdcode like '3%'and syzk.stdcode = '02' then card.leijzj else 0 end) q9,");
   sb.append("    sum(case when cla.stdcode like '4%'and syzk.stdcode = '02' then card.leijzj else 0 end) q10,");
   sb.append("    sum(case when cla.stdcode like '5%'and syzk.stdcode = '02' then card.leijzj else 0 end) q11,");
   sb.append("    sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '02' then card.leijzj else 0 end) q12,999.99 q13,");
 
   sb.append("    999.99 r5,999.99 r6,999.99 r7,");
   sb.append("    sum(case when cla.stdcode like '2%'and syzk.stdcode = '02' then card.jingz else 0 end) r8,");
   sb.append("    sum(case when cla.stdcode like '3%'and syzk.stdcode = '02' then card.jingz else 0 end) r9,");
   sb.append("   sum(case when cla.stdcode like '4%'and syzk.stdcode = '02' then card.jingz else 0 end) r10,");
   sb.append("  sum(case when cla.stdcode like '5%'and syzk.stdcode = '02' then card.jingz else 0 end) r11,");
   sb.append("  sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '02' then card.jingz else 0 end) r12,999.99 r13,");
 
   sb.append("   999.99 s5,999.99 s6,999.99 s7,");
   sb.append("    sum(case when cla.stdcode like '2%'and syzk.stdcode = '03' then card.shul else 0 end) s8,");
   sb.append("    sum(case when cla.stdcode like '3%'and syzk.stdcode = '03' then card.shul else 0 end) s9,");
   sb.append("   sum(case when cla.stdcode like '4%'and syzk.stdcode = '03' then card.shul else 0 end) s10,");
   sb.append("   sum(case when cla.stdcode like '5%'and syzk.stdcode = '03' then card.shul else 0 end) s11,");
   sb.append("   sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '03' then card.shul else 0 end) s12,999.99 s13,");
 
   sb.append("  sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.xzjz else case when cla.stdcode like '103%'and syzk.stdcode = '03'  then card.jiaz else 0 end end) + ");
   sb.append("   sum(case when (cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%'or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '03'  then card.jiaz else 0 end) + ");
   sb.append("  sum(case when cla.stdcode like '6050411%' then card.xzjz else case when cla.stdcode like '605%' and syzk.stdcode = '03' then card.jiaz else  0 end end) t5,");
   sb.append("  sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.xzjz else case when cla.stdcode like '103%'and syzk.stdcode = '03'  then card.jiaz else 0 end end) + ");
   sb.append("    sum(case when (cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%'or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '03'  then card.jiaz else 0 end) t6,");
   sb.append("    sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.xzjz else case when cla.stdcode like '103%'and syzk.stdcode = '03'  then card.jiaz else 0 end end) t7,");
   sb.append("   sum(case when cla.stdcode like '2%'and syzk.stdcode = '03' then card.jiaz else 0 end) t8,");
   sb.append("   sum(case when cla.stdcode like '3%'and syzk.stdcode = '03' then card.jiaz else 0 end) t9,");
   sb.append("   sum(case when cla.stdcode like '4%'and syzk.stdcode = '03' then card.jiaz else 0 end) t10,");
   sb.append("   sum(case when cla.stdcode like '5%'and syzk.stdcode = '03' then card.jiaz else 0 end) t11,");
   sb.append("   sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '03'  then card.jiaz else 0 end) t12,");
   sb.append("    sum(case when cla.stdcode like '6050411%' then card.xzjz else case when cla.stdcode like '605%' and syzk.stdcode = '03' then card.jiaz else  0 end end) t13,");
 
   sb.append("    999.99 u5,999.99 u6,999.99 u7,");
   sb.append("   sum(case when cla.stdcode like '2%'and syzk.stdcode = '03' then card.leijzj else 0 end) u8,");
   sb.append("   sum(case when cla.stdcode like '3%'and syzk.stdcode = '03' then card.leijzj else 0 end) u9,");
   sb.append("   sum(case when cla.stdcode like '4%'and syzk.stdcode = '03' then card.leijzj else 0 end) u10,");
   sb.append("   sum(case when cla.stdcode like '5%'and syzk.stdcode = '03' then card.leijzj else 0 end) u11,");
   sb.append("    sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '03' then card.leijzj else 0 end) u12,999.99 u13,");
 
   sb.append("     999.99 v5,999.99 v6,999.99 v7,");
   sb.append("      sum(case when cla.stdcode like '2%'and syzk.stdcode = '03' then card.jingz else 0 end) v8,");
   sb.append("      sum(case when cla.stdcode like '3%'and syzk.stdcode = '03' then card.jingz else 0 end) v9,");
   sb.append("      sum(case when cla.stdcode like '4%'and syzk.stdcode = '03' then card.jingz else 0 end) v10,");
   sb.append("      sum(case when cla.stdcode like '5%'and syzk.stdcode = '03' then card.jingz else 0 end) v11,");
   sb.append("     sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '03' then card.jingz else 0 end) v12,999.99 v13,");
 
   sb.append("   999.99 w5,999.99 w6,999.99 w7,");
   sb.append("    sum(case when cla.stdcode like '2%'and syzk.stdcode = '04' then card.shul else 0 end) w8,");
   sb.append("    sum(case when cla.stdcode like '3%'and syzk.stdcode = '04' then card.shul else 0 end) w9,");
   sb.append("    sum(case when cla.stdcode like '4%'and syzk.stdcode = '04' then card.shul else 0 end) w10,");
   sb.append("    sum(case when cla.stdcode like '5%'and syzk.stdcode = '04' then card.shul else 0 end) w11,");
   sb.append("   sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '04' then card.shul else 0 end) w12, 999.99 w13,");
 
   sb.append("   sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.dczjz else case when cla.stdcode like '103%'and syzk.stdcode = '04'  then card.jiaz else 0 end end) + ");
   sb.append("  sum(case when (cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%'or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '04'  then card.jiaz else 0 end) + ");
   sb.append("  sum(case when cla.stdcode like '6050411%' then card.dczjz else case when cla.stdcode like '605%' and syzk.stdcode = '04' then card.jiaz else  0 end end) x5,");
   sb.append("  sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.dczjz else case when cla.stdcode like '103%'and syzk.stdcode = '04'  then card.jiaz else 0 end end) + ");
   sb.append("   sum(case when (cla.stdcode like '2%'or cla.stdcode like '3%'or cla.stdcode like '4%'or cla.stdcode like '5%'or cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '04'  then card.jiaz else 0 end) x6,");
   sb.append("   sum(case when cla.stdcode like '101%'or cla.stdcode like '102%' then card.dczjz else case when cla.stdcode like '103%'and syzk.stdcode = '04'  then card.jiaz else 0 end end) x7,");
   sb.append("    sum(case when cla.stdcode like '2%'and syzk.stdcode = '04' then card.jiaz else 0 end) x8,");
   sb.append("    sum(case when cla.stdcode like '3%'and syzk.stdcode = '04' then card.jiaz else 0 end) x9,");
   sb.append("    sum(case when cla.stdcode like '4%'and syzk.stdcode = '04' then card.jiaz else 0 end) x10,");
   sb.append("    sum(case when cla.stdcode like '5%'and syzk.stdcode = '04' then card.jiaz else 0 end) x11,");
   sb.append("    sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '04'  then card.jiaz else 0 end) x12,");
   sb.append("    sum(case when cla.stdcode like '6050411%' then card.dczjz else case when cla.stdcode like '605%' and syzk.stdcode = '04' then card.jiaz else  0 end end) x13,");
 
   sb.append("    999.99 y5,999.99 y6,999.99 y7,");
   sb.append("     sum(case when cla.stdcode like '2%'and syzk.stdcode = '04' then card.leijzj else 0 end) y8,");
   sb.append("     sum(case when cla.stdcode like '3%'and syzk.stdcode = '04' then card.leijzj else 0 end) y9,");
   sb.append("     sum(case when cla.stdcode like '4%'and syzk.stdcode = '04' then card.leijzj else 0 end) y10,");
   sb.append("     sum(case when cla.stdcode like '5%'and syzk.stdcode = '04' then card.leijzj else 0 end) y11,");
   sb.append("     sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '04' then card.leijzj else 0 end) y12,999.99 y13,");
 
   sb.append("       999.99 z5,999.99 z6,999.99 z7,");
   sb.append("     sum(case when cla.stdcode like '2%'and syzk.stdcode = '04' then card.jingz else 0 end) z8,");
   sb.append("    sum(case when cla.stdcode like '3%'and syzk.stdcode = '04' then card.jingz else 0 end) z9,");
   sb.append("     sum(case when cla.stdcode like '4%'and syzk.stdcode = '04' then card.jingz else 0 end) z10,");
   sb.append("     sum(case when cla.stdcode like '5%'and syzk.stdcode = '04' then card.jingz else 0 end) z11,");
   sb.append("    sum(case when (cla.stdcode like '601%'or cla.stdcode like '602%' or cla.stdcode like '603%' or cla.stdcode like '604%') and syzk.stdcode = '04' then card.jingz else 0 end) z12,999.99 z13");
   sb.append("  from gams_assetcard card");
   sb.append(" left join gams_jc_assetclass cla on card.zifl = cla.recid");
   sb.append("  left join gams_jc_syzk syzk on card.syzk = syzk.recid  ,md_org org ,");
   sb.append(" (select c.objectid, max(c.jizrq) jizrq");
   sb.append(" from gams_assetcard c");
   sb.append(" where to_char(c.jizrq,'yyyy-MM-dd') < = '2019-12-31'");
   sb.append(" group by c.objectid) lasec");
   sb.append(" where lasec.objectid = card.objectid");
   sb.append(" and lasec.jizrq = card.jizrq");
   sb.append("  and card.cardstate in ('00','02')");
   sb.append("  and card.orgunit = org.recid");
   sb.append(" and org.stdcode = '000223'");
   return sb.toString();
 }
	
	
}
