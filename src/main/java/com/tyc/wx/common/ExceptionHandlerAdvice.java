package com.tyc.wx.common;


import com.tyc.wx.exception.CommonException;
import com.tyc.wx.model.CommonResult;
import com.tyc.wx.model.ResultCode;
import com.tyc.wx.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-12 11:18:56
 */

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        if (e instanceof CommonException) {
            CommonException ex = (CommonException) e;
            return CommonResult.failed(ex.getCode(),ex.getMessage());
        }
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return CommonResult.failed(ResultCode.PARAM_CHECK_FAILED,ex.getBindingResult().getFieldError().getDefaultMessage());
        }
        log.error("system error:", LogUtil.getErrorMsg(e));
        return CommonResult.failed(500,e.getMessage());
    }
}
