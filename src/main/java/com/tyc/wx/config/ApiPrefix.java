package com.tyc.wx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-20 13:40:43
 */
@Data
@Component
@ConfigurationProperties(prefix = "api.prefix")
public class ApiPrefix {
    private String v1;
}
