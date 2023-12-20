package com.tyc.wx.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 14:36:31
 */
@Data
public class LoginReq {
    @NotEmpty(message = "userName is null")
    private String userName;
    @NotEmpty(message = "passWord is null")
    private String passWord;
}
