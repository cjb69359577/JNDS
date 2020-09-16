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
 * @date 2020年9月10日
 * 
 */
public class SaveServic extends Service {
	protected SaveServic() {
		super("SaveServic");
	}
	
	@Override
	protected void init(Context context) throws Throwable {
		//在启服务时定义一个唯一的context提供器
		ContextProvider provider = new ContextProvider(context);
		ConnectionProvider.setContextProvider(provider);
		//顺便插入存储过程文件
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
		LogUtil.log("开始执行导入，文件数据"+excelData.size()+"行");
		long start = System.currentTimeMillis();
		Connection conn = ConnectionProvider.getConnection();
		String databaseName = conn.getMetaData().getDatabaseProductName();
		if(!databaseName.equalsIgnoreCase("Oracle")){
			throw  new RuntimeException("暂无非oracle存储过程的导入方案");
		}
		List<String> skipRecid = new ArrayList<String>();
		List<String> insertRecid = new ArrayList<String>();
		Map<String,Integer> orderMap = new HashMap<String, Integer>();
		for(Map<String, Object> rowData : excelData){
			String recid = (String) rowData.get("凭证标识");
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
				throw  new RuntimeException("导入主表（凭证："+recid+"）发生异常："+message);
			}
			if(insertRecid.contains(recid)){
				orderMap.put(recid, orderMap.get(recid)+1);
				message = insertVoucherItemOracle(context, conn, rowData, orderMap.get(recid));
			}
			if(message.startsWith(WebCode.ERROR)){
				throw  new RuntimeException("导入子表（凭证："+recid+"）发生异常："+message);
			}

		}
		int sumDetail = 0;
		for(String insertID : insertRecid){
			sumDetail =sumDetail+orderMap.get(insertID);
		}
		long end = System.currentTimeMillis();
		StringBuffer logMessage = new StringBuffer();
		logMessage.append("Excel导入完成，共计导入单据：" + (skipRecid.size() + insertRecid.size()) + " 个。\r\n");
		logMessage.append("其中跳过已存在单据：" + skipRecid.size() + "个。\r\n");
		logMessage.append("其中插入单据：" + insertRecid.size() + "个。插入子表："+sumDetail+"个\r\n");
		logMessage.append("总计耗时：" + (end - start) + "ms\r\n");
		LogUtil.log(logMessage.toString());
		
	}
	private static String insertVoucherOracle(Context context, Connection conn, Map<String, Object> rowData){
		//准备连接参数
		String message = null;
		CallableStatement cStmt = null;
		//提取主表数据
		String recid = (String) rowData.get("凭证标识");
		long recver = context.newRECVER();
		String vchrnum = (String) rowData.get("凭证号");
		int acctyear = (int) (Double.parseDouble((String) rowData.get("年度")));
		int acctperiod = (int) (Double.parseDouble((String) rowData.get("期间")));
		String orgcode = (String) rowData.get("单位代码");
		String createdate = (String) rowData.get("凭证日期");	
		//开始连接
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
		//准备连接参数
		String message = null;
		CallableStatement cStmt = null;
		//提取子表数据
		String recid = (String) rowData.get("凭证标识");
		int acctyear =  (int) (Double.parseDouble((String) rowData.get("年度")));
		int p_itemorder = itemorder;
		double p_debit = Double.valueOf( (String) rowData.get("借方金额"));
		double p_credit =Double.valueOf( (String) rowData.get("贷方金额"));
		String p_km_code = (String) rowData.get("科目代码");
		String p_xm_code = (String) rowData.get("项目代码");
		String p_gn_code = (String) rowData.get("功能分类代码");
		String p_jj_code = (String) rowData.get("经济分类代码");
		//开始连接
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
