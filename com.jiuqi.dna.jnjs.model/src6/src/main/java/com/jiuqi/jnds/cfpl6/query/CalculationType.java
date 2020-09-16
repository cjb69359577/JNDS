package com.jiuqi.jnds.cfpl6.query;

/**
 * 计算提取表类型
 * @author xiongkang
 *
 */
public enum CalculationType {

	/**
	 * 收入支出固定表
	 */
	SRZCGDB("srzcgdb"),
	/**
	 * 项目支出决算明细表
	 */
	XMZCJSMXB("xmzcjsmxb"),
	/**
	 * 车辆情况表
	 */
	CLQKB("clqkb"),
	/**
	 * 固定和无形资产存量情况表
	 */
	GDHWXZCCLQKB("gdhwxzcclqkb");
	
	private String value;
	
	private CalculationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static CalculationType getByValue(String value) {
		if (value == null) {
			return null;
		}
		for (CalculationType ct : CalculationType.values()) {
			if (ct.getValue().equals(value)) {
				return ct;
			}
		}
		return null;
	}
	
}
