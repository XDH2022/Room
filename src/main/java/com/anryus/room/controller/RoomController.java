package com.anryus.room.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.anryus.room.model.JsonVO;
import com.anryus.room.model.Room;
import com.anryus.room.model.User;
import com.anryus.room.service.ChatService;
import com.anryus.room.service.RoomService;
import com.anryus.room.service.RoomUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;
    public final RoomUserService roomUserService;
    public final ChatService chatService;
    public RoomController(RoomService roomService,RoomUserService roomUserService,ChatService chatService){
        this.roomService = roomService;
        this.roomUserService = roomUserService;
        this.chatService = chatService;
    }


    /**
     *
     * @param room 聊天室信息
     * @return roomId
     */
    @PostMapping("/add")
    @SaCheckLogin
    public String addNewRoom(@Param("room")Room room){

        return JsonVO.success(roomService.addNewRoom(room));
    }


    /**
     * 删除聊天室，房间拥有者才能删除聊天室，检查用户是否拥有该聊天室的room.delete权限
     *
     * @param roomId 聊天室id
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @SaCheckLogin
    public String deleteRoom(@Param("roomId")int roomId){
        roomService.deleteRoom(roomId, StpUtil.getLoginIdAsInt());
        return JsonVO.success(true);
    }


    @PostMapping("/update")
    @SaCheckLogin
    public String updateRoom(@Param("room") Room room) {
        Room newRoom = roomService.updateRoom(room);
        return JsonVO.success(newRoom);
    }

    @PostMapping("/join")
    @SaCheckLogin
    public String joinRoom(@Param("roomId") int roomId) {
        roomUserService.joinRoom(roomId,StpUtil.getLoginIdAsInt());
        return JsonVO.success(true);
    }

    @GetMapping("/users")
    @SaCheckLogin
    public String getUserListInRoom(@Param("roomId") int roomId) {
        List<User> userListInRoom = roomUserService.getUsersInRoom(roomId);
        return JsonVO.success(userListInRoom);
    }

    @GetMapping("/history")
    @SaCheckLogin
    public String getChatList(@Param("roomId") int roomId,@Param("lastSendTime")Long lastSendTime) {
        long lastTime;
        if (lastSendTime!=null){
            lastTime = lastSendTime;
        }else {
            lastTime = System.currentTimeMillis();
        }
        return JsonVO.success(chatService.getChatsInRoom(roomId, StpUtil.getLoginIdAsInt(),lastTime));
    }

    @GetMapping("/list")
    @SaCheckLogin
        public JsonVO<List<Room>> getRoomList(){
        return JsonVO.successByObject(roomUserService.getRoomList(StpUtil.getLoginIdAsInt()));
    }
}
