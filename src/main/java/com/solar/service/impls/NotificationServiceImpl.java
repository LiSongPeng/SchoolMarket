package com.solar.service.impls;

import com.solar.entity.Notification;
import com.solar.mapper.NotificationMapper;
import com.solar.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationMapper notificationMapper;

    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationMapper.getNotificationsByUserId(userId);
    }

    @Override
    public boolean deleteNotification(String id) {
        return notificationMapper.deleteNotification(id) > 0;
    }

    @Override
    public boolean deleteAllNotificationByUserId(String userId) {
        return notificationMapper.deleteAllNotificationByUserId(userId) > 0;
    }

    @Autowired
    public void setNotificationMapper(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }
}
