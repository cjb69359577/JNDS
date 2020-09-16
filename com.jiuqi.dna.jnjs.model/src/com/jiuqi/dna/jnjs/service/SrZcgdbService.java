package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;

/**
 * �ñ�Ϊ����֧����
 * A-C����Ҫ����ȡ2019��ȡ�000223��λ����
 * ��λ���Ϊ��000223��
 * 1��12��ָ����Ŀ��ص�ƾ֤���ݵĽ����Ϣ��
 * �Ӷ����ֳ�����λ����ȵ��������
 * @author zhangwei
 *
 */
public class  SrZcgdbService{
	
	
	/**
	 * ����  sql��д
	 * ȡ������
	 * ��C4��=ƾ֤����GL_VOUCHER�������Ϊ2019�꣬��000223��λ������λ���Ϊ��000223��,
	 * �ڼ�Ϊ1��12��ƾ֤������ƾ֤�ӱ�GL_VOUCHERITEM���п�Ŀ���루��Ŀ���STDCODE�ֶΣ�
	 * �ԡ�600101����600102����ͷ�����ݣ��䡰�������ֶκϼ�ֵ��ȥ���跽���ֶκϼ�ֵ��
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getSrService(Context conn,int acctyear){
		Map<String,Double> mapSR = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getSrzcgdService(@acctyear int) begin  ");
		sb.append("  select SUM( case when (a.stdcode like '600101%' or a.stdcode like '600102%')  then (s.credit - s.debit) else 0 end) as ybsr, ");
		sb.append("         SUM( case when (a.stdcode like '6201%')  then (s.credit - s.debit) else 0 end) as sjsr,");
		sb.append("         SUM( case when (a.stdcode like '6101%')  then (s.credit - s.debit) else 0 end) as sysr,");
		sb.append("         SUM( case when (a.stdcode like '6401%')  then (s.credit - s.debit) else 0 end) as jysr,");
		sb.append("         SUM( case when (a.stdcode like '6301%'  )  then (s.credit - s.debit) else 0 end) as fssr,");
		sb.append("         SUM( case when (a.stdcode like '6501%' or a.stdcode like '6601%' or a.stdcode like '6602%' or a.stdcode like '6609%' )  then (s.credit - s.debit) else 0 end) as qtsr ");
		sb.append("   from gl_voucheritem as s ");
		sb.append("   join gl_voucher as t ");
		sb.append("     on s.vchrid = t.recid ");
		sb.append("   join md_accountsubject as a ");
		sb.append("     on s.subjectid = a.recid ");
		sb.append("   join md_org as o ");
		sb.append("     on o.recid = t.unitid ");
		sb.append("   where t.acctyear = 2019 ");
		sb.append("    and o.stdcode = '000223' ");
		sb.append("   end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			while (rs.next()) {
				mapSR.put("ybsr", rs.getFields().get(0).getDouble());
				mapSR.put("sjsr", rs.getFields().get(1).getDouble());
				mapSR.put("sysr", rs.getFields().get(2).getDouble());
				mapSR.put("jysr", rs.getFields().get(3).getDouble());
				mapSR.put("fssr", rs.getFields().get(4).getDouble());
				mapSR.put("qtsr", rs.getFields().get(5).getDouble());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapSR;
	}
	
	

	/**
	 * ֧��sql
	 * ȡ������
	 * ������F4��=ƾ֤����GL_VOUCHER�������Ϊ2019�꣬��000223��λ������λ���Ϊ��000223��,
	 * �ڼ�Ϊ1��12��ƾ֤������ƾ֤�ӱ�GL_VOUCHERITEM���п�Ŀ�����ԣ���Ŀ�������ݵ�STDCODE�ֶΣ�
	 * ��7201����7301����7401����7501����ͷ���ҹ����ĸ�����ϱ�GL_ASSISTCOMB���й��ܷ�����201��ͷ�����ݣ�
	 * �䡰�跽���ֶκϼ�ֵ��ȥ���������ֶκϼ�ֵ��
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getZcService(Context conn,int acctyear){
		Map<String,Double> mapZC = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getSrzcgdService(@acctyear int) begin  ");
		sb.append("  select substr(f.stdcode, 1, 3) as stdcode, sum(s.debit - s.credit) as hj ");
		sb.append("  from gl_voucheritem as s ");
		sb.append("  join gl_voucher as t ");
		sb.append("    on s.vchrid = t.recid ");
		sb.append("   join md_accountsubject as a "); 
		sb.append("     on s.subjectid = a.recid ");
		sb.append("  join gl_assistcomb as g ");
		sb.append("    on g.recid = s.asscombid ");
		sb.append("  join md_expendfuncclass as f ");
		sb.append("    on f.recid = g.expendfuncclassid ");
		sb.append("  join md_org as o ");
		sb.append("    on o.recid = t.unitid ");
		sb.append(" where t.acctyear = 2019 "); 
		sb.append("   and o.stdcode = '000223' ");
		sb.append("   and (a.stdcode like '7201%' or a.stdcode like '7301%' or ");
		sb.append("       a.stdcode like '7401%' or a.stdcode like '7501%') ");
		sb.append("  group by substr(f.stdcode, 1, 3) ");
		sb.append("  order by substr(f.stdcode, 1, 3); ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getFields().get(0).getString() +"---------"+rs.getFields().get(1).getDouble());
				mapZC.put(rs.getFields().get(0).getString(),rs.getFields().get(1).getDouble());
			}
		} catch (Exception e) {
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapZC;
	}
	

}
