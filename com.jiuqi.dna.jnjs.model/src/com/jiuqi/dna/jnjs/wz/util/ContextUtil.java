package com.jiuqi.dna.jnjs.wz.util;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.spi.application.AppUtil;

/**
 * @author wangzhe01
 * @date 2020年9月10日
 * 
 */
public class ContextUtil {
	/**
	  * 创建上下文环境
	  * 
	  * @return
	  */
	 public static Context getContext() {
	     return AppUtil.getDefaultApp().getSystemSession().newContext(false);
	 }
}
