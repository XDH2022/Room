package com.anryus.room.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.anryus.room.model.JsonVO;
import com.anryus.room.model.User;
import com.anryus.room.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import static com.anryus.room.common.StatusCode.UNKNOWN_ERROR;

@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @PostMapping("/login")
    public String login(@Param("username") String username, @Param("password") String password) {
        User user = userService.userLogin(username, password);
        StpUtil.login(user.getUserId());
        return JsonVO.success(StpUtil.getTokenValue());
    }

    @PostMapping("/logout")
    @SaCheckLogin
    public String logout() {
        StpUtil.logout();
        return JsonVO.success(true);
    }

    @PostMapping("/registry")
    public String registry(@Param("username") String username, @Param("password") String password, @Param("role") String role) {
        User user = new User(username, password, role);
        int i = userService.registerNewUser(user);
        if (i>0){
            return JsonVO.success(true);
        }else {
            return JsonVO.fail(UNKNOWN_ERROR);
        }
    }

    @GetMapping("/info")
    @SaCheckLogin
    public String getUserInfo() {
        User userInfoByUserId = userService.getUserInfoByUserId(StpUtil.getLoginIdAsInt());
        return JsonVO.success(userInfoByUserId);
    }

    @DeleteMapping("/delete")
    @SaCheckPermission("user.delete")
    public String deleteUser(@Param("userId")int userId) {
        userService.deleteUser(userId);
        return JsonVO.success(true);
    }

    @PostMapping("/update")
    @SaCheckLogin
    public String updateUser(@Param("user")User user){
        userService.updateUserInfo(user);
        return JsonVO.success(true);
    }

}
