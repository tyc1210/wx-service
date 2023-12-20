package com.tyc.wx.config;

import com.tyc.wx.common.annotation.ApiV1RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-20 13:42:20
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ApiPrefix apiPrefix;
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(apiPrefix.getV1(), c -> c.isAnnotationPresent(ApiV1RestController.class));
    }
}

