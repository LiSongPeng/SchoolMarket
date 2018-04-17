package com.solar.mapper;

import com.solar.entity.Notification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiHuiBo
 */
public interface NotificationMapper {
    List<Notification> getNotificationsByUserId(@Param("userId") String userId);

    int addNotification(@Param("id") String id, @Param("title") String title,
                        @Param("content") String content, @Param("userId") String userId);

    int deleteNotification(@Param("id") String id);

    int deleteAllNotificationByUserId(@Param("userId") String userId);
}
