package com.jiuqi.jnds.cfpl6.repository;

import com.jiuqi.jnds.cfpl6.entity.MDExpendFuncClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * <h1>MDExpendFuncClassRepository</h1>
 *
 * <p>功能分类Repository</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 13:41
 */
@Repository
public interface MDExpendFuncClassRepository extends JpaRepository<MDExpendFuncClass, UUID> {


    /**
     * 通过功能分类代码查询功能分类信息
     *
     * @param stdcode 功能分类代码
     * @return com.jiuqi.jnds.cfpl6.entity.MDExpendFuncClass 功能分类信息实体类
     * @author Wang Xin
     * @date 2020/9/10 13:52
     */
    List<MDExpendFuncClass> findMDExpendFuncClassByStdcodeAndAcctyear(String stdcode, Integer year);
}
