package com.anryus.room.websocket.handler;

import com.anryus.room.websocket.SessionPool;
import jakarta.annotation.Resource;
import jakarta.websocket.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserHandler {

    @Resource
    RedisTemplate<String, String> redisTemplate;


    /**
     * 用户登录，将session保存
     *
     * @param userId  用户id
     * @param session 用户session
     */
    public void userOnLine(String userId, Session session) {
        SessionPool.addSession(userId, session);
    }

    /**
     * 用户离线，将session过期
     *
     * @param userId 用户id
     */
    public void userOutLine(String userId) {
        SessionPool.removeSession(userId);
    }
}
