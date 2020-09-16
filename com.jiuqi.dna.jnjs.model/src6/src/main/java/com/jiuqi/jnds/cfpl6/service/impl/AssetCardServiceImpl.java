package com.jiuqi.jnds.cfpl6.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jiuqi.jnds.cfpl6.entity.AssetCard;
import com.jiuqi.jnds.cfpl6.repository.AssetCardRepository;
import com.jiuqi.jnds.cfpl6.service.AssetCardService;

@Service
public class AssetCardServiceImpl implements AssetCardService {
	
	@Autowired
	private AssetCardRepository cardRepo;

	@Override
	public List<AssetCard> listAll(int start, int count) {
		return cardRepo.findAll(PageRequest.of(start, count)).getContent();
	}

}
