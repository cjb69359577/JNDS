package com.jiuqi.jnds05.common.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.jnds05.common.bean.CarSituationIntf;


public class CarSituationDao {
	/**
	 * 查询sql
	 * @return
	 */
	public String GetQuerySql(){
		StringBuilder resultsql=new StringBuilder();
		resultsql.append("  SELECT C.STDNAME  AS ZCFL,G.BILLCODE AS ZCBM, ");
		resultsql.append("  G.CHEPH AS  CPH,  G.JIAZ AS CVALUE,");
		resultsql.append("  G.LEIJZJ AS LJZJ,G.JINGZ AS JVALUE, ");
		resultsql.append("  SYZK.STDNAME AS SYQK, CLYT.STDNAME AS CLYT ");
		resultsql.append("  FROM GAMS_ASSETCARD G ");
		resultsql.append("  INNER JOIN (SELECT G.OBJECTID, MAX(G.JIZRQ) JIZRQ ");
		resultsql.append("  FROM GAMS_ASSETCARD G  ");
		resultsql.append("  JOIN GAMS_JC_ASSETCLASS C ON C.RECID = G.ZIFL ");
		resultsql.append("  INNER JOIN MD_ORG M ON M.RECID = G.ORGUNIT ");
		resultsql.append("  WHERE  ");
		resultsql.append("  G.JIZRQ <= TIMESTAMP'2019-12-31 23:59:59' ");
		resultsql.append("  AND ((C.STDCODE > '2030000' AND C.STDCODE < '2030800') OR C.STDCODE = '2039900') ");
		resultsql.append("  AND G.CARDSTATE IN ('00', '02') ");
		resultsql.append("  AND M.STDCODE = '000223' ");
		resultsql.append("  GROUP BY G.OBJECTID) CA ");
		resultsql.append("  ON CA.OBJECTID = G.OBJECTID ");
		resultsql.append("  AND CA.JIZRQ = G.JIZRQ  ");
		resultsql.append("  INNER JOIN GAMS_JC_ASSETCLASS C ");
		resultsql.append("  ON C.RECID = G.ZIFL  ");
		resultsql.append("  INNER JOIN GAMS_JC_SYZK SYZK  ");
		resultsql.append("  ON SYZK.RECID = G.SYZK  ");
		resultsql.append("  INNER JOIN GAMS_JC_CLYTFL CLYT ");
		resultsql.append("  ON CLYT.RECID = G.CLYT ");
		return resultsql.toString();
	}
	/**
	 * 获取车辆信息结果集
	 * @param context
	 * @return
	 */
	public List<CarSituationIntf> GetAllCarSituationResult(Context context){
		List<CarSituationIntf> resultlist=new ArrayList<CarSituationIntf>();
		Connection  conn = context.get(Connection.class);
		String querysql=GetQuerySql();
		ResultSet rs=null;
		try {
			rs=conn.prepareStatement(querysql).executeQuery();
			 while (rs.next()) {
				 CarSituationIntf tempintf=new CarSituationIntf();
				//资产分类
				 tempintf.setZcfl(rs.getString(1));
				 //资产编号
				 tempintf.setZcbh(rs.getString(2));
				 //车牌号
				 tempintf.setCph(rs.getString(3));
				 //价值 
				 tempintf.setCvalue(String.format("%.2f", rs.getDouble(4)));
				 //累计折旧
				 tempintf.setLjzj(String.format("%.2f", rs.getDouble(5)));
				 //净值
				 tempintf.setJvalue(String.format("%.2f", rs.getDouble(6)));
				 //使用情况
				 tempintf.setSyqk(rs.getString(7));
				 //车辆用途
				 tempintf.setClyt(rs.getString(8));
				 resultlist.add(tempintf);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultlist;
		
	}
}
