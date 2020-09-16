package com.jiuqi.jnds.cfpl6.service;

import java.util.List;

import com.jiuqi.jnds.cfpl6.entity.AssetCard;

public interface AssetCardService {
	
	List<AssetCard> listAll(int start, int count);
	
}
