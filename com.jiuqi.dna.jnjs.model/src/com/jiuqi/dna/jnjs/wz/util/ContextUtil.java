package com.jiuqi.dna.jnjs.wz.util;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.spi.application.AppUtil;

/**
 * @author wangzhe01
 * @date 2020��9��10��
 * 
 */
public class ContextUtil {
	/**
	  * ���������Ļ���
	  * 
	  * @return
	  */
	 public static Context getContext() {
	     return AppUtil.getDefaultApp().getSystemSession().newContext(false);
	 }
}
