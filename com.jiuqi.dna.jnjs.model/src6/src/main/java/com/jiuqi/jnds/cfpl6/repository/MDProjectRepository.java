package com.jiuqi.jnds.cfpl6.repository;

import com.jiuqi.jnds.cfpl6.entity.MDProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * <h1>MDProjectRepository</h1>
 *
 * <p>项目信息Repository</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 13:37
 */
@Repository
public interface MDProjectRepository extends JpaRepository<MDProject, UUID> {

    /**
     * 通过项目信息代码查询项目信息
     *
     * @param stdcode 项目信息代码
     * @return com.jiuqi.jnds.cfpl6.entity.MDProject 项目信息实体
     * @author Wang Xin
     * @date 2020/9/10 13:53
     */
    List<MDProject> findMDProjectByStdcodeAndAcctyear(String stdcode, Integer year);
}
