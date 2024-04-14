package com.anryus.room.controller;

import com.anryus.room.model.Friend;
import com.anryus.room.model.JsonVO;
import com.anryus.room.service.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/friend")
public class FriendController {

    FriendService friendService;

    public FriendController(FriendService friendService){
        this.friendService = friendService;
    }

    @GetMapping("/friends")
    public String getFriendList(@RequestParam("userId")Integer userId){
        return JsonVO.success(friendService.getFriendList(userId));
    }

    @PostMapping("/add")
    public String addFriends(){
        return null;
    }

}
