package com.tyc.wx.interceptor;


import com.tyc.wx.exception.CommonException;
import com.tyc.wx.model.ResultCode;
import com.tyc.wx.utils.SessionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 15:01:44
 */
@Slf4j
@Component
@WebFilter(urlPatterns = {"/wx/*"}, filterName = "loginInterceptor")
public class LoginInterceptor extends OncePerRequestFilter {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    private SessionContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods","POST, GET");
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if(method.equals("OPTIONS")){
            filterChain.doFilter(request, response);
            return;
        }
        if(uri.contains("login") || uri.contains("game")){
            filterChain.doFilter(request, response);
            return;
        }
        String sid = request.getHeader("sid");
        Map<String, String> session = context.getSession(sid);
        if(session != null){
            String name = session.get("username");
            if(name != null){
                filterChain.doFilter(request, response);
                return;
            }
        }
        resolver.resolveException(request, response, null, new CommonException(ResultCode.USER_NEED_LOGIN));
        return;
    }
}
