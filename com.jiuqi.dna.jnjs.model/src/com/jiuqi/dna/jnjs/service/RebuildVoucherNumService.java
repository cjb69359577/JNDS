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
 * ����ƾ֤��
 * @author Administrator
 *
 */
public class  RebuildVoucherNumService{
	
	/**
	 * ��ȡ�����Ǽ��ҵ�λ����ƾ֤
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
	 * ƾ֤����GL_VOUCHER���е�ƾ֤���ֶΣ�VCHRNUM���Ǵ洢ƾ֤��˳�����,Ҫ��ÿ�굥λ��Χ�ڴ˱�����ڵ������������Ϻţ�
	 * ����A��λ��2019��ȵ�5�ڵ�ƾ֤����5800�ţ���ƾ֤��Ÿ���ƾ֤��������ֱ�ֵΪ1��2��3��4��5��6��5800��
	 * 1���ṩ�������Ѵ��ڶϺ����������ʵ���Զ����ұ仯���ݣ�������ƾ֤�����ɹ������¼��㲢��ֵƾ֤�š�
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
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return map;
	}
	
	/**
	 * ��������ƾ֤��
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
