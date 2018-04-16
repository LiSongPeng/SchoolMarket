package com.solar.service.interfaces;

import com.solar.entity.User;
import com.solar.vo.UserView;

import java.util.List;

/**
 * @author LiHuiBo
 * 用户服务层接口
 */
public interface UserService {
    /**
     * 用户登录业务接口
     *
     * @param phoneOrEmail 可使用手机或者邮箱登录
     * @param password     密码
     * @return 成功返回用户视图实体，失败返回null
     */
    UserView login(String phoneOrEmail, String password);

    /**
     * 找回密码业务接口
     *
     * @param phoneOrEmail 手机或邮箱
     * @param identify     身份证号
     * @return 成功返回密码，失败返回null
     */
    String findPassword(String phoneOrEmail, String identify);

    /**
     * 查询学校列表
     *
     * @return
     */
    List<String> getSchoolList();

    /**
     * 注册业务
     * @param user 用户实体
     * @return 注册是否成功
     */
    boolean register(User user);
}
