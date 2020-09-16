package com.jiuqi.jnds.cfpl6.repository;

import com.jiuqi.jnds.cfpl6.entity.MDOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * <h1>MDOrgRepository</h1>
 *
 * <p>单位信息Repository</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 13:17
 */
@Repository
public interface MDOrgRepository extends JpaRepository<MDOrg, UUID> {

    /***
     * 通过单位信息代码查询单位信息
     *
     * @param stdcode 单位信息代码
     * @return com.jiuqi.jnds.cfpl6.entity.MDOrg 单位信息实体类
     * @author Wang Xin
     * @date 2020/9/10 13:53
     */
    List<MDOrg> findMDOrgByStdcode(String stdcode);
}
