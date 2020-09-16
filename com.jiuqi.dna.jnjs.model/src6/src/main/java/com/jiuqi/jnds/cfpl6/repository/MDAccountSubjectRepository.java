package com.jiuqi.jnds.cfpl6.repository;

import com.jiuqi.jnds.cfpl6.entity.MDAccountSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * <h1>MDAccountSubjectRepository</h1>
 *
 * <p>科目信息Repository</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 13:32
 */
@Repository
public interface MDAccountSubjectRepository extends JpaRepository<MDAccountSubject, UUID> {

    /***
     * 通过 科目信息Code 查询科目信息
     *
     * @param stdcode 科目信息Code
     * @return com.jiuqi.jnds.cfpl6.entity.MDAccountSubject  科目信息实体
     * @author Wang Xin
     * @date 2020/9/10 13:51
     */
    List<MDAccountSubject> findMDAccountSubjectByStdcodeAndAcctyear(String stdcode, Integer year);
}
