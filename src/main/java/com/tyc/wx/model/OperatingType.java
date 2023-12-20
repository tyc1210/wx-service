package com.tyc.wx.model;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-18 18:13:57
 */
public enum OperatingType {
    LOGIN("登录"),
    LOGOUT("退出"),
    ADD("添加"),
    DELETE("删除"),
    EDIT("修改"),
    QUERY("查询");

    String description;

    OperatingType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
