package com.tyc.wx.utils;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-20 14:11:22
 */
public class LogUtil {
    public static String getErrorMsg(Throwable throwable){
        String msg = "\n"+throwable.toString()+"\n";
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        for (int i = 0; i < stackTrace.length && i < 3; i++) {
            msg += "\t"+stackTrace[i].toString() + "\n";
        }
        return msg;
    }
}
