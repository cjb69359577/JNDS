package com.jiuqi.dna.jnjs.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.jnjs.entity.ClqkEntity;
/**
 * 
 * @author Administrator
 *
 */
public class  ClqkService{
	
	
	/**
	 * 1、通过获取卡片表中“000223单位”（单位编号为：000223）
	 * 2019年  12月31日前车辆卡片(卡片最新版本)对应的资产编号个数决定该表行数。
	 * 反映到此表中D列确定行数。
	 * C列显示该行卡片信息对应的“资产分类”基础数据的stdname列。
	 * 3、D到H列分别对应该行卡片信息的资产相关信息。
	 * 4、I列和J列分别显示该行卡片信息对应的“使用状况”和“车辆用途”
	 * 基础数据的stdname列。
	 * 取数规则
	 * 【C6】资产分类 = “卡片表单位为“000223单位”（单位编号为：000223）
	 * 的数据中对应资产分类在
	 * “（STDCODE > 2030000 AND STDCODE < 2030800）OR STDCODE = 2039900”，卡片状态是（00,02）
	 * 且记账日期在2019年12月31日（包含31日）前卡片最新版本的数据对应卡片信息的资产分类名称。
	 * @param conn
	 * @return
	 */
	public static List<ClqkEntity> getClqk(Context conn,int acctyear){
		List<ClqkEntity> list = new ArrayList<ClqkEntity>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getClqk(@acctyear int) begin  ");
		sb.append(" SELECT ac.stdname as zcfl,tmp.billcode as zcbh,tmp.cheph as cph,tmp.jiaz as jz,tmp.leijzj as ljzj ,");
		sb.append("	       tmp.jingz as jingz,syzk.stdname as syzk,clyt.stdname as clyt  ");
		sb.append("   FROM gams_assetcard_temp as tmp  ");
		sb.append("   join md_org as org  ");
		sb.append("   on org.recid = tmp.orgunit  ");
		sb.append("   and org.stdcode = '000223'  ");
		sb.append(" LEFT join gams_jc_syzk as syzk  ");
		sb.append("   on syzk.recid=tmp.syzk  ");
		sb.append(" LEFT join gams_jc_clytfl as clyt  ");
		sb.append("   on clyt.recid=tmp.clyt  ");
		sb.append(" LEFT join gams_jc_assetclass as ac  ");
		sb.append("   on ac.recid=tmp.zifl ");
		sb.append(" where to_char(tmp.jizrq,'yyyy')<=@acctyear  ");
		sb.append("     and tmp.cardstate in ('00','02')  ");
		sb.append("    and ((ac.stdcode >'2030000' and ac.stdcode < '2030800') or ac.STDCODE = 2039900)  ");
		sb.append(" end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			while (rs.next()) {
				ClqkEntity entity = new ClqkEntity();
				entity.setZcfl(rs.getFields().get(0).getString());
				entity.setZcbh(rs.getFields().get(1).getString());
				entity.setCph(rs.getFields().get(2).getString());
				entity.setJz(rs.getFields().get(3).getDouble());
				entity.setLjzj(rs.getFields().get(4).getDouble());
				entity.setJingz(rs.getFields().get(5).getDouble());
				entity.setSyzk(rs.getFields().get(6).getString());
				entity.setClyt(rs.getFields().get(7).getString());
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return list;
	}

}
