package com.tyc.wx.logger;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * 跟踪业务运行数据
 *
 * @author tyc
 * @version 1.0
 * @date 2022-12-26 17:51:35
 */
@Slf4j
public class TraceLogger {
    private static final Marker TRACE_MARKER = MarkerFactory.getMarker("Trace");


    /**
     * 业务名称:业务ID:标识(开始/结束):
     * @param message
     */
    public static void info(String message){
        log.info(TRACE_MARKER,message);
    }

}
