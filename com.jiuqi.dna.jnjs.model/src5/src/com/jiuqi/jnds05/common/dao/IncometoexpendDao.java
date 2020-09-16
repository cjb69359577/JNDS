package com.jiuqi.jnds05.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jiuqi.dna.bap.basedata.common.util.BaseDataCenter;
import com.jiuqi.dna.bap.basedata.intf.facade.FBaseDataObject;
import com.jiuqi.dna.core.Context;

public class IncometoexpendDao {

	/**
	 * 获取收入、支出决算数据
	 * 
	 * @param context
	 * @return
	 */
	public Map<String, Object> getIncometoexpendData(Context context) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		JSONArray result = new JSONArray();
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			List<FBaseDataObject> objectList = BaseDataCenter.getObjectList(context, "MD_INCOMETOEXPEND");
			String s = "";
			for (int i = 0; i < objectList.size(); i++) {
				if ("收入".equals(objectList.get(i).getFieldValue("FL"))) {
					sql.append(s);
					sql.append(" select ");
					sql.append(" sum(t.dfje) - sum(t.jfje) as je, ");
					sql.append("'" + objectList.get(i).getFieldValue("STDCODE") + "' as stdcode,");
					sql.append("'" + objectList.get(i).getFieldValue("STDNAME") + "' as stdname,");
					sql.append("'" + objectList.get(i).getFieldValue("FL") + "' as fl");
					sql.append(" from data_extract t ");
					sql.append("  where t.acctyear = 2019 ");
					sql.append("    and t.unitcode = '000223' ");
					sql.append("    and t.acctperiod between 1 and 12 ");
					String[] split = String.valueOf(objectList.get(i).getFieldValue("TJ")).split(",");
					String a = "";
					if (split.length > 0) {
						sql.append("and (");
					}
					for (int j = 0; j < split.length; j++) {
						sql.append(a);
						sql.append(" t.kmcode like '" + split[j] + "%'");
						a = "or";
					}
					if (split.length > 0) {
						sql.append(")");
					}
					s = " union all ";
				} else if ("支出".equals(objectList.get(i).getFieldValue("FL"))) {
					sql.append(s);
					sql.append(" select ");
					sql.append(" sum(t.jfje) - sum(t.dfje) as je, ");
					sql.append("'" + objectList.get(i).getFieldValue("STDCODE") + "' as stdcode,");
					sql.append("'" + objectList.get(i).getFieldValue("STDNAME") + "' as stdname,");
					sql.append("'" + objectList.get(i).getFieldValue("FL") + "' as fl");
					sql.append("  from data_extract t ");
					sql.append("  where t.acctyear = 2019 ");
					sql.append("    and t.unitcode = '000223' ");
					sql.append("    and t.acctperiod between 1 and 12 ");
					sql.append("    and REGEXP_LIKE(t.kmcode, '(7201|7301|7401|7501)[[:alnum:]]') ");
					String[] split = String.valueOf(objectList.get(i).getFieldValue("TJ")).split(",");
					String a = "";
					if (split.length > 0) {
						sql.append("and (");
					}
					for (int j = 0; j < split.length; j++) {
						sql.append(a);
						sql.append("  t.gnflcode like '" + split[j] + "%'");
						a = "or";
					}
					if (split.length > 0) {
						sql.append(")");
					}
					s = " union all ";
				}
			}
			conn = context.get(Connection.class);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			double sr = 0.00;
			double zc = 0.00;
			while (rs.next()) {
				JSONObject returnJson = new JSONObject();
				returnJson.put("je", df.format(rs.getDouble(1)));
				returnJson.put("code", rs.getString(2));
				returnJson.put("name", rs.getString(3));
				returnJson.put("fl", rs.getString(4));
				if ("收入".equals(rs.getString(4))) {
					sr = sr + rs.getDouble(1);
				} else if ("支出".equals(rs.getString(4))) {
					zc = zc + rs.getDouble(1);
				}
				result.put(returnJson);
			}
			map.put("result", result);
			map.put("srhj", sr);
			map.put("zchj", zc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return map;
	}

}
