package com.jiuqi.jnds.cfpl6.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.jiuqi.jnds.cfpl6.cache.QuerySqlProvider;
import com.jiuqi.jnds.cfpl6.service.CalculationQueryService;

/**
 * 
 * @author LUOXINLIANG 
 *
 */
@SuppressWarnings({ "deprecation", "unchecked" })
@Service
public class CalculationQueryServiceImpl implements CalculationQueryService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Map<String, Object> queryIncomeAndExpendDetail() {
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(QuerySqlProvider.getIncomeAndExpendSql()).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		return resultList.get(0);
	}

	@Override
	public List<Map<String, Object>> queryFinalAccountOfExpendDetail(int start, int count) {
		// 项目支出决算明细 每页默认展示5000条
		start = start <= 0 ? 1 : start;
		int maxResult = 5000;
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(QuerySqlProvider.getFinalAccountOfExpendSql()).unwrap(SQLQuery.class)
		.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult((start - 1) * maxResult).setMaxResults(maxResult).getResultList();
		return resultList;
	}

	@Override
	public int countFinalAccountOfExpendDetail() {
		String countSql = "select count(1) from (" + QuerySqlProvider.getFinalAccountOfExpendSql() + ")";
		return Long.valueOf(entityManager.createNativeQuery(countSql).getSingleResult().toString()).intValue();
	}

	@Override
	public List<Map<String, Object>> queryVehicleInformationDetail(int start, int count) {
		// 车辆情况表 单页数据量20条
		start = start <= 0 ? 1 : start;
		int maxResult = 20;
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(QuerySqlProvider.getVehicleInformationDetailSql()).unwrap(SQLQuery.class)
		.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setFirstResult((start - 1) * maxResult).setMaxResults(maxResult).getResultList();
		return resultList;		
	}

	@Override
	public int countFinalAccountOfVehicleInformationDetail() {
		// 车辆情况表总数
		String countSql = "select count(1) from (" + QuerySqlProvider.getVehicleInformationDetailSql() + ")";
		return Long.valueOf(entityManager.createNativeQuery(countSql).getSingleResult().toString()).intValue();
	}

	@Override
	public Map<String, Object> queryAssetsDetail() {
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(QuerySqlProvider.getAssetsDetailSql()).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		return resultList.get(0);
	}
	
}
