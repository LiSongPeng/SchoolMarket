package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.User;
import com.solar.mapper.CommentMapper;
import com.solar.mapper.UserMapper;
import com.solar.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author LiHuiBo
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private CommentMapper commentMapper;

    @Override
    public User login(String phoneOrEmail, String password) {
        User user = userMapper.queryUser(phoneOrEmail, password);
        if (user == null) {
            return null;
        }
        userMapper.updateLastLoginTime(user.getId());
        return user;
    }

    @Override
    public String findPassword(String phoneOrEmail, String identify) {
        return userMapper.queryPassword(phoneOrEmail, identify);
    }

    @Override
    public boolean register(User user) {
        return userMapper.addUser(user) > 0;
    }

    @Override
    public PageInfo<User> getUsers(int pageNumber, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<User> list = userMapper.queryUsers();
        return new PageInfo<>(list);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.queryUserByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.queryUserByPhone(phone);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public boolean updateUser(String id, String location, String email, String phone, String password) {
        return userMapper.updateUser(id, location, email, phone, password) > 0;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
}
