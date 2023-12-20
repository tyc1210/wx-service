package com.tyc.wx.controller;


import com.tyc.wx.common.annotation.ApiV1RestController;
import com.tyc.wx.common.annotation.OperateLog;
import com.tyc.wx.model.CommonResult;
import com.tyc.wx.model.OperatingModule;
import com.tyc.wx.model.OperatingType;
import com.tyc.wx.model.request.AdminLogPageReq;
import com.tyc.wx.model.request.LoginReq;
import com.tyc.wx.service.IAdminService;
import com.tyc.wx.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 14:34:17
 */
@ApiV1RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private ILogService logService;

    @PostMapping("/login")
    @OperateLog(operatingModule = OperatingModule.LOGIN, operatingType = OperatingType.LOGIN)
    public CommonResult login(@RequestBody @Valid LoginReq loginReq){
        return CommonResult.success(adminService.login(loginReq));
    }

    @PostMapping("/log/list")
    public CommonResult getLogList(@RequestBody AdminLogPageReq adminLogPageReq){
        return CommonResult.success(logService.getPageNoticeLog(adminLogPageReq));
    }
}
