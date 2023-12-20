package com.tyc.wx.common.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @author admin
 */
@RestController
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ApiV1RestController {
}
