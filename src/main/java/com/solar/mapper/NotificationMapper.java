package com.solar.mapper;

import com.solar.entity.Notification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiHuiBo
 */
public interface NotificationMapper {
    List<Notification> getNotificationsByUserId(@Param("userId") String userId);
}
