package com.tyc.wx.logger;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * 需要打点数据
 *
 * @author tyc
 * @version 1.0
 * @date 2021-10-28 14:44:35
 */
@Slf4j
public class DataLogger {
    private static final Marker DATA_MARKER = MarkerFactory.getMarker("DATA");

    /**
     * 记录需存储日志
     * @param logType 数据类型
     * @param msg json数据
     */
    public static void info(String logType, String msg){
        log.info(DATA_MARKER,"{}|{}", logType, msg);
    }
}
