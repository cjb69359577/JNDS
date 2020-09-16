package com.jiuqi.jnds.cfpl6.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.jiuqi.jnds.cfpl6.service.FinanceAnalyseQueryService;

/**
 * 
 * @author LUOXINLIANG 
 *
 */
@Service
@SuppressWarnings({"unchecked", "deprecation"})
public class FinanceAnalyseQueryServiceImpl implements FinanceAnalyseQueryService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Map<String, Object>> queryMonthlyData() {
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(monthlyDataSql()).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		return resultList;
	}

	@Override
	public Map<String, Object> queryOutLayData() {
		List<Map<String, Object>> resultList = entityManager.createNativeQuery(outLayDataSql()).unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
		return resultList.get(0);
	}

	@Override
	public List<Map<String, Object>> queryTop10Data() {
		Query<?> query = entityManager.createNativeQuery(top10DataSql()).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) query.getResultList();
		return resultList;
	}
	
	private String monthlyDataSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.acctperiod, ");
		sql.append("       sum(case when acc.stdcode like '6%' then t.credit - t.debit else 0 end) shour, ");
		sql.append("       sum(case when acc.stdcode like '7%' then t.debit - t.credit else 0 end) zhic ");
		sql.append("from ( ");
		sql.append("  select i.subjectid,h.acctperiod,sum(i.debit) as debit,sum(i.credit) as credit ");
		sql.append("  from GL_VOUCHERITEM i  ");
		sql.append("  left join  GL_VOUCHER h on i.vchrid = h.recid ");
		sql.append("  where i.subjectid in (select acc.recid from MD_ACCOUNTSUBJECT acc where acc.stdcode like '6%' or acc.stdcode like '7%') ");
		sql.append("    and h.acctyear = '2019' and h.acctperiod <> 0 and h.unitid = (select m.recid from MD_ORG m where m.stdcode = '000223') ");
		sql.append("  group by i.subjectid,h.acctperiod) t  ");
		sql.append("left join MD_ACCOUNTSUBJECT acc on t.subjectid = acc.recid ");
		sql.append("group by t.acctperiod ");
		sql.append("order by t.acctperiod ");
		return sql.toString();
	}
	
	private String outLayDataSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(case when cl.stdcode like '30212%' then t.debit - t.credit else 0 end) as yingcgfy, ");
		sql.append("       sum(case when cl.stdcode like '30217%' then t.debit - t.credit else 0 end) as gongwjdfy, ");
		sql.append("       sum(case when stdcode like '30231%' or stdcode like '30913%' or stdcode like '31013%' then t.debit - t.credit else 0 end) as gongwcgzjyxfy ");
		sql.append("from ( ");
		sql.append("  select i.subjectid,i.asscombid,sum(i.debit) as debit,sum(i.credit) as credit ");
		sql.append("  from GL_VOUCHERITEM i  ");
		sql.append("  where i.asscombid in (select comb.recid from GL_ASSISTCOMB comb where comb.expendeconclassid in (select cl.recid from MD_EXPENDECONCLASS cl where cl.stdcode like '30212%' or cl.stdcode like '30217%' or cl.stdcode like '30231%' or cl.stdcode like '30913%' or cl.stdcode like '31013%')) ");
		sql.append("    and i.subjectid in (select acc.recid from MD_ACCOUNTSUBJECT acc where acc.stdcode like '72010102%' or acc.stdcode like '72010202%' or acc.stdcode like '72010302%' or acc.stdcode like '79010102%') ");
		sql.append("    and i.vchrid in (select h.recid from GL_VOUCHER h where h.acctyear = '2019' and h.acctperiod <> 0 and h.unitid = (select m.recid from MD_ORG m where m.stdcode = '000223')) ");
		sql.append("  group by i.subjectid,i.asscombid) t ");
		sql.append("left join GL_ASSISTCOMB comb on t.asscombid = comb.recid ");
		sql.append("left join MD_EXPENDECONCLASS cl on comb.expendeconclassid = cl.recid ");
		return sql.toString();
	}
	
	private String top10DataSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("select pr.stdname,t2.jine ");
		sql.append("from ( ");
		sql.append("  select comb.projectid,sum(t.debit - t.credit) as jine ");
		sql.append("  from ( ");
		sql.append("    select i.asscombid,sum(i.debit) as debit,sum(i.credit) as credit ");
		sql.append("    from GL_VOUCHERITEM i  ");
		sql.append("    where i.subjectid in (select acc.recid from MD_ACCOUNTSUBJECT acc where acc.stdcode like '720102%' or acc.stdcode like '730102%' or acc.stdcode like '740102%' or acc.stdcode like '750102%' or acc.stdcode like '790102%') ");
		sql.append("      and i.vchrid in (select h.recid from GL_VOUCHER h where h.acctyear = '2019' and h.acctperiod <> 0 and h.unitid = (select m.recid from MD_ORG m where m.stdcode = '000223')) ");
		sql.append("    group by i.asscombid) t ");
		sql.append("  left join GL_ASSISTCOMB comb on t.asscombid = comb.recid  ");
		sql.append("group by comb.projectid) t2  ");
		sql.append("left join MD_PROJECT pr on t2.projectid = pr.recid ");
		sql.append("order by t2.jine desc ");
		return sql.toString();
	}
}
