package com.jiuqi.dna.jnjs.wz.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wangzhe01
 * @date 2020Äê9ÔÂ11ÈÕ
 * 
 */
public class ProceduresUtil {
	 
	 public static void createProcedures(){
		 Statement state; 
		 Connection conn = null;
		 try {
			 conn = ConnectionProvider.getConnection();
			 state = conn.createStatement();
			 state.execute(FileUtil.readFileContent("/procedures/importVoucher_wangzhe01.sql"));
			 state.execute(FileUtil.readFileContent("/procedures/importVoucherDetail_wangzhe01.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
