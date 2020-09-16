package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.jnjs.util.MapToJson;

public class QueryAssetClassDistributedService {

	public static Map<String,Double> QueryAssetClassDistributed(Context conn){
		Map<String,Double> map = new HashMap<String, Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getAssetClassDistributed() begin  ");
		sb.append("select sum(aa.jiaz),bb.zicdl from gams_assetcard_temp as aa");
		sb.append(" left join gams_jc_assetclass as bb on aa.zifl= bb.recid" );
		sb.append(" where to_char(aa.jizrq, 'yyyy-MM-dd') <= '2019-12-31' ");
		sb.append("   and aa.cardstate in ('00','02') ");
		sb.append("   and  bb.zicdl is not null group by  bb.zicdl");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			rs = dbc.executeQuery();
			while (rs.next()) {
				map.put(rs.getFields().get(1).getString(), MapToJson.getDoubleFormat(rs.getFields().get(0).getDouble()/10000));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return map;
	}
}
