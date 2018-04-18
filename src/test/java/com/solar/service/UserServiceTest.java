package com.solar.service;

import com.solar.BaseJunitTest;
import com.solar.entity.User;
import com.solar.service.interfaces.UserService;
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
    }

    public void testFindPassword(String phoneOrEmail, String identify) {

    }

    public void testRegister(User user) {

    }

    public void testLikeComment(String commentId) {

    }

    public void testDisklikeComment(String commentId) {

    }

    public void testGetUsers(int pageNumber, int pageSize) {

    }

    public void testGetUserByEmail(String email) {

    }

    public void testGetUserByPhone(String phone) {

    }

    public void testGetUserById(String id) {

    }

    public void testUpdateUser(String id, String location,
                               String email, String phone,
                               String password, String headImg) {

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
