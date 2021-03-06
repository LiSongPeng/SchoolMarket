package com.solar.controller;

import com.github.pagehelper.PageInfo;
import com.solar.entity.User;
import com.solar.service.interfaces.UserService;
import com.solar.utils.Constant;
import com.solar.utils.UUIDGenerator;
import com.solar.vo.Response;
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
    public Response<User> login(@RequestParam("phoneOrEmail") String phoneOrEmail, @RequestParam("password") String password) {
        Response<User> response = new Response<>();
        User user = userService.login(phoneOrEmail, password);
        if (user == null) {
            response.setFlag(Response.FAIL);
            response.setMessage("登录失败，手机号/邮箱或密码错误！");
        } else if (user.getStatus() == User.DISABLED_STATUS) {
            response.setFlag(Response.FAIL);
            response.setMessage("登录失败，账户被禁用！");
        } else {
            response.setFlag(Response.SUCCESS);
            response.setMessage("登录成功!");
            response.setData(user);
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

    @PostMapping(value = "/register.do", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String register(@RequestParam("realName") String realName, @RequestParam("gender") int gender,
                           @RequestParam("identify") String identify, @RequestParam("location") String location, @RequestParam("email") String email
            , @RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("headImg") MultipartFile headImg, @RequestParam("alipay") MultipartFile alipay, HttpSession session) {
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
        File file = new File(path + File.separator + newHeadImgName);
        try {
            headImg.transferTo(file);
        } catch (IOException e) {
            return "注册失败，请稍后重试！";
        }
        user.setHeadImg(Constant.UPLOAD_DIRECTORY + "/" + newHeadImgName);
        String alipayName = alipay.getOriginalFilename();
        LOG.debug("alipayName:" + alipayName);
        String newAlipayName = UUIDGenerator.getUUID() + alipayName.substring(alipayName.indexOf('.'));
        LOG.debug("newAliPayName:" + newAlipayName);
        file = new File(path + File.separator + newAlipayName);
        try {
            alipay.transferTo(file);
        } catch (IOException e) {
            return "注册失败，请稍后重试！";
        }
        user.setAlipay(Constant.UPLOAD_DIRECTORY + "/" + newAlipayName);
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

    @GetMapping("/getUsers.do")
    @ResponseBody
    public Response<PageInfo<User>> getUsers(@RequestParam("pageSize") int pageSize,
                                             @RequestParam("pageNumber") int pageNumber) {
        Response<PageInfo<User>> response = new Response<>();
        PageInfo<User> list = userService.getUsers(pageNumber, pageSize);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @GetMapping("/getByEmail.do")
    @ResponseBody
    public Response<User> getByEmail(@RequestParam("email") String email) {
        Response<User> response = new Response<>();
        User user = userService.getUserByEmail(email);
        if (user != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(user);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @GetMapping("/getByPhone.do")
    @ResponseBody
    public Response<User> getByPhone(@RequestParam("phone") String phone) {
        Response<User> response = new Response<>();
        User user = userService.getUserByPhone(phone);
        if (user != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(user);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @GetMapping("/getById.do")
    @ResponseBody
    public Response<User> getById(@RequestParam("id") String id) {
        Response<User> response = new Response<>();
        User user = userService.getUserById(id);
        if (user != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(user);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @GetMapping("/update.do")
    @ResponseBody
    public Response updateUser(@RequestParam("id") String id, @RequestParam("location") String location,
                               @RequestParam("email") String email, @RequestParam("phone") String phone,
                               @RequestParam("password") String password) {
        Response response = new Response();
        boolean result = userService.updateUser(id, location, email, phone, password);
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
