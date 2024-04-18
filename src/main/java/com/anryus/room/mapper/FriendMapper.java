package com.anryus.room.mapper;

import com.anryus.room.model.Friend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("select * from t_friend where owner_id = #{userid}")
    List<Friend> getFriendList(int userId);

    @Insert("insert into t_friend(owner_id,friend_id) values (#{owner_id},#{friend_id})")
    int insertFriend(Friend friend);
}
