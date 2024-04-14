package com.anryus.room.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.anryus.room.model.JsonVO;
import com.anryus.room.model.User;
import com.anryus.room.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
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
        StpUtil.login(user.getId());
        return JsonVO.success(StpUtil.getTokenValue());
    }

    @PostMapping("/logout")
    @SaCheckLogin
    public String logout() {
        StpUtil.logout();
        return JsonVO.success(true);
    }

    @PostMapping("/registry")
    public String registry(@Param("username") String username,
                           @Param("password") String password,
                           @Param("role") String role,
                           @Param("email")String email,
                           Session session) {
        userService.register(username,password,email,role,session.getId());
        return JsonVO.success("注册成功");
    }

    @GetMapping("/info")
    @SaCheckLogin
    public String getUserInfo() {
        User userInfoByUserId = userService.getUserInfoByUserId(StpUtil.getLoginIdAsInt());
        return JsonVO.success(userInfoByUserId);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@Param("email")String email, @Param("password") String password, @Param("code")String code, HttpSession session) {
        userService.resetPassword(email, password,code,session.getId());
        return JsonVO.success("重置成功");
    }

    @PostMapping("/update")
    @SaCheckLogin
    public String updateUser(@Param("user")User user){
        userService.updateUserInfo(user);
        return JsonVO.success(true);
    }

    @PostMapping("/validateCode")
    public String validateCode(@Param("email")String email, @Param("hasAccount")boolean hasAccount,HttpSession session){
        String code = userService.sendValidateEmail(email,session.getId(),hasAccount);
        if (code!=null){
            return JsonVO.success(code);
        }else {
            return JsonVO.fail("错误");
        }
    }

}
