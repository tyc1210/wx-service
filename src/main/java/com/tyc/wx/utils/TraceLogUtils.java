package com.tyc.wx.utils;

import java.util.UUID;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-12-27 11:03:54
 */
public class TraceLogUtils {
    public static String  getTraceId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
