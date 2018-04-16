package com.solar.mapper;

import com.solar.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LiHuiBo
 */
@Repository
public interface UserMapper {
    User queryUser(@Param("phoneOrEmail") String phoneOrEmail, @Param("password") String password);
}
