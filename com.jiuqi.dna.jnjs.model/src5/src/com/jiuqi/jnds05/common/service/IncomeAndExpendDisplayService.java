package com.jiuqi.jnds05.common.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.jnds05.common.bean.IncomeAndExpendBean;
import com.jiuqi.jnds05.common.dao.IncomeAndExpendDisplayDao;

public class IncomeAndExpendDisplayService {
	
	IncomeAndExpendDisplayDao dao = new IncomeAndExpendDisplayDao();
	public List<IncomeAndExpendBean> getIncomeAndExpendMouth(Context context) throws SQLException{
		Map<String, Object> srmap  = dao.getIncomeAndExpendMouth(context, "sr");
		Map<String, Object> zcmap  = dao.getIncomeAndExpendMouth(context, "zc");
		List<IncomeAndExpendBean> iaxbeanList = new ArrayList<IncomeAndExpendBean>();
	    String [] arrs={"1","2","3","4","5","6","7","8","9","10","11","12"};
		for (int i = 0; i < arrs.length; i++) {
			IncomeAndExpendBean iaxbean = new IncomeAndExpendBean();
			String a = arrs[i];
			iaxbean.setMounth(a);
			Double sr = (Double)srmap.get(a);
			Double zc = (Double)zcmap.get(a);
			iaxbean.setIncome(sr==null?0.00:sr);
			iaxbean.setExpend(zc==null?0.00:zc);
			iaxbeanList.add(iaxbean);
		}
		return iaxbeanList;
	}
	
	public IncomeAndExpendBean getIncomeAndExpendSG(Context context) throws SQLException{
		Double goabroadcost  = dao.getIncomeAndExpendSGMouth(context, "goabroadcost");
		Double officialreceptcost  = dao.getIncomeAndExpendSGMouth(context, "officialreceptcost");
		Double officialcarcost  = dao.getIncomeAndExpendSGMouth(context, "officialcarcost");
		IncomeAndExpendBean iaxbean = new IncomeAndExpendBean(); 
		iaxbean.setGoabroadcost(goabroadcost==null?0.00:goabroadcost);
		iaxbean.setOfficialreceptcost(officialreceptcost==null?0.00:officialreceptcost);
		iaxbean.setOfficialcarcost(officialcarcost==null?0.00:officialcarcost);
		return iaxbean;
	}
}
