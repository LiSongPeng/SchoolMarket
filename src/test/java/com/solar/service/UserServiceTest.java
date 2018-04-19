package com.solar.service;

import com.github.pagehelper.PageInfo;
import com.solar.BaseJunitTest;
import com.solar.entity.User;
import com.solar.service.interfaces.UserService;
import com.solar.utils.UUIDGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiHuiBo
 */
public class UserServiceTest extends BaseJunitTest {
    private UserService userService;

    @Test
    public void testLogin() {
        String phoneOrEmail = "15510841744";
        String password = "123456789";
        User user = userService.login(phoneOrEmail, password);
        Assert.assertNotNull(user);
        phoneOrEmail = "3423432423";
        user = userService.login(phoneOrEmail, password);
        Assert.assertNull(user);
    }

    @Test
    public void testFindPassword() {
        String phoneOrEmail = "15510841744";
        String identify = "124214214124214214";
        String password = userService.findPassword(phoneOrEmail, identify);
        Assert.assertEquals("123456789", password);
        phoneOrEmail = "234234324";
        password = userService.findPassword(phoneOrEmail, identify);
        Assert.assertNull(password);
    }

    @Test
    public void testRegister() {
        User user = new User();
        user.setName("sssss");
        user.setEmail("23423423422@qq.com");
        user.setGender(1);
        user.setId(UUIDGenerator.getUUID());
        user.setHeadImg("sdfsdfsdf.png");
        user.setAlipay("sdfsdfsdsss.png");
        user.setIdentify("324444444234234");
        user.setLocation("sdfsdfdsfdsfsdf");
        user.setPassword("12322423423");
        user.setPhone("234543543543");
        Assert.assertTrue(userService.register(user));
    }

    @Test
    public void testLikeComment() {
        Assert.assertTrue(userService.likeComment("sdfdsfdssfs"));
    }

    @Test
    public void testDisklikeComment() {
        Assert.assertTrue(userService.disklikeComment("sdfdsfdssfs"));
    }

    @Test
    public void testGetUsers() {
        int pageNumber = 1;
        int pageSize = 5;
        PageInfo<User> list = userService.getUsers(pageNumber, pageSize);
        Assert.assertTrue(list.getList().size() > 0);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "15510841744";
        User user = userService.getUserByEmail(email);
        Assert.assertNull(user);
        email = "1424242424@qq.com";
        user = userService.getUserByEmail(email);
        Assert.assertNotNull(user);
    }

    @Test
    public void testGetUserByPhone() {
        String phone = "15510841744";
        User user = userService.getUserByPhone(phone);
        Assert.assertNotNull(user);
        phone = "1424242424@qq.com";
        user = userService.getUserByPhone(phone);
        Assert.assertNull(user);
    }

    @Test
    public void testGetUserById() {
        String id = "234234324234";
        User user = userService.getUserById(id);
        Assert.assertNotNull(user);
        id = "1424242424@qq.com";
        user = userService.getUserById(id);
        Assert.assertNull(user);
    }

    @Test
    public void testUpdateUser() {
        String id = "22222222222";
        String location = "sssssssssss";
        String email = "23432@ss.com";
        String phone = "43543543534";
        String password = "4354sdfsd";
        boolean result = userService.updateUser(id, location, email, phone, password);
        Assert.assertTrue(result);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
