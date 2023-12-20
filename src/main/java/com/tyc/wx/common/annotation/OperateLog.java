package com.tyc.wx.common.annotation;


import com.tyc.wx.model.OperatingModule;
import com.tyc.wx.model.OperatingType;

import java.lang.annotation.*;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-18 18:13:30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface OperateLog {
    OperatingType operatingType();

    OperatingModule operatingModule();
}

