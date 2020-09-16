package com.jiuqi.jnds.cfpl6.service;

import java.util.List;
import java.util.Map;

public interface ComplexQueryService {

	Map<String, Object> queryByClass();

	List<Map<String, Object>> queryByUsedStatus();

}
