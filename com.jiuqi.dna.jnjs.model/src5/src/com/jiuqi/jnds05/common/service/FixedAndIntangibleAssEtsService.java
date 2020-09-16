package com.jiuqi.jnds05.common.service;
import java.util.List;
import java.util.Map;
import com.jiuqi.dna.core.Context;
import com.jiuqi.jnds05.common.dao.FixedAndIntangibleAssEtsDao;

public class FixedAndIntangibleAssEtsService {

	private static FixedAndIntangibleAssEtsService instance;
	private String [] assTypeS = {"101%,102%,103%","2%","3%","4%","5%","601%,602%,603%,604%","605%"};
	public static FixedAndIntangibleAssEtsService getInstance() {
		if (instance == null) {
			instance = new FixedAndIntangibleAssEtsService();
		}
		return instance;
	}

	public List<Map<String,String>> getFixAndIntangibleAssEtsList(Context ctx, String orgCode) {
		return new FixedAndIntangibleAssEtsDao().getList(ctx,orgCode,assTypeS);
	}
	
	
	/**
	 *add by WangN
	 *For Wangjun
	 *�鿴�ʲ�ʹ�������serverlet
	 */
	public List<Map<String , String>> getFixInstangibleAssShowInfor(Context ctx, String orgCode){
		// �ʲ����� ÿһ�ֵķ����Ӧ��һ������
		return new FixedAndIntangibleAssEtsDao().getShowInfo(ctx,orgCode,assTypeS);
	} 
	
}
