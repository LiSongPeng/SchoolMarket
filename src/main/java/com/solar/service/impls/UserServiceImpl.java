package com.solar.service.impls;

import com.solar.entity.User;
import com.solar.mapper.UserMapper;
import com.solar.service.interfaces.UserService;
import com.solar.vo.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Override
    public UserView login(String phoneOrEmail, String password) {
        User user = userMapper.queryUser(phoneOrEmail, password);
        if (user == null) {
            return null;
        }
        userMapper.updateLastLoginTime(user.getId());
        UserView userView = new UserView();
        userView.setEmail(user.getEmail());
        userView.setGender(user.getGender());
        userView.setIdentify(user.getIdentify());
        userView.setLastLoginTime(user.getLastLoginTime());
        userView.setLocation(user.getLocation());
        userView.setName(user.getName());
        userView.setPhone(user.getPhone());
        userView.setRegisterTime(user.getRegisterTime());
        userView.setStudentID(user.getStudentID());
        userView.setStatus(user.getStatus());
        userView.setHeadImg(user.getHeadImg());
        return userView;
    }

    @Override
    public String findPassword(String phoneOrEmail, String identify) {
        return userMapper.queryPassword(phoneOrEmail, identify);
    }

    @Override
    public List<String> getSchoolList() {
        return userMapper.querySchools();
    }

    @Override
    public boolean register(User user) {
        return userMapper.addUser(user) > 0;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
