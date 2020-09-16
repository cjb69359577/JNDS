package com.jiuqi.dna.jnjs.wz.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.jiuqi.dna.core.spi.application.ContextProvider;

/**
 * @author wangzhe01
 * @date 2020��9��11��
 * 
 */
public class ConnectionProvider {
	private static ContextProvider contextProvider;
	
	/**
	 * ��ȡһ�����ݿ�����
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return contextProvider.getCurrentContext().get(Connection.class);
	}
	
	/**
	 * ����context��ȡ��
	 * @param p
	 */
	public static void setContextProvider(ContextProvider p ){
		contextProvider = p;
	}
}
