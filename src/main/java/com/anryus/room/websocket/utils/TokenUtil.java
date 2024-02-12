package com.anryus.room.websocket.utils;

import cn.dev33.satoken.stp.StpUtil;

public class TokenUtil {
    public static int getUserIdByToken(String token){
        String idStr = (String) StpUtil.getLoginIdByToken(token);
        if (idStr!=null){
            return Integer.parseInt(idStr);
        }else {
            return -1;
        }
    }
}
