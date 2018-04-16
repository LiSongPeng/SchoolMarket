package com.solar.controller;

import com.solar.entity.User;
import com.solar.service.interfaces.UserService;
import com.solar.utils.UUIDGenerator;
import com.solar.vo.Response;
import com.solar.vo.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiHuiBo
 * 用户控制器
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("/login.do")
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

    @GetMapping("/forgot.do")
    @ResponseBody
    public Response<String> findPassword(@RequestParam("phoneOrEmail") String phoneOrEmail, @RequestParam("identify") String identify) {
        Response<String> response = new Response<>();
        String password = userService.findPassword(phoneOrEmail, identify);
        if (password == null) {
            response.setFlag(Response.FAIL);
            response.setMessage("找回失败，手机号/邮箱或身份证号错误！");
        } else {
            response.setFlag(Response.SUCCESS);
            response.setMessage("找回成功!");
            response.setData(password);
        }
        return response;
    }

    @GetMapping("/register.do")
    @ResponseBody
    public Response register(@RequestParam("realName") String realName, @RequestParam("gender") int gender,
                             @RequestParam("identify") String identify, @RequestParam("studentId") String studentId
            , @RequestParam("school") String school, @RequestParam("location") String location, @RequestParam("email") String email
            , @RequestParam("phone") String phone, @RequestParam("password") String password) {
        Response response = new Response();
        User user = new User();
        user.setName(realName);
        user.setEmail(email);
        user.setGender(gender);
        user.setId(UUIDGenerator.getUUID());
        user.setHeadImg("defaultUserIcon.png");
        user.setIdentify(identify);
        user.setLocation(location);
        user.setPassword(password);
        user.setStudentID(studentId);
        boolean success = userService.register(user);
        if (success) {
            response.setFlag(Response.SUCCESS);
            response.setMessage("注册成功！");
        } else {
            response.setFlag(Response.FAIL);
            response.setMessage("注册失败!");
        }
        return response;
    }

    @GetMapping("/schoolList.do")
    @ResponseBody
    public Response<List<String>> schoolList() {
        Response<List<String>> response = new Response<>();
        List<String> schoolList = userService.getSchoolList();
        if (schoolList == null) {
            response.setFlag(Response.FAIL);
            response.setMessage("查找失败");
        } else {
            response.setFlag(Response.SUCCESS);
            response.setData(schoolList);
        }
        return response;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
