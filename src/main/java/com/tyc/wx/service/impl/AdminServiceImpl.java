package com.tyc.wx.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tyc.wx.entity.AdminUser;
import com.tyc.wx.exception.CommonException;
import com.tyc.wx.logger.DataLogger;
import com.tyc.wx.mapper.AdminMapper;
import com.tyc.wx.model.ResultCode;
import com.tyc.wx.model.request.LoginReq;
import com.tyc.wx.service.IAdminService;
import com.tyc.wx.utils.RSAEncrypt;
import com.tyc.wx.utils.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 14:35:52
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private SessionContext context;

    @Override
    public String login(LoginReq loginReq) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginReq.getUserName());
        AdminUser adminUser = adminMapper.selectOne(queryWrapper);
        String password = null;
        try {
            password = RSAEncrypt.decrypt(loginReq.getPassWord());
        } catch (Exception e) {
            throw new CommonException(ResultCode.DECODE_ERROR);
        }
        if(!password.equals(adminUser.getPsw())){
            throw new CommonException(ResultCode.USER_LOGIN_FAILED);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession(true);
        Map<String, String> map = new HashMap<>();
        map.put("username", loginReq.getUserName());
        map.put("id", session.getId());
        map.put("role",adminUser.getRole().toString());
        context.addSession(map);
        DataLogger.info("login", JSONUtil.toJsonStr(adminUser));
        return session.getId();
    }
}
