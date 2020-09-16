package com.jiuqi.dna.jnjs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
/**
 * 重算凭证号
 * @author Administrator
 *
 */
public class  RebuildVoucherNumService{
	
	/**
	 * 获取都有那几家单位存在凭证
	 * @param conn
	 * @return
	 */
	
	public static List<String> getUnitList(Context conn) {
		List<String> list = new ArrayList<String>();
		String sql = "define query getUnitList() begin  SELECT a.unitid as unitid FROM gl_voucher as a group by a.unitid  end ";
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sql);
			rs = dbc.executeQuery();
			while (rs.next()) {
				list.add(rs.getFields().get(0).getGUID().toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (dbc != null) {
				dbc.unuse();
			}
		}
		return list;
	}
	
	
	/**
	 * 凭证主表（GL_VOUCHER）中的凭证号字段（VCHRNUM）是存储凭证的顺序号码,要求每年单位范围内此编号需在单期内连续不断号：
	 * 例：A单位在2019年度第5期的凭证共有5800张，其凭证编号根据凭证日期排序分别赋值为1、2、3、4、5、6…5800。
	 * 1、提供数据中已存在断号情况，程序实现自动查找变化数据，并按照凭证号生成规则重新计算并赋值凭证号。
	 * @param conn
	 * @param unitid
	 * @return
	 */

	public static Map<String,Integer> getVoucherListByUnit(Context conn,String unitid,int acctyear ,int acctperiod){
		Map<String,Integer> map = new HashMap<String, Integer>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getUnitList() begin  ");
		sb.append("  SELECT a.recid as recid FROM gl_voucher as a ");
		sb.append("  where a.acctyear = "+acctyear);
		sb.append("  and a.unitid = GUID'"+unitid+"' ");
		sb.append("  and a.acctperiod = "+acctperiod);
		sb.append("   order by a.createdate,a.createtime");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			rs = dbc.executeQuery();
			int num = 0;
			while (rs.next()) {
				num++;
				map.put(rs.getFields().get(0).getGUID().toString(), num);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return map;
	}
	
	/**
	 * 批量更新凭证号
	 * @param conn
	 * @param unitid
	 * @param acctyear
	 * @param acctperiod
	 */
	public static void batchUpdateVoucherNum(Context conn,String unitid,int acctyear ,int acctperiod){
		Map<String,Integer> map = getVoucherListByUnit(conn,unitid,acctyear,acctperiod);
		StringBuffer sb = new StringBuffer();
		sb.append("  define update batchUpdateVoucherNum(@num int,@recid guid) begin ");
		sb.append("  update  gl_voucher as a  set vchrnum=@num ");
		sb.append("  where a.recid =@recid ");
		sb.append("  end ");
		DBCommand db = null;
		db=conn.prepareStatement(sb.toString());
		try{
		 for(Map.Entry<String, Integer> entry : map.entrySet()){
			 db.setArgumentValues(Integer.valueOf(entry.getValue()),GUID.valueOf(entry.getKey()));
			 db.addBatch();
         }
		 db.executeBatch();
		 db.clearBatch();
		 db.unuse();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
