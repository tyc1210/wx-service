package com.tyc.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-20 11:35:55
 */
@SpringBootApplication
public class WxServiceApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WxServiceApplication.class);
        application.run(args);
    }
}
