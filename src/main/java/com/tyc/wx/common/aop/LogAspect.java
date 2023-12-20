package com.tyc.wx.common.aop;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyc.wx.common.annotation.OperateLog;
import com.tyc.wx.entity.AdminOperateLog;
import com.tyc.wx.model.OperatingModule;
import com.tyc.wx.model.OperatingType;
import com.tyc.wx.model.request.LoginReq;
import com.tyc.wx.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-18 18:15:09
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private ILogService logService;

    @Pointcut("execution(public * com.tyc.wx.controller..*(..))")
    public void logAop() {}

    @After("logAop()")
    public void insertLog(JoinPoint joinpoint){
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();
        boolean hasAnnotation = method.isAnnotationPresent(OperateLog.class);
        if(hasAnnotation){
            OperateLog operateLog = method.getAnnotation(OperateLog.class);
            OperatingModule module = operateLog.operatingModule();
            OperatingType type = operateLog.operatingType();
            String moduleDesc = module.getDescription();
            String typeDesc = type.getDescription();
            String content = getDetail(method, joinpoint);
            AdminOperateLog log = new AdminOperateLog(getParameter(method, joinpoint, "username"), new Timestamp(System.currentTimeMillis()), typeDesc, moduleDesc, content);
            logService.save(log);
        }
    }

    private String getDetail(Method method, JoinPoint point) {
        Object[] args = point.getArgs();
        Parameter[] params = method.getParameters();
        StringBuilder detailBuff = new StringBuilder();
        if(method.getName().equals("logout")){
            return detailBuff.toString();
        }
        if(method.getName().equals("login")){
            LoginReq req = (LoginReq)args[0];
            JSONObject object = new JSONObject();
            object.put("username", req.getUserName());
            args[0] = object;
        }
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                if (args[i] instanceof HttpServletRequest ||
                        args[i] instanceof MultipartFile ||
                        args[i] instanceof HttpServletResponse) {
                    continue;
                }
                if (args[i] instanceof String ||
                        args[i] instanceof Integer ||
                        args[i] instanceof Float ||
                        args[i] instanceof Double ||
                        args[i] instanceof BigDecimal ||
                        args[i] instanceof Date ||
                        args[i] instanceof Timestamp) {
                    detailBuff.append(String.valueOf(args[i])).append(",");
                } else {
                    detailBuff.append(JSONUtil.toJsonStr(args[i])).append(",");
                }
            }
            detailBuff.delete(detailBuff.length() - 1, detailBuff.length());
        }
        return detailBuff.toString();
    }

    private String getParameter(Method method, JoinPoint point, String key) {
        String result = "";
        Object[] args = point.getArgs();
        Parameter[] params = method.getParameters();
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                if (args[i] instanceof HttpServletRequest ||
                        args[i] instanceof MultipartFile ||
                        args[i] instanceof HttpServletResponse) {
                    continue;
                }
                if (args[i] instanceof String ||
                        args[i] instanceof Integer ||
                        args[i] instanceof Float ||
                        args[i] instanceof Double ||
                        args[i] instanceof BigDecimal ||
                        args[i] instanceof Date ||
                        args[i] instanceof Timestamp) {
                    continue;
                } else {
                    JSONObject object = JSONUtil.parseObj(JSONUtil.toJsonStr(args[i]));
                    result = object.get(key) != null ? object.get(key).toString() : "";
                }
            }
        }
        return result;
    }

}
