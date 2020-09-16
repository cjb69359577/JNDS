package com.jiuqi.jnds05.common.service;

import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.jnds05.common.dao.IncometoexpendDao;


public class IncometoexpendService {
	IncometoexpendDao dao = new IncometoexpendDao();
	public Map<String, Object> getIncometoexpendData(Context context) {
		return dao.getIncometoexpendData(context);
	}
	
}
