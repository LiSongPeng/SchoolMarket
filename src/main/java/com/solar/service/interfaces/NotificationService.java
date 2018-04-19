package com.solar.service.interfaces;


import com.solar.entity.Notification;

import java.util.List;

/**
 * @author LiHuiBo
 */
public interface NotificationService {
    /**
     * 获取通知
     * @param userId
     * @return
     */
    List<Notification> getNotificationsByUserId(String userId);

    /**
     * 删除通知
     * @param id
     * @return
     */
    boolean deleteNotification(String id);

    /**
     * 删除所有通知
     * @param userId
     * @return
     */
    boolean deleteAllNotificationByUserId(String userId);
}
