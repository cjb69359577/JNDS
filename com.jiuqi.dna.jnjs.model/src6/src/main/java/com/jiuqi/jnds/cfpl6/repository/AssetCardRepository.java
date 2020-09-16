package com.jiuqi.jnds.cfpl6.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiuqi.jnds.cfpl6.entity.AssetCard;

/**
 * 卡片表操作接口
 * @author xiongkang
 *
 */
@Repository
public interface AssetCardRepository extends JpaRepository<AssetCard, UUID> {

}
