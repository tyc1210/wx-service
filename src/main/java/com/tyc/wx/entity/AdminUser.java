package com.tyc.wx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 14:28:56
 */
@Data
@TableName("admin_user")
public class AdminUser {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String psw;
    private Integer role;
    private Date createdTime;
    private Date updatedTime;
}
