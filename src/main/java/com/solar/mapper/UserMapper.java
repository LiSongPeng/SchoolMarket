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
    List<User> queryUsers();

    User queryUser(@Param("phoneOrEmail") String phoneOrEmail, @Param("password") String password);

    String queryPassword(@Param("phoneOrEmail") String phoneOrEmail, @Param("identify") String identify);

    void updateLastLoginTime(@Param("id") String id);

    int addUser(@Param("user") User user);

    User queryUserByEmail(@Param("email") String email);

    User queryUserByPhone(@Param("phone") String phone);

    User queryUserById(@Param("id") String id);

    int updateUser(@Param("id") String id, @Param("location") String location,
                   @Param("email") String email, @Param("phone") String phone,
                   @Param("password") String password);
}
