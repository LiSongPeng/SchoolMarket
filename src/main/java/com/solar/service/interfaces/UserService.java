package com.solar.service.interfaces;

import com.solar.vo.UserView;

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
}
