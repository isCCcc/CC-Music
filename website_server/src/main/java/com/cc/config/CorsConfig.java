package com.cc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : WZX
 * @create 2022/5/22 22:09
 */
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 设置允许跨域的路径
                registry.addMapping("/**")
                        // 设置允许跨域请求的域名
                        .allowedOriginPatterns("*")
                        // 是否允许cookie
                        .allowCredentials(true)
                        // 设置允许的请求方式
                        .allowedMethods("GET", "POST", "DELETE", "PUT","HEAD","OPTION")
                        // 设置允许的header属性
                        .allowedHeaders("*")
                        // 跨域允许时间
                        .maxAge(3600);

            }
        };
    }

}