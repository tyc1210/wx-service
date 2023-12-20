package com.tyc.wx.model.request;

import lombok.Data;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-19 10:00:28
 */
@Data
public class AdminLogPageReq extends PageReq{
    private String operatorName;
    private String optType;
    private String optModule;
}
