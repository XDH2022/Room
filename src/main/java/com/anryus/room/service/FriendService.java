package com.anryus.room.service;

import com.anryus.room.mapper.FriendMapper;
import com.anryus.room.mapper.UserMapper;
import com.anryus.room.model.Friend;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    UserMapper userMapper;
    FriendMapper friendMapper;

    public FriendService(UserMapper userMapper, FriendMapper friendMapper){
        this.userMapper = userMapper;
        this.friendMapper = friendMapper;
    }

    public List<Friend> getFriendList(int userId){
        return friendMapper.getFriendList(userId);
    }

    public boolean addFriend(int ownerId,int friendId){
        //todo 加入队列向被加好友的一方发送审批消息
        return false;
    }
}
