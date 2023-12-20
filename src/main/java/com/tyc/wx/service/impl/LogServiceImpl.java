package com.tyc.wx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tyc.wx.entity.AdminOperateLog;
import com.tyc.wx.mapper.LogMapper;
import com.tyc.wx.model.request.AdminLogPageReq;
import com.tyc.wx.service.ILogService;
import com.tyc.wx.utils.IpUtil;
import com.tyc.wx.utils.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-19 09:32:39
 */
@Service
public class LogServiceImpl implements ILogService {
    @Autowired
    private LogMapper logMapper;

    @Autowired
    private SessionContext context;

    @Override
    public void save(AdminOperateLog adminOperateLog) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String authIp = IpUtil.getIpAddr(request);
        adminOperateLog.setOptIp(authIp);
        Map<String, String> session = context.getSession(request.getHeader("sid"));
        if (session != null){
            String userName = session.get("username");
            adminOperateLog.setOperatorName(userName);
        }
        logMapper.insert(adminOperateLog);
    }

    @Override
    public Page<AdminOperateLog> getPageNoticeLog(AdminLogPageReq adminLogPageReq) {
        Page<AdminOperateLog> page = new Page<>(adminLogPageReq.getPage(),adminLogPageReq.getSize());
        QueryWrapper<AdminOperateLog> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(adminLogPageReq.getOperatorName())){
            queryWrapper.eq("operatorName",adminLogPageReq.getOperatorName());
        }
        if(StringUtils.isNotBlank(adminLogPageReq.getOptType())){
            queryWrapper.eq("optType",adminLogPageReq.getOptType());
        }
        if(StringUtils.isNotBlank(adminLogPageReq.getOptModule())){
            queryWrapper.eq("optModule",adminLogPageReq.getOptModule());
        }
        queryWrapper.orderByDesc("optTime");
        return logMapper.selectPage(page, queryWrapper);
    }
}
