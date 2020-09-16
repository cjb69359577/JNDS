package com.jiuqi.dna.jnjs.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.spi.application.ContextProvider;
import com.jiuqi.dna.jnjs.consts.WebCode;
import com.jiuqi.dna.jnjs.entity.ImportVoucherTask;
import com.jiuqi.dna.jnjs.wz.util.ConnectionProvider;
import com.jiuqi.dna.jnjs.wz.util.LogUtil;
import com.jiuqi.dna.jnjs.wz.util.ProceduresUtil;

/**
 * @author wangzhe01
 * @date 2020��9��10��
 * 
 */
public class SaveServic extends Service {
	protected SaveServic() {
		super("SaveServic");
	}
	
	@Override
	protected void init(Context context) throws Throwable {
		//��������ʱ����һ��Ψһ��context�ṩ��
		ContextProvider provider = new ContextProvider(context);
		ConnectionProvider.setContextProvider(provider);
		//˳�����洢�����ļ�
		ProceduresUtil.createProcedures();
	}

	@Publish	
	protected class importVoucher extends  SimpleTaskMethodHandler<ImportVoucherTask> {

		@Override
		protected void handle(Context context, ImportVoucherTask task) throws Throwable {
			try {
				save(context, task.getExcelData());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private static void save(Context context, List<Map<String, Object>> excelData) throws Throwable {
		LogUtil.log("��ʼִ�е��룬�ļ�����"+excelData.size()+"��");
		long start = System.currentTimeMillis();
		Connection conn = ConnectionProvider.getConnection();
		String databaseName = conn.getMetaData().getDatabaseProductName();
		if(!databaseName.equalsIgnoreCase("Oracle")){
			throw  new RuntimeException("���޷�oracle�洢���̵ĵ��뷽��");
		}
		List<String> skipRecid = new ArrayList<String>();
		List<String> insertRecid = new ArrayList<String>();
		Map<String,Integer> orderMap = new HashMap<String, Integer>();
		for(Map<String, Object> rowData : excelData){
			String recid = (String) rowData.get("ƾ֤��ʶ");
			String message = "";
			if(!skipRecid.contains(recid)&&!insertRecid.contains(recid)){
				message = insertVoucherOracle(context, conn, rowData);
			}
			
			if(message.equals(WebCode.SUCCESS)){
				insertRecid.add(recid);
				orderMap.put(recid, 0);
			}
			if(message.equals(WebCode.SKIP)){
				skipRecid.add(recid);
			}
			if(message.startsWith(WebCode.ERROR)){
				throw  new RuntimeException("��������ƾ֤��"+recid+"�������쳣��"+message);
			}
			if(insertRecid.contains(recid)){
				orderMap.put(recid, orderMap.get(recid)+1);
				message = insertVoucherItemOracle(context, conn, rowData, orderMap.get(recid));
			}
			if(message.startsWith(WebCode.ERROR)){
				throw  new RuntimeException("�����ӱ�ƾ֤��"+recid+"�������쳣��"+message);
			}

		}
		int sumDetail = 0;
		for(String insertID : insertRecid){
			sumDetail =sumDetail+orderMap.get(insertID);
		}
		long end = System.currentTimeMillis();
		StringBuffer logMessage = new StringBuffer();
		logMessage.append("Excel������ɣ����Ƶ��뵥�ݣ�" + (skipRecid.size() + insertRecid.size()) + " ����\r\n");
		logMessage.append("���������Ѵ��ڵ��ݣ�" + skipRecid.size() + "����\r\n");
		logMessage.append("���в��뵥�ݣ�" + insertRecid.size() + "���������ӱ�"+sumDetail+"��\r\n");
		logMessage.append("�ܼƺ�ʱ��" + (end - start) + "ms\r\n");
		LogUtil.log(logMessage.toString());
		
	}
	private static String insertVoucherOracle(Context context, Connection conn, Map<String, Object> rowData){
		//׼�����Ӳ���
		String message = null;
		CallableStatement cStmt = null;
		//��ȡ��������
		String recid = (String) rowData.get("ƾ֤��ʶ");
		long recver = context.newRECVER();
		String vchrnum = (String) rowData.get("ƾ֤��");
		int acctyear = (int) (Double.parseDouble((String) rowData.get("���")));
		int acctperiod = (int) (Double.parseDouble((String) rowData.get("�ڼ�")));
		String orgcode = (String) rowData.get("��λ����");
		String createdate = (String) rowData.get("ƾ֤����");	
		//��ʼ����
		try {
			cStmt = conn.prepareCall("{call importVoucher_wangzhe01(?, ?, ?, ?, ?, ?, ?, ?)}");
			cStmt.setString(1, recid);
			cStmt.setLong(2, recver);
			cStmt.setString(3, vchrnum);
			cStmt.setInt(4, acctyear);
			cStmt.setInt(5, acctperiod);
			cStmt.setString(6, orgcode);
			cStmt.setString(7, createdate);
			cStmt.registerOutParameter(8, Types.VARCHAR);
			cStmt.execute();
			message = cStmt.getString(8);
			return message;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return message;		
	}
	
	private static String insertVoucherItemOracle(Context context, Connection conn, Map<String, Object> rowData,int itemorder){
		//׼�����Ӳ���
		String message = null;
		CallableStatement cStmt = null;
		//��ȡ�ӱ�����
		String recid = (String) rowData.get("ƾ֤��ʶ");
		int acctyear =  (int) (Double.parseDouble((String) rowData.get("���")));
		int p_itemorder = itemorder;
		double p_debit = Double.valueOf( (String) rowData.get("�跽���"));
		double p_credit =Double.valueOf( (String) rowData.get("�������"));
		String p_km_code = (String) rowData.get("��Ŀ����");
		String p_xm_code = (String) rowData.get("��Ŀ����");
		String p_gn_code = (String) rowData.get("���ܷ������");
		String p_jj_code = (String) rowData.get("���÷������");
		//��ʼ����
		try {
			cStmt = conn.prepareCall("{call importVoucherDetail_wangzhe01(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			cStmt.setString(1, recid);
			cStmt.setInt(2, acctyear);
			cStmt.setInt(3, p_itemorder);
			cStmt.setDouble(4, p_debit);
			cStmt.setDouble(5, p_credit);
			cStmt.setString(6, p_km_code);
			cStmt.setString(7, p_xm_code);
			cStmt.setString(8, p_gn_code);
			cStmt.setString(9, p_jj_code);
			cStmt.registerOutParameter(10, Types.VARCHAR);
			cStmt.execute();
			message = cStmt.getString(10);
			return message;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return message;		
	}
}
