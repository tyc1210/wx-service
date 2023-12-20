package com.tyc.wx.exception;

import com.tyc.wx.model.ResultCode;
import lombok.Data;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 14:44:37
 */
@Data
public class CommonException extends RuntimeException{
    private Integer code;

    public CommonException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public CommonException(ResultCode code){
        super(code.getMsg());
        this.code = code.getCode();
    }

}
