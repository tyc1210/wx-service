package com.tyc.wx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tyc.wx.entity.AdminOperateLog;
import com.tyc.wx.model.request.AdminLogPageReq;


public interface ILogService {
    void save(AdminOperateLog adminOperateLog);

    Page<AdminOperateLog> getPageNoticeLog(AdminLogPageReq adminLogPageReq);
}
