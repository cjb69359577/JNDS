package com.jiuqi.jnds.cfpl6.query;

/**
 * 数据提取查询参数
 * @author xiongkang
 *
 */
public class CalculationQueryParam {
	private String calculationType;
	private int currentPage = 1;
	private int pageSize = 20;
	
	/**
	 * {@link CalculationType}
	 * @return
	 */
	public String getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
