package com.jiuqi.jnds.cfpl6.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jiuqi.jnds.cfpl6.entity.GLAssistComb;

/**
 * <h1>GLAssistCombRepository</h1>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 16:54
 */
@Repository
public interface GLAssistCombRepository extends JpaRepository<GLAssistComb, UUID> {

    List<GLAssistComb> findGLAssistCombByAcctyearAndProjectidAndExpendfuncclassidAndExpendeconclassid(Integer acctyear, UUID projectid, UUID expendfuncclassid, UUID expendeconclassid);

}
