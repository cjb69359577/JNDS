package com.jiuqi.jnds.cfpl6.repository;

import com.jiuqi.jnds.cfpl6.entity.GLVoucherItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <h1>GLVoucherItemRepository</h1>
 *
 * <p>凭证子表Repository</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 13:49
 */
@Repository
public interface GLVoucherItemRepository extends JpaRepository<GLVoucherItem, UUID> {
}
