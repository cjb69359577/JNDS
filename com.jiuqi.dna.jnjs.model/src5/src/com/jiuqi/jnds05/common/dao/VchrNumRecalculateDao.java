package com.jiuqi.jnds05.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.spi.application.ContextSPI;

public class VchrNumRecalculateDao {
	/**
	 * 查询凭证断号的数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryVchrNumNoSustainData(ContextSPI ctx) throws SQLException {
		ResultSet rs = null;
		Connection conn = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = ctx.get(Connection.class);
			StringBuffer sb = new StringBuffer();
			sb.append(" select m.unitID,m.acctyear,m.acctperiod,n.stdcode,n.stdname from( \n");
			sb.append(" select unitID,acctyear,acctperiod,chaz,shul  \n");
			sb.append("  from (  \n");
			sb.append(
					"  select  unitID,acctyear,acctperiod,max(vchrnum)-min(vchrnum)+1 as  chaz ,count(1) as shul,count(distinct vchrnum) as shul2 from  GL_VOUCHER where acctperiod <>0 group by unitID,acctyear,acctperiod order by unitID,acctyear,acctperiod) \n");
			sb.append(" where chaz<>shul or shul<>shul2 ) m \n");
			sb.append(" left join MD_ORG n on m.unitID=n.recid \n");
			rs = conn.createStatement().executeQuery(sb.toString());
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("unitID", rs.getString(1));
				map.put("acctyear", rs.getInt(2));
				map.put("acctperiod", rs.getInt(3));
				map.put("unitCode", rs.getString(4));
				map.put("unitName", rs.getString(5));
				list.add(map);
			}
		} finally {
			// TODO: handle finally clause
			// if(rs!=null){
			// try {
			// rs.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// rs =null;
			// }
			// if(conn!=null){
			// try {
			// conn.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// conn=null;
			// }
		}
		return list;
	}

	/**
	 * 更新断号的凭证数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> updateVchrNumRecalculate(ContextSPI ctx, List<Map<String, Object>> listMap)
			throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = ctx.get(Connection.class);
			StringBuffer sb = new StringBuffer();
			// sb.append(" update GL_VOUCHER a set a.VCHRNUM= \n");
			// sb.append(" (select b.rn from (select recid,sum(1) over( order by
			// createdate,VCHRNUM,ROWNUM ) rn from GL_VOUCHER where unitID=? and
			// acctyear=? and acctperiod=? ) b where a.recid=b.recid) \n");
			// sb.append(" where a.unitID=? and a.acctyear=? and a.acctperiod=?
			// \n");
			sb.append(" MERGE INTO GL_VOUCHER a \n");
			sb.append(
					" USING (select recid,sum(1) over( order by createdate,ROWNUM ) as rn from GL_VOUCHER where  unitID=? and acctyear=? and acctperiod=?)  b \n");
			sb.append("  ON ( a.recid =b.recid) \n");
			sb.append("  WHEN MATCHED THEN \n");
			sb.append("   UPDATE  SET a.VCHRNUM = b.rn  \n");

			prepareStatement = conn.prepareStatement(sb.toString());
			for (Map<String, Object> lm : listMap) {
				// 获取当前时间为开始时间，转换为long型
				long startTime = fromDateStringToLong(
						new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
				prepareStatement.setString(1, lm.get("unitID").toString());
				prepareStatement.setInt(2, Integer.valueOf(lm.get("acctyear").toString()));
				prepareStatement.setInt(3, Integer.valueOf(lm.get("acctperiod").toString()));
				// prepareStatement.setString(4, lm.get("unitID").toString());
				// prepareStatement.setInt(5,
				// Integer.valueOf(lm.get("acctyear").toString()));
				// prepareStatement.setInt(6,
				// Integer.valueOf(lm.get("acctperiod").toString()));
				int rows = prepareStatement.executeUpdate();
				// 获取当前时间为截止时间，转换为long型
				long stopTime = fromDateStringToLong(
						new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
				// 计算时间差,单位毫秒
				long timeSpan = stopTime - startTime;
				if (rows > 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("acctyear", lm.get("acctyear").toString());
					map.put("unitCode", lm.get("unitCode").toString());
					map.put("unitName", lm.get("unitName").toString());
					map.put("acctperiod", lm.get("acctperiod").toString());
					map.put("runtime", timeSpan + "毫秒");
					list.add(map);

				}

			}
		} finally {
			ctx.dispose();
			// TODO: handle finally clause
			// if(rs!=null){
			// try {
			// rs.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// rs =null;
			// }
			//
			// if(prepareStatement!=null){
			// try {
			// prepareStatement.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// prepareStatement=null;
			// }
			//
			// if(conn!=null){
			// try {
			// conn.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// conn=null;
			// }
		}

		return list;
	}

	/**
	 * 根据String型时间，获取long型时间，单位毫秒
	 * 
	 * @param inVal
	 *            时间字符串
	 * @return long型时间
	 */
	public static long fromDateStringToLong(String inVal) {
		Date date = null;
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		try {
			date = inputFormat.parse(inVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

}
