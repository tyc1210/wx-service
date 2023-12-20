package com.tyc.wx.service;


import com.tyc.wx.model.request.LoginReq;

public interface IAdminService {
    String login(LoginReq loginReq);
}
