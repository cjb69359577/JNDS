package com.jiuqi.dna.jnjs.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;

/**
 * ����ƾ֤����
 * @author zhangwei
 *
 */
public class InsertVoucherService {

	/**
	 * ����ƾ֤����
	 * @param conn
	 * @return
	 */
	public static void insertVoucher(Context conn){
		List<String> list = new ArrayList<String>();
		DBCommand db = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" define insert insertVoucher(@RECID guid,@RECVER int,@VCHRNUM int ,@ACCTYEAR int ,@ACCTPERIOD int ,@UNITID guid,@CREATEDATE date,@CREATETIME date)");
		sql.append(" begin ");
		sql.append(" insert into GL_VOUCHER (RECID,RECVER,VCHRNUM,ACCTYEAR,ACCTPERIOD,UNITID,CREATEDATE,CREATETIME ) ");
		sql.append(" values (@RECID,@RECVER,@VCHRNUM,@ACCTYEAR,@ACCTPERIOD,@UNITID,@CREATEDATE,@CREATETIME ) ");
		sql.append(" end ");
		db=conn.prepareStatement(sql.toString());
		for(int i = 0 ;i < list.size() ;i++ ){
				db.setArgumentValues();
	             db.addBatch();
		}
		if(null != db){
			db.executeBatch();
			db.clearBatch();
			db.unuse();
		}
	}
	
	/**
	 * ����ƾ֤��¼����
	 * @param conn
	 * @return
	 */
	public static void insertVoucherItem(Context conn){
		List<String> list = new ArrayList<String>();
		DBCommand db = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" define insert insertVoucherItem(@RECID guid,@RECVER int,@VCHRID guid,@ITEMORDER int ,@SUBJECTID guid,@DEBIT double,@CREDIT double,@ASSCOMBID guid)");
		sql.append(" begin ");
		sql.append(" insert into GL_VOUCHERITEM (RECID,RECVER,VCHRID,ITEMORDER,SUBJECTID,DEBIT,CREDIT,ASSCOMBID ) ");
		sql.append(" values (@RECID,@RECVER,@VCHRID,@ITEMORDER,@SUBJECTID,@DEBIT,@CREDIT,@ASSCOMBID ) ");
		sql.append(" end ");
		db=conn.prepareStatement(sql.toString());
		for(int i = 0 ;i < list.size() ;i++ ){
				db.setArgumentValues();
	             db.addBatch();
		}
		if(null != db){
			db.executeBatch();
			db.clearBatch();
			db.unuse();
		}
	}
	
	/**
	 * ���븨���������
	 * @param conn
	 * @return
	 */
	public static void insertAl_assistcomb(Context conn){
		List<String> list = new ArrayList<String>();
		DBCommand db = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" define insert insertAl_assistcomb(@RECID guid,@RECVER int,@ACCYEAR int ,@PROJECTID guid,@EXPENDFUNCCLASSID guid,@EXPENDECONCLASSID guid)");
		sql.append(" begin ");
		sql.append(" insert into gl_assistcomb (RECID,RECVER,ACCYEAR,PROJECTID,EXPENDFUNCCLASSID,EXPENDECONCLASSID ) ");
		sql.append(" values (@RECID,@RECVER,@ACCYEAR,@PROJECTID,@EXPENDFUNCCLASSID,@EXPENDECONCLASSID ) ");
		sql.append(" end ");
		db=conn.prepareStatement(sql.toString());
		for(int i = 0 ;i < list.size() ;i++ ){
				db.setArgumentValues();
	            db.addBatch();
		}
		if(null != db){
			db.executeBatch();
			db.clearBatch();
			db.unuse();
		}
	}
	

	/**
	 * �������servlce
	 * @param conn
	 * @return
	 */
	public static boolean isExistVoucherByid(Context conn,GUID RECID){
		StringBuffer sb = new StringBuffer();
		sb.append("  define query isExistVoucherByid(@RECID guid) begin  ");
		sb.append("  SELECT A.RECID AS RECID FROM GL_VOUCHER AS A  ");
		sb.append("  WHERE A.RECID=@RECID ");
		sb.append(" end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			dbc.setArgumentValues(RECID);
			rs = dbc.executeQuery();
			while (rs.next()) {
				if(null != rs.getFields().get(0).getGUID()){
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
		return false;
	}

	
	
	
}
