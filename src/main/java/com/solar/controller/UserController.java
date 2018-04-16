package com.solar.controller;

import com.solar.service.interfaces.UserService;
import com.solar.vo.Response;
import com.solar.vo.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LiHuiBo
 * 用户控制器
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/login.do")
    @ResponseBody
    public Response<UserView> login(@RequestParam("phoneOrEmail") String phoneOrEmail, @RequestParam("password") String password) {
        Response<UserView> response = new Response<>();
        UserView userView = userService.login(phoneOrEmail, password);
        if (userView == null) {
            response.setFlag(Response.FAIL);
            response.setMessage("登录失败，手机号/邮箱或密码错误！");
        } else {
            response.setFlag(Response.SUCCESS);
            response.setMessage("登录成功!");
            response.setData(userView);
        }
        return response;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
