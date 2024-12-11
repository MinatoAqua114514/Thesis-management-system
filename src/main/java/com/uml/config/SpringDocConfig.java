package com.uml.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 配置接口文档基本信息
                .info(this.getApiInfo());
    }

    private Info getApiInfo() {
        return new Info()
                // 接口文档标题
                .title("毕业论文管理系统 API")
                // 接口文档描述
                .description("毕业论文 API 示例文档")
                // 接口作者信息
                .contact(new Contact().name("陈佳林").email("2314445395@qq.com"))
                // 概述信息
                .summary("毕业论文管理系统 API")
                // 接口版本
                .version("1.0.0")
                // 接口文档许可证信息
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }
}
