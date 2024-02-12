package com.anryus.room.websocket;

import jakarta.websocket.Session;

import java.util.HashMap;

public class SessionPool {
    private static final HashMap<String,Session> POOL = new HashMap<>();

    public static void addSession(String userId, Session session){
        POOL.put(userId,session);
    }

    public static void removeSession(String userId){
        POOL.remove(userId);
    }

    public static Session getSession(String userId){
        return POOL.get(userId);
    }



}
