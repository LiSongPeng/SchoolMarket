package com.solar.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.solar.entity.User;

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
     * @return 成功返回用户实体，失败返回null
     */
    User login(String phoneOrEmail, String password);

    /**
     * 找回密码业务接口
     *
     * @param phoneOrEmail 手机或邮箱
     * @param identify     身份证号
     * @return 成功返回密码，失败返回null
     */
    String findPassword(String phoneOrEmail, String identify);

    /**
     * 注册业务
     *
     * @param user 用户实体
     * @return 注册是否成功
     */
    boolean register(User user);

    /**
     * 给评论点赞
     *
     * @param commentId
     * @return
     */
    boolean likeComment(String commentId);

    /**
     * 给评论踩赞
     *
     * @param commentId
     * @return
     */
    boolean disklikeComment(String commentId);

    /**
     * 获取用户
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<User> getUsers(int pageNumber, int pageSize);

    /**
     * 根据邮箱获取用户
     *
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 根据手机号获取用户
     *
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 更新用户信息
     *
     * @param id
     * @param location
     * @param email
     * @param phone
     * @param password
     * @return
     */
    boolean updateUser(String id, String location,
                       String email, String phone,
                       String password);
}
