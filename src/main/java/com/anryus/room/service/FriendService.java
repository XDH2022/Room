package com.anryus.room.service;

import com.anryus.room.mapper.FriendMapper;
import com.anryus.room.mapper.UserMapper;
import com.anryus.room.model.Friend;
import com.anryus.room.model.User;
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


    public boolean addFriend(int requestUserId, int targetUserId) {
        User request = userMapper.getUserInfoByUserId(requestUserId);
        User target = userMapper.getUserInfoByUserId(targetUserId);
        if (request == null && target == null) {
            Friend friend = new Friend();
            friend.setOwner_id(requestUserId);
            friend.setFriend_id(targetUserId);
            int result = friendMapper.insertFriend(friend);
            return result == 1;
        }

        return false;
    }
}
