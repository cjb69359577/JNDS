package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;

public class QueryAssetUsageStatisticsService {	
	
	
	public static Map<String,Double> QueryAssetUsageStatistics(Context conn){
	Map<String,Double> map = new HashMap<String, Double>();
	StringBuffer sb = new StringBuffer();
	sb.append("  define query getAssetClassDistributed() begin  ");
	sb.append("  select sum(aa.jiaz),bb.stdname from gams_assetcard_temp as aa "  );
	sb.append(" left join gams_jc_syzk as bb on aa.syzk= bb.recid " );
	sb.append(" where to_char(aa.jizrq, 'yyyy-MM-dd') <= '2019-12-31'     ");//and aa.cardstate in ('00','02')
	sb.append("   and aa.cardstate in ('00','02') ");
	sb.append(" group by  bb.stdname");
	sb.append("  end ");
	RecordSet rs = null;
	DBCommand dbc = null;
	try {
		dbc = conn.prepareStatement(sb.toString());
		rs = dbc.executeQuery();
		while (rs.next()) {
			map.put(rs.getFields().get(1).getString(), rs.getFields().get(0).getDouble());
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("sql±àÒëÊ§°Ü£¬Çë¼ì²ésqlºóÔÙ²Ù×÷¡£");
	}finally{
		if(null != dbc){
			dbc.unuse();
		}
	}
	return map;
}
	
}
