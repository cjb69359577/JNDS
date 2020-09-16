package com.jiuqi.jnds.cfpl6.service;

import java.util.List;
import java.util.Map;

/**
 * 报表数据计算提取服务
 * @author LUOXINLIANG 
 *
 */
public interface CalculationQueryService {

	/**
	 * 查询收入支出固定表
	 * @return
	 */
	Map<String, Object> queryIncomeAndExpendDetail();
	
	/**
	 * 查询项目支出决算明细
	 * @param start	起始行
	 * @param count	总条数
	 * @return
	 */
	List<Map<String, Object>> queryFinalAccountOfExpendDetail(int start, int count);
	
	/**
	 * 查询项目支出决算明细总条目
	 * @return
	 */
	int countFinalAccountOfExpendDetail();
	
	/**
	 * 查询项目支出决算明细
	 * @param start	起始行
	 * @param count	总条数
	 * @return
	 */
	/**
	 * 查询车辆信息表明细
	 * @param start	起始行
	 * @param count	总条数
	 * @return
	 */
	List<Map<String, Object>> queryVehicleInformationDetail(int start, int count);
	
	/**
	 * 查询车辆信息表明细总条目
	 * @return
	 */
	int countFinalAccountOfVehicleInformationDetail();
	
	/**
	 * 查询固定和无形资产存量情况表
	 * @param start	起始行
	 * @param count	总条数
	 * @return
	 */
	Map<String, Object> queryAssetsDetail();
	
}
