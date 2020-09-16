package com.jiuqi.dna.jnjs.wz.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.jiuqi.dna.core.spi.application.ContextProvider;

/**
 * @author wangzhe01
 * @date 2020年9月11日
 * 
 */
public class ConnectionProvider {
	private static ContextProvider contextProvider;
	
	/**
	 * 获取一个数据库连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return contextProvider.getCurrentContext().get(Connection.class);
	}
	
	/**
	 * 定义context提取器
	 * @param p
	 */
	public static void setContextProvider(ContextProvider p ){
		contextProvider = p;
	}
}
