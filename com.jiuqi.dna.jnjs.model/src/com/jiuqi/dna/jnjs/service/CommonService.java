package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
/**
 * 资产类别分布情况
 * 按照固定资产六大类：
 * “土地、房屋及构筑物”、“通用设备”、
 * “专用设备”、
 * “文物和陈列品”、
 * “图书档案”、
 * “家具、用具、装具及动植物”展示
 * @author Administrator
 *
 */
public class  CommonService{
	
	/**
	 * 初始化数据
	 */
	public static void initService(Context context){
		//获取组织基础数据
		MdServices.getMd_org(context);
		//获取项目基础数据
		MdServices.getMd_project(context);
		//获取支出经济基础数据
		MdServices.getMd_Expendeconclass(context);
		//获取功能基础数据
		MdServices.getMd_Expendfuncclasst(context);
		//获取科目基础数据
		MdServices.getMd_Accountsubject(context);
		//删除临时表数据
		MdServices.deleteGAMS_ASSETCARD_TEMP(context);
		//重新插入临时表数据
		MdServices.insertGAMS_ASSETCARD_TEMP(context);
		//更新维护资产大类的值
		MdServices.updateGams_jc_assetclass(context);
	}
	
	
	
	 /**
	 * 资产类别分布情况
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getZcfbqk(Context conn){
		Map<String,Double> mapSR = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getZcfbqk() begin  ");
		sb.append("  select  SUM( case when (asset.stdcode like '101%') or (asset.stdcode like '102%') or (asset.stdcode like '103%') then (card.jiaz) else 0 end) as tudi,");
		sb.append("          SUM( case when (asset.stdcode like '2%' ) then (card.jiaz) else 0 end) as tongyong,");
		sb.append("          SUM( case when (asset.stdcode like '3%' ) then (card.jiaz) else 0 end) as zhuanyong,");
		sb.append("          SUM( case when (asset.stdcode like '4%' ) then (card.jiaz) else 0 end) as wenwu,");
		sb.append("          SUM( case when (asset.stdcode like '5%' ) then (card.jiaz) else 0 end) as tushu,");
		sb.append("         SUM( case when (asset.stdcode like '601%') or (asset.stdcode like '602%') or (asset.stdcode like '603%') or (asset.stdcode like '604%') then (card.jiaz) else 0 end) as jiaju ");
		sb.append("  FROM gams_assetcard as card");
		sb.append("  left join gams_jc_assetclass  as asset");
		sb.append("      on card.zifl = asset.recid");
		sb.append("  left join gams_jc_syzk as syzk");
		sb.append("      on syzk.recid=card.syzk");
		sb.append("      and syzk.stdcode='01'");
	    sb.append(" where to_char(card.jizrq, 'yyyy') <= 2019 ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			rs = dbc.executeQuery();
			while (rs.next()) {
				mapSR.put("tudi",rs.getFields().get(0).getDouble());
				mapSR.put("tongyong",rs.getFields().get(0).getDouble());
				mapSR.put("zhuanyong",rs.getFields().get(0).getDouble());
				mapSR.put("wenwu",rs.getFields().get(0).getDouble());
				mapSR.put("tushu",rs.getFields().get(0).getDouble());
				mapSR.put("jiaju",rs.getFields().get(0).getDouble());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapSR;
	}
	
}
