package com.anryus.room.websocket;

import cn.dev33.satoken.stp.StpUtil;
import com.anryus.room.common.StatusCode;
import com.anryus.room.model.JsonVO;
import com.anryus.room.utils.SpringContextHolder;
import com.anryus.room.websocket.handler.ChatHandler;
import com.anryus.room.websocket.handler.UserHandler;
import com.anryus.room.model.Chat;
import com.anryus.room.utils.CommonExtKt;
import com.anryus.room.websocket.utils.TokenUtil;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ServerEndpoint(value = "/channel/{token}")
public class ChatChannel {
    Logger logger = Logger.getLogger(this.getClass().getName());

    ChatHandler chatHandler = SpringContextHolder.getBean(ChatHandler.class);

    UserHandler userHandler = SpringContextHolder.getBean(UserHandler.class);

    @OnMessage
    public void onMessage(@PathParam("token") String token,String message,Session session) {
        Chat chat = CommonExtKt.fromJson(message, Chat.class);
        int userId = TokenUtil.getUserIdByToken(token);
        if (userId!=-1){
            chat.setSendUserId(userId);
            chatHandler.sendToAllOnlineUserInRoom(chat);
            chatHandler.saveChat(chat);
        }else {
            closeConnect(session);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        logger.log(Level.INFO,"Websocket connect : "+token);
        var id = (String) StpUtil.getLoginIdByToken(token);
        if (id != null) {
            userHandler.userOnLine(id, session);
            session.getAsyncRemote().sendText(CommonExtKt.toJson(JsonVO.success("你好:"+id)));
        } else {
            closeConnect(session);
        }
    }

    @OnClose
    public void onClose(@PathParam("token")String token){
        String id = (String) StpUtil.getLoginIdByToken(token);
        userHandler.userOutLine(id);
    }

    @OnError
    public void onError(Session session, @NotNull Throwable error) {
        error.printStackTrace();
    }

    private void closeConnect(Session session){
        try {
            session.getAsyncRemote()
                    .sendText(JsonVO.fail(StatusCode.NOT_FOUND_USER));
            session.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
