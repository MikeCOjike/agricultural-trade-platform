package com.agritrade.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 农产品跨境贸易云平台主启动类
 * 
 * @author MikeCOjike
 * @version 1.0.0
 * @since 2024-01-01
 */
@SpringBootApplication
public class AgriTradePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgriTradePlatformApplication.class, args);
        System.out.println("🚀 农产品跨境贸易云平台启动成功！");
        System.out.println("📖 API文档地址: http://localhost:8080/swagger-ui.html");
        System.out.println("💡 数据库管理: http://localhost:8080/h2-console (开发环境)");
    }
}

