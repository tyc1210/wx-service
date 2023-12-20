package com.tyc.wx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-18 18:09:32
 */
@Data
@TableName("admin_operate_log")
@NoArgsConstructor
public class AdminOperateLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String operatorName;
    private String optIp;
    private Timestamp optTime;
    private String optType;
    private String optModule;
    private String optContent;

    public AdminOperateLog(String operatorName, Timestamp optTime, String optType, String optModule, String optContent) {
        this.operatorName = operatorName;
        this.optTime = optTime;
        this.optType = optType;
        this.optModule = optModule;
        this.optContent = optContent;
    }
}
