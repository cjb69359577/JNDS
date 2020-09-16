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
 * Service发布服务
 * 加载基础数据到缓存中
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
		//获取组织基础数据
		getMd_org(context);
		//获取项目基础数据
		getMd_project(context);
		//获取支出经济基础数据
		getMd_Expendeconclass(context);
		//获取功能基础数据
		getMd_Expendfuncclasst(context);
		//获取科目基础数据
		getMd_Accountsubject(context);
		//删除临时表数据
		deleteGAMS_ASSETCARD_TEMP(context);
		//插入临时表数据
		insertGAMS_ASSETCARD_TEMP(context);
		//更新维护资产大类的值
		updateGams_jc_assetclass(context);
		
	}
	
	/**
	 * 获取组织结构基础数据
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
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}
	
	/**
	 * 获取项目基础数据
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
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}

	/**
	 * 获取支出功能
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
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}
	

	/**
	 * 获取经济基础数据
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
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}

	
	/**
	 * 获取经济基础数据
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
			System.out.println("sql编译失败，请检查sql后再操作。");
		}finally{
			if(null != dbc){
				dbc.unuse();
			}
		}
	}
	
	
	/**
	 * 将目标结果的数据提取出来
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
	 * 删除临时表数据
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
      * 更新维护资产大类的值
      */
      public static void updateGams_jc_assetclass(Context conn){
  	
	    	//Connection conn = JdbcUtils.getConnection();
	  		String sql = " update gams_jc_assetclass aa "+
                         "set aa.zicdl = case when aa.stdcode like '101%' then '土地、房屋及构筑物' "+
	                     "    when aa.stdcode like '102%' then '土地、房屋及构筑物' "+
	                     "    when aa.stdcode like '103%' then '土地、房屋及构筑物' "+
	                     "    when aa.stdcode like '2%' then '通用设备' "+
	                     "    when aa.stdcode like '3%' then '专用设备' "+
	                     "    when aa.stdcode like '4%' then '文物和陈列品' "+
	                     "    when aa.stdcode like '5%' then '图书档案' "+
	                     "    when aa.stdcode like '601%' then '家具、用具、装具及动植物' "+
	                     "    when aa.stdcode like '602%' then '家具、用具、装具及动植物' "+
	                     "    when aa.stdcode like '603%' then '家具、用具、装具及动植物'  "+
	                     "    when aa.stdcode like '604%' then '家具、用具、装具及动植物' "+
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
 			
 	       查看卡片临时表是否存在数据
 	 * 
 	 */
 	public static boolean isExistGAMS_ASSETCARD_TEMP(){
 		//获取连连接
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
 			//最后释放资源
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
