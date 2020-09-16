package com.jiuqi.jnds.cfpl6.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.jiuqi.jnds.cfpl6.service.ComplexQueryService;

@Service
@SuppressWarnings({"unchecked", "deprecation"})
public class ComplexQueryServiceImpl implements ComplexQueryService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Map<String, Object> queryByClass() {
		List<?> resultList = entityManager.createNativeQuery(sqlByClass()).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> queryByUsedStatus() {
		List<?> resultList = entityManager.createNativeQuery(sqlByState()).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		return (List<Map<String, Object>>) resultList;
	}

	private String sqlByClass() {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(case when zicfl.stdcode like '101%' or zicfl.stdcode like '102%' or ");
		sql.append("zicfl.stdcode like '103%' then coalesce(c.jiaz, 0) else 0 end) as tudfwgzw,");
		sql.append("sum(case when zicfl.stdcode like '2%' then coalesce(c.jiaz, 0) else 0 end) as tongysb,");
		sql.append("sum(case when zicfl.stdcode like '3%' then coalesce(c.jiaz, 0) else 0 end) as zhuanysb,");
		sql.append("sum(case when zicfl.stdcode like '4%' then coalesce(c.jiaz, 0) else 0 end) as wenwhclp,");
		sql.append("sum(case when zicfl.stdcode like '5%' then coalesce(c.jiaz, 0) else 0 end) as tusda,");
		sql.append("sum(case when zicfl.stdcode like '601%' or zicfl.stdcode like '602%' or zicfl.stdcode like '603%'");
		sql.append(" or zicfl.stdcode like '604%' then coalesce(c.jiaz, 0) else 0 end) as jiajyjzjdzw,");
		sql.append("sum(case when zicfl.stdcode like '605%' then coalesce(c.jiaz, 0) else 0 end) as wuxzc ");
		sql.append("from gams_assetcard c left join gams_jc_assetclass zicfl on c.zifl = zicfl.recid,");
		sql.append("(select max(card.jizrq) as maxjizrq, card.objectid as objectid from gams_assetcard card ");
		sql.append("where card.cardstate like '0%' group by card.objectid) t ");
		sql.append("where c.jizrq = t.maxjizrq and c.objectid = t.objectid ");
		return sql.toString();
	}

	private String sqlByState() {
		StringBuilder sql = new StringBuilder();
		sql.append("select syzk.stdcode as code, sum(c.jiaz) as zongjz from gams_jc_syzk syzk ");
		sql.append("left join gams_assetcard c on c.syzk = syzk.recid,");
		sql.append("(select max(card.jizrq) as maxjizrq, card.objectid as objectid ");
		sql.append("from gams_assetcard card where card.cardstate like '0%' group by card.objectid) t ");
		sql.append("where c.objectid = t.objectid and c.jizrq = t.maxjizrq group by syzk.stdcode ");
		return sql.toString();
	}

}
