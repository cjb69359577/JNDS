package com.jiuqi.jnds.cfpl6.repository;

import com.jiuqi.jnds.cfpl6.entity.GLVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * <h1>GLVoucherRepository</h1>
 *
 * <p>凭证主表Repository</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 13:48
 */
@Repository
public interface GLVoucherRepository extends JpaRepository<GLVoucher, UUID> {


}
