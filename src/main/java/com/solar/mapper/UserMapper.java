package com.solar.mapper;

import com.solar.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Repository
public interface UserMapper {
    User queryUser(@Param("phoneOrEmail") String phoneOrEmail, @Param("password") String password);

    String queryPassword(@Param("phoneOrEmail") String phoneOrEmail, @Param("identify") String identify);

    void updateLastLoginTime(@Param("id") String id);

    List<String> querySchools();

    int addUser(@Param("user") User user);
}
