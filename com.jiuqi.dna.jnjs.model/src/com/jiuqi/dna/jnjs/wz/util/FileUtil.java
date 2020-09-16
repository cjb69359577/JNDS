package com.jiuqi.dna.jnjs.wz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author wangzhe01
 * @date 2020Äê9ÔÂ11ÈÕ
 * 
 */
public class FileUtil {
	public static String readFileContent(String fileName) {
		InputStream in = FileUtil.class.getResourceAsStream(fileName);
		
		StringBuffer sbf = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr+"\r\n");
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}

	
	
	public static void main(String[] args) {
		System.out.println(readFileContent("/procedures/importVoucher_wangzhe01.sql"));

	}
}
