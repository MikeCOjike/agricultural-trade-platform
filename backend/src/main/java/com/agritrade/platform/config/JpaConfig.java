package com.agritrade.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA配置
 * 
 * @author MikeCOjike
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // 启用JPA审计功能，自动填充@CreatedDate和@LastModifiedDate
}
