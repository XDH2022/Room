package com.anryus.room.service;

import com.anryus.room.exception.NotFoundUserException;
import com.anryus.room.exception.UserAlreadyExistException;
import com.anryus.room.exception.UserVerifyException;
import com.anryus.room.mapper.UserMapper;
import com.anryus.room.model.Role;
import com.anryus.room.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper mapper) {
        this.userMapper = mapper;
    }

    public int registerNewUser(User user) {
        boolean result = checkUserExist(user.getUsername());
        if (result){
            throw new UserAlreadyExistException();
        }
        return userMapper.insertNewUser(user);
    }

    public int updateUserInfo(User user) {
        boolean exist = checkUserExist(user.getUserId());
        if (exist){
            return userMapper.updateUserPassword(user);
        }else {
            throw new NotFoundUserException();
        }
    }

    public User userLogin(String username, String password) {
        var userByUsername = userMapper.getUserByUsername(username);
        if (userByUsername == null){
            throw new NotFoundUserException();
        }

        if (!userByUsername.getPassword().equals(password)){
            throw new UserVerifyException();
        }
        return userByUsername;
    }

    public User getUserInfoByUserId(int userId){
        User userInfoByUserId = userMapper.getUserInfoByUserId(userId);
        if (userInfoByUserId == null){
            throw new NotFoundUserException();
        }
        return userInfoByUserId;
    }

    public List<User> getUsersInfo(List<Integer> userIds){
        return userMapper.batchSelectUserInfo(userIds);
    }

    public boolean checkUserExist(String username){
        return userMapper.getUserByUsername(username) != null;
    }

    public boolean checkUserExist(int userId){
        return userMapper.getUserInfoByUserId(userId) != null;
    }

    public List<Role> getUserRoles(int userId){
        return userMapper.getRoleByUserId(userId);
    }

    public int deleteUser(int userId){
        boolean exist = checkUserExist(userId);
        if (!exist){
            throw new NotFoundUserException();
        }
        return userMapper.deleteUsrByUserId(userId);
    }




}
