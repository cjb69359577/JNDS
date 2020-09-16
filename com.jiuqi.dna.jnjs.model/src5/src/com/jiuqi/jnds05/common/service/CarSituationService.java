package com.jiuqi.jnds05.common.service;

import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.jnds05.common.bean.CarSituationIntf;
import com.jiuqi.jnds05.common.dao.CarSituationDao;

public class CarSituationService {
	CarSituationDao cardao=null;
	private static CarSituationService carsituationservice;
	private CarSituationService(){}
	public static synchronized CarSituationService getInstance(){
		if(carsituationservice==null){
			carsituationservice=new CarSituationService(); 
		}
		return carsituationservice;
	}
	public  List<CarSituationIntf> GetAllCarSituations(Context context) {
		cardao=new CarSituationDao();
		 List<CarSituationIntf> resultlist=cardao.GetAllCarSituationResult(context);
		return resultlist;
	}
}
