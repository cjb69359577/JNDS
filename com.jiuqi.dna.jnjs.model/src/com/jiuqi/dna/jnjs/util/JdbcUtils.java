package com.jiuqi.dna.jnjs.util;  
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


/**
 *   
 * @author Administrator
 *
 */
public class JdbcUtils {
	
	static int poolSize = 20;  
    static String driver = "oracle.jdbc.driver.OracleDriver";  
    static String url="jdbc:oracle:thin:@10.1.31.94:1521:orcl";  
    //static String url="jdbc:oracle:thin:@10.1.31.94:1521:orcl";  
    static String user="jnjs";  
    static String pwd = "jnjs";  
    public static Vector<Connection> pools = new Vector<Connection>();  
      
    public static Connection getConnection(){  
        try {  
            //1.加载驱动  
        	Class.forName(driver);
//        	new oracle.jdbc.driver.OracleDriver();
            //2.取得数据库连接  
            Connection conn =  DriverManager.getConnection(url, user, pwd);  
            return conn;  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;  
    }  
    public static void createSequence(){
    	Connection conn = null;
		Statement stmt = null;
		try {
			conn = JdbcUtils.getPool();
			StringBuffer sql = new StringBuffer("CREATE SEQUENCE cardcodesequence START WITH 5000001 NOMAXvalue INCREMENT BY 1 NOCYCLE CACHE 10");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				stmt = null;
			}
			if(conn != null){
				JdbcUtils.pools.add(conn);
			}
		}
    	
    }
    static {  
        int i = 0;  
        while(i<poolSize){  
	        pools.add(getConnection());  
	        i++;  
        }  
//        createSequence();
    }  
      
    public static synchronized Connection getPool(){  
        if(pools != null && pools.size() > 0){  
            int lastInd = pools.size() -1;  
            return pools.remove(lastInd);  
        }else{  
            return getConnection();  
        }  
    }  
}  