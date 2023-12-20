package com.tyc.wx;

import cn.hutool.crypto.digest.MD5;
import com.tyc.wx.model.request.LoginReq;
import com.tyc.wx.service.IAdminService;
import com.tyc.wx.utils.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-20 14:17:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestApplication {
    @Autowired
    private IAdminService adminService;

    @Test
    public void login() {
        LoginReq loginReq = new LoginReq();
        loginReq.setUserName("test");
        String pwd = null;
        try {
            pwd = RSAEncrypt.encrypt(MD5.create().digestHex("123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginReq.setPassWord(pwd);
        log.info(adminService.login(loginReq));
    }
}
