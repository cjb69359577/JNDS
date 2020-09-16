package com.jiuqi.jnds.cfpl6.repository;

import com.jiuqi.jnds.cfpl6.entity.MDExpendEconClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * <h1>MDExpendEconClassRepository</h1>
 *
 * <p>经济分类Repository</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 13:44
 */
@Repository
public interface MDExpendEconClassRepository extends JpaRepository<MDExpendEconClass, UUID> {

    /***
     * 通过经济分类代码查询经济分类信息
     *
     * @param stdcode 经济分类代码
     * @return com.jiuqi.jnds.cfpl6.entity.MDExpendEconClass 经济分类信息实体
     * @author Wang Xin
     * @date 2020/9/10 13:52
     */
    List<MDExpendEconClass> findMDExpendEconClassByStdcodeAndAcctyear(String stdcode, Integer year);
}
