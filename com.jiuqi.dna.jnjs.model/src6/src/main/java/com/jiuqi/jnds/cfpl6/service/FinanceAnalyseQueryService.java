package com.jiuqi.jnds.cfpl6.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author LUOXINLIANG 
 *
 */
public interface FinanceAnalyseQueryService {

	List<Map<String, Object>> queryMonthlyData();
	
	Map<String, Object> queryOutLayData();
	
	List<Map<String, Object>> queryTop10Data();
}
