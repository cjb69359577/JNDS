package com.jiuqi.dna.jnjs.entity;

import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * @author wangzhe01
 * @date 2020��9��11��
 * 
 */
public class ImportVoucherTask extends SimpleTask {

	private  List<Map<String, Object>> excelData;

	public List<Map<String, Object>> getExcelData() {
		return excelData;
	}

	public void setExcelData(List<Map<String, Object>> excelData) {
		this.excelData = excelData;
	}
	
	
}
