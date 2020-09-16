package com.jiuqi.jnds05.common.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.bap.basedata.common.util.BaseDataCenter;
import com.jiuqi.dna.bap.basedata.intf.facade.FBaseDataObject;
import com.jiuqi.dna.core.Context;
import com.jiuqi.jnds05.common.bean.ProjectExpenditureTop10Entity;
import com.jiuqi.jnds05.common.dao.ProjectExpenditureDetailsDao;

public class ProjectExpenditureDetailsService {

	public List<ProjectExpenditureTop10Entity> queryProjectExpenditureTop10(Context context) {
		ProjectExpenditureDetailsDao dao = new ProjectExpenditureDetailsDao();
		String sql = dao.ProjectExpenditureTop10();
		List<ProjectExpenditureTop10Entity> result = new ArrayList<ProjectExpenditureTop10Entity>();
		Connection conn = null;
		try {
			conn = context.get(Connection.class);
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			int index = 1;
			while (rs.next()) {
				if (index > 10)
					break;
				ProjectExpenditureTop10Entity entity = new ProjectExpenditureTop10Entity();
				entity.setProject(rs.getString("STDNAME"));
				entity.setZcje(rs.getDouble("ZCJE"));
				result.add(entity);
				index++;
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, List<Map<String, String>>> queryProjectExpenditureDetails(Context context) {
		List<Map<String, String>> baseResult = new ArrayList<Map<String, String>>();
		DecimalFormat dobleDf=new DecimalFormat("##0.00");
		String detailCode = "";
		String detailColumns = "";
		List<FBaseDataObject> baseList = BaseDataCenter.getObjectList(context, "MD_XMZCJSMXB");
		for (FBaseDataObject objs : baseList) {
			Map<String, String> baseMap = new HashMap<String, String>();
			detailCode += objs.getValueAsString("MXDM") + ":";
			detailColumns += objs.getValueAsString("MXBS") + ":";
			baseMap.put("jjfldl", objs.getStdCode());
			baseMap.put("jjflxjbs", objs.getStdName());
			baseMap.put("jjflzdbs", objs.getValueAsString("MXBS"));
			baseMap.put("jjfldm", objs.getValueAsString("MXDM"));
			baseMap.put("jjflzdmc", objs.getValueAsString("MXMC"));
			baseMap.put("hasChild",
					(null == objs.getStdName() || "".equals(objs.getStdName()) || null == objs.getValueAsString("MXMC")
							|| "".equals(objs.getValueAsString("MXMC"))) ? "false" : "true");
			baseResult.add(baseMap);
		}
		String mainSql = "SELECT AaA.gnflcode,AaA.gnflname,AaA.xmcode,AaA.xmname, \n";
		String sql = " FROM (SELECT AA.gnflcode,AA.gnflname,AA.xmcode,AA.xmname, \n";
		int index = 0;
		String[] detailColumn = detailColumns.substring(0, detailColumns.length() - 1).split(":");
		for (String str : detailCode.substring(0, detailCode.length() - 1).split(":")) {
			mainSql = mainSql + "SUM(AAA." + detailColumn[index] + ") AS " + detailColumn[index] + ", \n";
			sql = sql + "CASE WHEN AA.JJFL = '" + str + "' THEN AA.ssss ELSE 0.00 END AS " + detailColumn[index]
					+ ", \n";
			index++;
		}
		sql = sql.substring(0, sql.length() - 3) + " FROM \n" + "( \n"
				+ "select a.gnflcode,a.gnflname,a.xmcode,a.xmname,substr(a.jjflcode,1,5) AS JJFL,sum(a.jfje-a.dfje) as ssss from data_extract a \n"
				+ "where a.unitcode = '000223' \n" + "and a.acctyear = 2019 \n"
				+ "and (a.kmcode like '72010102%' or a.kmcode like '72010202%'or a.kmcode like '72010302%'or a.kmcode like '79010102%') \n"
				+ "and a.acctperiod <> 0 \n"
				+ "group by a.gnflcode,a.gnflname,a.xmcode,a.xmname,substr(a.jjflcode,1,5) \n" + ") AA \n"
				+ ") AAA GROUP BY AAA.gnflcode,AAA.gnflname,AAA.xmcode,AAA.xmname";
		mainSql = mainSql.substring(0, mainSql.length() - 3) + sql;
		// 项目支出决算明细
//		ResultMapperEntity result = new ResultMapperEntity();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		try {
			conn = context.get(Connection.class);
			PreparedStatement pst = conn.prepareStatement(mainSql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, String> data = new HashMap<String, String>();
				BigDecimal hj = new BigDecimal("0.00");
				data.put("Z05_2EJMC", rs.getString("gnflcode"));
			    data.put("Z05_2EJDM", rs.getString("gnflname"));
			    data.put("Z05_2YJMC", rs.getString("xmcode"));
			    data.put("Z05_2YJDM", rs.getString("xmname"));
				for (FBaseDataObject obj : baseList) {
					BigDecimal xj = new BigDecimal("0.00");
					String[] columns = obj.getValueAsString("MXBS").split(":");
					for (String str : columns) {
						data.put(str, dobleDf.format(Double.valueOf(rs.getString(str))));
						xj = xj.add(new BigDecimal(rs.getString(str)));
						hj = hj.add(new BigDecimal(rs.getString(str)));
					}
					data.put(obj.getStdName(), dobleDf.format(xj.doubleValue()) + "");
				}
				if (hj.compareTo(new BigDecimal("0.00")) != 0) {
					data.put("Z05_2F1_1", dobleDf.format(hj.doubleValue()) + "");
					list.add(data);
				}
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
		map.put("resultList", list);
		map.put("baseResult", baseResult);
		return map;
	}

}
