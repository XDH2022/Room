package com.anryus.room.service;

import cn.dev33.satoken.secure.BCrypt;
import com.anryus.room.exception.NotFoundUserException;
import com.anryus.room.exception.UserAlreadyExistException;
import com.anryus.room.exception.UserVerifyException;
import com.anryus.room.mapper.UserMapper;
import com.anryus.room.model.Role;
import com.anryus.room.model.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final StringRedisTemplate template;


    public UserService(UserMapper mapper, StringRedisTemplate template) {
        this.userMapper = mapper;
        this.template = template;
    }

    public int registerNewUser(User user) {
        boolean result = checkUserExist(user.getUsername());
        if (result) {
            throw new UserAlreadyExistException();
        }
        return userMapper.insertNewUser(user);
    }

    /**
     * @param username 注册用户名
     * @param password 密码
     * @param email    注册邮箱地址
     * @param id       session id
     */
    public void register(String username, String password, String email, String role, String id) {

        if (userMapper.getUserByUsername(username) != null) {
            throw new UserAlreadyExistException();
        }

        password = BCrypt.hashpw(password);
        User user = new User(username, password, role, email);
        if (userMapper.insertNewUser(user) <= 0) {
            throw new RuntimeException();
        }
    }

    /**
     *
     * @param email 重置的邮箱
     * @param newPassword 新密码
     * @param code 验证码
     * @param id session id
     */

    public void resetPassword(String email, String newPassword, String code, String id) {
        String key = email+":"+id+":true";
        User user = userMapper.getUserByEmail(email);
        if (user==null){
            throw new NotFoundUserException();
        }

        if (Boolean.TRUE.equals(template.hasKey(key))){
            String s = template.opsForValue().get(key);
            if (s==null || !s.equals(code)){
                throw new UserVerifyException();
            }else{
                String password = BCrypt.hashpw(newPassword);
                user.setPassword(password);
                if (userMapper.updateUserPassword(user) <= 0){
                    throw new NotFoundUserException();
                }
            }

        } else {
            //未请求过验证码
            throw new UserVerifyException();
        }
    }

    public int updateUserInfo(User user) {
        boolean exist = checkUserExist(user.getId());
        if (exist) {
            return userMapper.updateUserPassword(user);
        } else {
            throw new NotFoundUserException();
        }
    }

    public User userLogin(String username, String password) {
        var userByUsername = userMapper.getUserByUsername(username);
        if (userByUsername == null) {
            throw new NotFoundUserException();
        }

        if (BCrypt.checkpw(password, userByUsername.getPassword())) {
            return userByUsername;
        } else {
            throw new UserVerifyException();
        }
    }

    public User getUserInfoByUserId(int userId) {
        User userInfoByUserId = userMapper.getUserInfoByUserId(userId);
        if (userInfoByUserId == null) {
            throw new NotFoundUserException();
        }
        return userInfoByUserId;
    }

    public List<User> getUsersInfo(List<Integer> userIds) {
        return userMapper.batchSelectUserInfo(userIds);
    }

    public boolean checkUserExist(String username) {
        return userMapper.getUserByUsername(username) != null;
    }

    public boolean checkUserExist(int userId) {
        return userMapper.getUserInfoByUserId(userId) != null;
    }

    public List<Role> getUserRoles(int userId) {
        return userMapper.getRoleByUserId(userId);
    }


    /**
     * 将验证码和邮箱放入Redis内，过期时间五分钟
     * 剩余时间低于4分钟，则可重新发送
     * 发送验证码
     * 发送失败，将Redis内的验证码和邮箱删除
     * 用户在注册时，从Redis内取出键值对，检查是否一致
     *
     * @param email      邮箱地址
     * @param id         session id
     * @param hasAccount 是否需要账户是否存在，当注册新用户时应为false，重置密码是则为true
     */
    public String sendValidateEmail(String email, String id, boolean hasAccount) {
        String key = email + ":" + id + ":" + hasAccount;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.MINUTES)).orElse(0L);
            if (expire >= 4) {
                return null;
            }
        }

        User account = userMapper.getUserByEmail(email);
        if (hasAccount && account == null) throw new NotFoundUserException();
        if (!hasAccount && account != null) throw new UserAlreadyExistException();

        Random random = new Random();
        int code = random.nextInt(899999) + 100000;

        //将邮箱和验证码写入redis内，有效期为5分钟
        template.opsForValue().set(key, String.valueOf(code), 5, TimeUnit.MINUTES);

        return String.valueOf(code);
    }


}
