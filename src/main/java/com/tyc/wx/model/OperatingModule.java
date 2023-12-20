package com.tyc.wx.model;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-18 18:14:20
 */
public enum OperatingModule {
    LOGIN("登录模块");
    String description;

    OperatingModule(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
