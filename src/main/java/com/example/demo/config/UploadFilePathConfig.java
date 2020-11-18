package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class UploadFilePathConfig implements WebMvcConfigurer {
    @Value("${zyj.uploaddir}")
    private String uploadDir;
    @Value("${zyj.accessPath}")
    private String accessPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(accessPath).addResourceLocations("file:" + uploadDir);
    }
}
