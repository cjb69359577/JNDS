package com.jiuqi.dna.jnjs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.jnjs.util.JdbcUtils;
import com.jiuqi.dna.jnjs.util.MD_Util;

/**
 * Service��������
 * ���ػ������ݵ�������
 * 
 * @author huangrui
 *
 */
public class MdServices extends Service {

	public MdServices() {
		super("MdServices");
	}

	@Override
	protected void init(Context context) throws Throwable {
		//��ȡ��֯��������
		getMd_org(context);
		//��ȡ��Ŀ��������
		getMd_project(context);
		//��ȡ֧�����û�������
		getMd_Expendeconclass(context);
		//��ȡ���ܻ�������
		getMd_Expendfuncclasst(context);
		//��ȡ��Ŀ��������
		getMd_Accountsubject(context);
		//ɾ����ʱ������
		deleteGAMS_ASSETCARD_TEMP(context);
		//������ʱ������
		insertGAMS_ASSETCARD_TEMP(context);
		//����ά���ʲ������ֵ
		updateGams_jc_assetclass(context);
		
	}
	
	/**
	 * ��ȡ��֯�ṹ��������
	 * @param conn
	 * @return
	 */
	public static void getMd_org(Context conn){
		MD_Util.mapORG.clear();
		MD_Util.mapORG_name.clear();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getMd_org() begin  ");
		sb.append(" SELECT a.recid as recid,a.stdcode as stdcode,a.stdname as stdname from md_org as a ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			rs = dbc.executeQuery();
			while (rs.next()) {
				MD_Util.mapORG.put(rs.getFields().get(1).getString(), rs.getFields().get(0).getGUID());
				MD_Util.mapORG_name.put(rs.getFields().get(0).getGUID().toString(), rs.getFields().get(2).getString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}
	
	/**
	 * ��ȡ��Ŀ��������
	 * @param conn
	 * @return
	 */
	public static void getMd_project(Context conn){
		MD_Util.mapXM.clear();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getMd_project() begin  ");
		sb.append(" SELECT a.recid as recid,a.stdcode as stdcode,a.acctyear as acctyear from md_project as a ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			rs = dbc.executeQuery();
			while (rs.next()) {
				MD_Util.mapXM.put(rs.getFields().get(1).getString()+"="+rs.getFields().get(2).getInt(), rs.getFields().get(0).getGUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}

	/**
	 * ��ȡ֧������
	 * @param conn
	 * @return
	 */
	public static void getMd_Expendfuncclasst(Context conn){
		MD_Util.mapGN.clear();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getMd_Expendfuncclasst() begin  ");
		sb.append(" SELECT a.recid as recid,a.stdcode as stdcode,a.acctyear as acctyear from Md_Expendfuncclass as a ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			rs = dbc.executeQuery();
			while (rs.next()) {
				MD_Util.mapGN.put(rs.getFields().get(1).getString()+"="+rs.getFields().get(2).getInt(), rs.getFields().get(0).getGUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}
	

	/**
	 * ��ȡ���û�������
	 * @param conn
	 * @return
	 */
	public static void getMd_Expendeconclass(Context conn){
		MD_Util.mapJJ.clear();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getMd_Expendeconclass() begin  ");
		sb.append(" SELECT a.recid as recid,a.stdcode as stdcode, a.acctyear as acctyear from Md_Expendeconclass as a ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			rs = dbc.executeQuery();
			while (rs.next()) {
				MD_Util.mapJJ.put(rs.getFields().get(1).getString()+"="+rs.getFields().get(2).getInt(), rs.getFields().get(0).getGUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}

	
	/**
	 * ��ȡ���û�������
	 * @param conn
	 * @return
	 */
	public static void getMd_Accountsubject(Context conn){
		MD_Util.mapKM.clear();
		StringBuffer sb = new StringBuffer();
		sb.append("  define query getMd_Accountsubject() begin  ");
		sb.append(" SELECT a.recid as recid,a.stdcode as stdcode,a.acctyear as acctyear from Md_Accountsubject as a ");
		sb.append("  end ");
		RecordSet rs = null;
		DBCommand dbc = null;
		try {
			dbc = conn.prepareStatement(sb.toString());
			rs = dbc.executeQuery();
			while (rs.next()) {
				MD_Util.mapKM.put(rs.getFields().get(1).getString()+"="+rs.getFields().get(2).getInt(), rs.getFields().get(0).getGUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql����ʧ�ܣ�����sql���ٲ�����");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}
	
	
	/**
	 * ��Ŀ������������ȡ����
	 */
	public static void insertGAMS_ASSETCARD_TEMP(Context conn){
		//Connection conn = JdbcUtils.getConnection();
		String tempSql = " SELECT * FROM (SELECT a.*,row_number() over(partition by a.objectid order by a.jizrq desc) as group_idx from gams_assetcard a ) r where r.group_idx = 1 ";
		String sql = " insert into gams_assetcard_temp(RECID,RECVER,BILLCODE,OBJECTID,ORGUNIT,JIAZ,LEIJZJ,JINGZ,SHUL,MIANJ,ZYMJ,XZMJ,CZJMJ,DCZMJ,ZYJZ,XZJZ,CZJJZ,DCZJZ,JIZRQ,SYZK,ZIFL,CLYT,CHEPH,CARDSTATE) "
				+ "  select RECID,RECVER,BILLCODE,OBJECTID,ORGUNIT,JIAZ,LEIJZJ,JINGZ,SHUL,MIANJ,ZYMJ,XZMJ,CZJMJ,DCZMJ,ZYJZ,XZJZ,CZJJZ,DCZJZ,JIZRQ,SYZK,ZIFL,CLYT,CHEPH,CARDSTATE from ("+tempSql+ ")";
		
		 Statement st =  conn.get(Statement.class);
    	 try {
			st.execute(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
	    	 try {
				st.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/*PreparedStatement ps = null;
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if (ps != null) {
					ps.close();
					}
					if(null != conn){
						conn.close();
					}
				} catch (SQLException e) {
			}
		}*/
	}
	
	/**
	 * ɾ����ʱ������
	 */
     public static void deleteGAMS_ASSETCARD_TEMP(Context conn){
    	 Statement st =  conn.get(Statement.class);
    	 try {
			st.execute(" truncate table gams_assetcard_temp");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
	    	 try {
				st.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
    		/*Connection conn = JdbcUtils.getConnection();
    		String sql = " truncate table gams_assetcard_temp ";
    		PreparedStatement ps = null;
    		try {
    			ps = conn.prepareStatement(sql);
    		    ps.execute();
    		} catch (SQLException e) {
    		} finally {
    				try {
    					if (ps != null) {
    					ps.close();
    					}
    					if(null != conn){
    						conn.close();
    					}
    				} catch (SQLException e) {
    			}
    		}*/
     }
     
     
     /**
      * ����ά���ʲ������ֵ
      */
      public static void updateGams_jc_assetclass(Context conn){
  	
	    	//Connection conn = JdbcUtils.getConnection();
	  		String sql = " update gams_jc_assetclass aa "+
                         "set aa.zicdl = case when aa.stdcode like '101%' then '���ء����ݼ�������' "+
	                     "    when aa.stdcode like '102%' then '���ء����ݼ�������' "+
	                     "    when aa.stdcode like '103%' then '���ء����ݼ�������' "+
	                     "    when aa.stdcode like '2%' then 'ͨ���豸' "+
	                     "    when aa.stdcode like '3%' then 'ר���豸' "+
	                     "    when aa.stdcode like '4%' then '����ͳ���Ʒ' "+
	                     "    when aa.stdcode like '5%' then 'ͼ�鵵��' "+
	                     "    when aa.stdcode like '601%' then '�Ҿߡ��þߡ�װ�߼���ֲ��' "+
	                     "    when aa.stdcode like '602%' then '�Ҿߡ��þߡ�װ�߼���ֲ��' "+
	                     "    when aa.stdcode like '603%' then '�Ҿߡ��þߡ�װ�߼���ֲ��'  "+
	                     "    when aa.stdcode like '604%' then '�Ҿߡ��þߡ�װ�߼���ֲ��' "+
	                     "    end";
	  		 Statement st =  conn.get(Statement.class);
	    	 try {
				st.execute(sql);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally{
		    	 try {
					st.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	  		/*PreparedStatement ps = null;
	  		try {
	  			ps = conn.prepareStatement(sql);
	  		    ps.execute();
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  		} finally {
	  				try {
	  					if (ps != null) {
	  					ps.close();
	  					}
	  					if(null != conn){
	  						conn.close();
	  					}
	  				} catch (SQLException e) {
	  			}
	  		}*/
      }
     

 	/**
 	 * boolean flag = ZclbfbService.isExistGAMS_ASSETCARD_TEMP();
 			if(flag){
 				ZclbfbService.insertGAMS_ASSETCARD_TEMP();
 			}
 			
 	       �鿴��Ƭ��ʱ���Ƿ��������
 	 * 
 	 */
 	public static boolean isExistGAMS_ASSETCARD_TEMP(){
 		//��ȡ������
 		Connection conn = JdbcUtils.getConnection();
 		String sql = " select count(*) from gams_assetcard_temp ";
 		PreparedStatement ps = null;
 		ResultSet rs = null;
 		try {
 			ps = conn.prepareStatement(sql);
 			rs = ps.executeQuery();
 			while (rs.next()) {
 				if(rs.getInt(1) >0 ){
 					return false;
 				}else{
 					return true;
 				}
 				
 			}
 		} catch (SQLException e) {
 			return false;
 		} finally {
 			//����ͷ���Դ
 				try {
 					if (ps != null) {
 					ps.close();
 					}
 					if(null != conn){
 						conn.close();
 					}
 				} catch (SQLException e) {
 			}
 		}
 		return true;
 	}
}
