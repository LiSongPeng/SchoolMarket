package com.solar.controller;

import com.solar.entity.User;
import com.solar.service.interfaces.UserService;
import com.solar.utils.Constant;
import com.solar.utils.UUIDGenerator;
import com.solar.vo.Response;
import com.solar.vo.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


/**
 * @author LiHuiBo
 * 用户控制器
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @GetMapping("/login.do")
    @ResponseBody
    public Response<UserView> login(@RequestParam("phoneOrEmail") String phoneOrEmail, @RequestParam("password") String password) {
        Response<UserView> response = new Response<>();
        UserView userView = userService.login(phoneOrEmail, password);
        if (userView == null) {
            response.setFlag(Response.FAIL);
            response.setMessage("登录失败，手机号/邮箱或密码错误！");
        } else if (userView.getStatus() == User.DISABLED_STATUS) {
            response.setFlag(Response.FAIL);
            response.setMessage("登录失败，账户被禁用！");
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

    @PostMapping("/register.do")
    public String register(@RequestParam("realName") String realName, @RequestParam("gender") int gender,
                           @RequestParam("identify") String identify, @RequestParam("location") String location, @RequestParam("email") String email
            , @RequestParam("phone") String phone, @RequestParam("password") String password, MultipartFile headImg, MultipartFile alipay, HttpSession session) {
        User user = new User();
        user.setName(realName);
        user.setEmail(email);
        user.setGender(gender);
        user.setId(UUIDGenerator.getUUID());
        String path = session.getServletContext().getRealPath(Constant.UPLOAD_DIRECTORY);
        LOG.debug("path:" + path);
        String headImgName = headImg.getOriginalFilename();
        LOG.debug("headImg:" + headImgName);
        String newHeadImgName = UUIDGenerator.getUUID() + headImgName.substring(headImgName.indexOf('.'));
        LOG.debug("newheadImgName:" + headImgName);
        File file = new File(newHeadImgName);
        try {
            headImg.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setHeadImg(newHeadImgName);
        String alipayName = alipay.getOriginalFilename();
        LOG.debug("alipayName:" + alipayName);
        String newAlipayName = UUIDGenerator.getUUID() + alipayName.substring(alipayName.indexOf('.'));
        LOG.debug("newAliPayName:" + newAlipayName);
        file = new File(newAlipayName);
        try {
            alipay.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setAlipay(newAlipayName);
        user.setIdentify(identify);
        user.setLocation(location);
        user.setPassword(password);
        user.setPhone(phone);
        boolean success = userService.register(user);
        String result;
        if (success) {
            result = "注册成功，请登录！";
        } else {
            result = "注册失败，手机号/邮箱可能已被注册，请重新注册！";
        }
        return result;
    }

    @RequestMapping("/likeComment.do")
    @ResponseBody
    public Response likeComment(@RequestParam("commentId") String commentId) {
        boolean result = userService.likeComment(commentId);
        Response response = new Response();
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/dislikeComment.do")
    @ResponseBody
    public Response dislikeComment(@RequestParam("commentId") String commentId) {
        boolean result = userService.disklikeComment(commentId);
        Response response = new Response();
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
