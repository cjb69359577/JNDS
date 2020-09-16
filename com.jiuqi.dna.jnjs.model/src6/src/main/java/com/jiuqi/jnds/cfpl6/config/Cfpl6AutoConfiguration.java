package com.jiuqi.jnds.cfpl6.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author xiongkang
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.jiuqi.jnds.cfpl6.repository")
@EntityScan(basePackages = "com.jiuqi.jnds.cfpl6.entity")
@ComponentScan(basePackages = "com.jiuqi.jnds.*")
public class Cfpl6AutoConfiguration {

}
