package com.jiuqi.dna.jnjs.service;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
/**
 * ��Ŀ֧������
 * ����ȡ��ĿA֧�����߼�Ϊ��
 * ƾ֤����GL_VOUCHER�������Ϊ2019�꣬��λΪ��000223��λ������λ���Ϊ��000223��,
 * �ڼ�Ϊ1��12��ƾ֤������ƾ֤�ӱ�GL_VOUCHERITEM���п�Ŀ�����ԣ���Ŀ�������ݵ�STDCODE�ֶΣ�
 * ��720102����730102����740102�� ��750102�� ��790102����ͷ���ҹ����ĸ�����ϱ�GL_ASSISTCOMB������ĿΪA�����ݣ��䡰�跽���ֶκϼ�ֵ��ȥ���������ֶκϼ�ֵ��
 * @author Administrator
 *
 */
public class  XmzcJdService{
	
	
	/**
	 * ��ȡ��λΪ000223����ȡ��Ŀ֧���������
	 * @param conn
	 * @return
	 */
	public static Map<String,Double> getXmzc(Context conn,int acctyear){
		Map<String,Double> mapSR = new HashMap<String,Double>();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getXmzc(@acctyear int) begin  ");
		sb.append(" SELECT result.stdname as stdname,result.hj as hj ");
		sb.append("  FROM (SELECT  pro.stdname as stdname, sum(item.debit) - sum(item.credit) as hj ");
		sb.append("          FROM gl_voucher as a ");
		sb.append("          join md_org  as org ");
		sb.append("            on org.recid = a.unitid ");
		sb.append("          join gl_voucheritem as item ");
		sb.append("            on item.vchrid = a.recid ");
		sb.append("          join md_accountsubject as subject ");
		sb.append("            on subject.recid = item.subjectid ");
		sb.append("          join gl_assistcomb as comb ");
		sb.append("            on item.asscombid = comb.recid ");
		sb.append("          join md_project as pro ");
		sb.append("            on pro.recid = comb.projectid ");
		sb.append("         where a.acctyear = 2019 ");
		sb.append("           and item.asscombid is not null ");
		sb.append("           and org.stdcode = '000223' ");
		sb.append("           and comb.projectid is not null ");
		sb.append("           and ((subject.stdcode like '720102%') or ");
		sb.append("               (subject.stdcode like '730102%') or ");
		sb.append("               (subject.stdcode like '740102%') or ");
		sb.append("          (subject.stdcode like '750102%') or ");
		sb.append("       (subject.stdcode like '790102%')) ");
		sb.append("     group by pro.stdname ");
		sb.append("     order by sum(item.debit) - sum(item.credit) desc) as result ");
        sb.append("    order by result.hj desc  ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(acctyear);
			rs = dbc.executeQuery();
			int num =0;
			while (rs.next()) {
			   if(num <=9){
				    num ++;
				    System.out.println(rs.getFields().get(0).getString()+"======"+rs.getFields().get(1).getDouble());
				 mapSR.put(rs.getFields().get(0).getString(),rs.getFields().get(1).getDouble());
			   }else{
				   break;
			   }
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			//�ͷ���Դ
			if(null != dbc){
				dbc.unuse();
			}
		}
		return mapSR;
	}

}
