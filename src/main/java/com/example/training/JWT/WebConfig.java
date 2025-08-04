package com.example.training.JWT;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Cho phép tất cả endpoint
                .allowedOrigins("http://localhost:3000")  // Cho phép frontend React
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Các method được phép
                .allowedHeaders("*")  // Cho phép tất cả header
                .allowCredentials(true)  // Cho phép gửi cookie, header chứng thực nếu có
                .maxAge(3600);  // Thời gian trình duyệt cache cấu hình CORS (giây)
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:upload/");
    }


}
