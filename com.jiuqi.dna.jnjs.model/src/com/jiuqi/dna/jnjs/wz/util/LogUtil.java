package com.jiuqi.dna.jnjs.wz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangzhe01
 * @date 2020Äê9ÔÂ11ÈÕ
 * 
 */
public class LogUtil {
	public static void log(String message){
		String nowdate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(new Date());
		System.out.println(nowdate + " : "+message);
	}
}
