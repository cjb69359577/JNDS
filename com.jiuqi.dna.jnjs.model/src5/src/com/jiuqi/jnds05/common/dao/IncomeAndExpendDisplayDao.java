package com.jiuqi.jnds05.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;

public class IncomeAndExpendDisplayDao {
	
	public Map<String, Object> getIncomeAndExpendMouth(Context context,String param) throws SQLException{
		//List<IncomeAndExpendBean> 
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if("sr".equals(param)){
			// 收入数据
			sql.append(" select SUM(zb.credit-zb.debit) as data,t.acctperiod as mouth from GL_VOUCHER t ");
			sql.append(" left join md_org md on md.recid = t.unitid");
			sql.append(" left join GL_VOUCHERITEM zb on t.recid = zb.vchrid ");
			sql.append(" left join MD_ACCOUNTSUBJECT km on km.recid= zb.subjectid");
			sql.append(" where t.acctyear='2019' ");
			sql.append("    and md.stdcode='000223' ");
			sql.append("    and km.stdcode like '6%' ");
			sql.append("    group by t.acctperiod ");
			sql.append("    order by t.acctperiod ");
		} else if ("zc".equals(param)){
			// 支出数据
			sql.append(" select SUM(zb.debit-zb.credit) as data,t.acctperiod as mouth from GL_VOUCHER t ");
			sql.append(" left join md_org md on md.recid = t.unitid");
			sql.append(" left join GL_VOUCHERITEM zb on t.recid = zb.vchrid ");
			sql.append(" left join MD_ACCOUNTSUBJECT km on km.recid= zb.subjectid");
			sql.append(" where t.acctyear='2019' ");
			sql.append("    and md.stdcode='000223' ");
			sql.append("    and km.stdcode like '7%' ");
			sql.append("    group by t.acctperiod ");
			sql.append("    order by t.acctperiod ");
		}
		conn = context.get(Connection.class);
		ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
//			DecimalFormat df = new DecimalFormat("0.00");
		while (rs.next()) {
//				map.put(rs.getString(2), df.format(rs.getDouble(1)));
			map.put(rs.getString(2), rs.getDouble(1));
		}
		return map;
	}
	
	public double getIncomeAndExpendSGMouth(Context context,String param) throws SQLException{
		//List<IncomeAndExpendBean> 
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		Map<String, Object> map = new HashMap<String, Object>();
		double a = 0;
		if("goabroadcost".equals(param)){
			// 因公出国
			sql.append(" select sum(zb.debit-zb.credit) as num from GL_VOUCHER t");
			sql.append(" left join md_org md on md.recid = t.unitid");
			sql.append(" left join GL_VOUCHERITEM zb on t.recid = zb.vchrid");
			sql.append(" left join MD_ACCOUNTSUBJECT km on km.recid= zb.subjectid");
			sql.append(" left join GL_ASSISTCOMB fz on fz.recid=zb.asscombid");
			sql.append(" left join md_expendeconclass jjfl on jjfl.recid = fz.expendeconclassid ");
			sql.append(" where t.acctyear='2019' ");
			sql.append("    and md.stdcode='000223' ");
			sql.append("    and (km.stdcode like '72010102%' or km.stdcode like '72010202%'or km.stdcode like'72010302%'or km.stdcode like'79010102%') ");
			sql.append("    and jjfl.stdcode like'30212%' ");
		} else if ("officialreceptcost".equals(param)){
			// 公务接待费
			sql.append(" select sum(zb.debit-zb.credit) as num from GL_VOUCHER t");
			sql.append(" left join md_org md on md.recid = t.unitid");
			sql.append(" left join GL_VOUCHERITEM zb on t.recid = zb.vchrid");
			sql.append(" left join MD_ACCOUNTSUBJECT km on km.recid= zb.subjectid");
			sql.append(" left join GL_ASSISTCOMB fz on fz.recid=zb.asscombid");
			sql.append(" left join md_expendeconclass jjfl on jjfl.recid = fz.expendeconclassid ");
			sql.append(" where t.acctyear='2019' ");
			sql.append("    and md.stdcode='000223' ");
			sql.append("    and (km.stdcode like '72010102%' or km.stdcode like '72010202%'or km.stdcode like'72010302%'or km.stdcode like'79010102%') ");
			sql.append("    and jjfl.stdcode like'30217%' ");
		} else if ("officialcarcost".equals(param)){
			// 公务车购置及运行费
			sql.append(" select sum(zb.debit-zb.credit) as num from GL_VOUCHER t");
			sql.append(" left join md_org md on md.recid = t.unitid");
			sql.append(" left join GL_VOUCHERITEM zb on t.recid = zb.vchrid");
			sql.append(" left join MD_ACCOUNTSUBJECT km on km.recid= zb.subjectid");
			sql.append(" left join GL_ASSISTCOMB fz on fz.recid=zb.asscombid");
			sql.append(" left join md_expendeconclass jjfl on jjfl.recid = fz.expendeconclassid ");
			sql.append(" where t.acctyear='2019' ");
			sql.append("    and md.stdcode='000223' ");
			sql.append("    and (km.stdcode like '72010102%' or km.stdcode like '72010202%'or km.stdcode like'72010302%'or km.stdcode like'79010102%') ");
			sql.append("    and (jjfl.stdcode like '30231%' or jjfl.stdcode like'30913%' or jjfl.stdcode like '31013%')");
		}
		conn = context.get(Connection.class);
		ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
//			DecimalFormat df = new DecimalFormat("0.00");
		while (rs.next()) {
//				map.put(rs.getString(2), df.format(rs.getDouble(1)));
			a = rs.getDouble(1);
		}
		return a;
	}
	
}
