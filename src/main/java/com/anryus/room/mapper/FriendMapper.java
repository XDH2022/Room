package com.anryus.room.mapper;

import com.anryus.room.model.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("select * from t_friend where owner_id = #{userid}")
    List<Friend> getFriendList(int userId);
}
