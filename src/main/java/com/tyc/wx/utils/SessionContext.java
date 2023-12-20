package com.tyc.wx.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 14:58:55
 */
@Slf4j
@Component
public class SessionContext {

    private static SessionContext instance;
    private ConcurrentHashMap<String, UserSession> sessionMap;
    private static final Long EXPIRE_TIME = 30 * 60 * 1000L;

    public SessionContext() {
        sessionMap = new ConcurrentHashMap<String, UserSession>();
    }

    public void addSession(Map<String, String> map) {
        if (map != null) {
            sessionMap.put(map.get("id"), new UserSession(map, System.currentTimeMillis()));
        }
    }

    public void delSession(Map<String, String> map) {
        if (map != null) {
            sessionMap.remove(map.get("id"));
        }
    }

    public Map<String, String> getSession(String sessionID) {
        Long curTs = System.currentTimeMillis();
        for(UserSession us : sessionMap.values()){
            if(curTs - us.getTimeStamp() > EXPIRE_TIME){
                sessionMap.remove(us.getMap().get("id"));
            }
        }
        if (sessionID == null || sessionMap.get(sessionID) == null) {
            return null;
        }
        UserSession userSession = sessionMap.get(sessionID);
        userSession.setTimeStamp(curTs);
        sessionMap.put(sessionID, userSession);
        return userSession.getMap();
    }

}
