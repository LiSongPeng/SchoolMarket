package com.solar.controller;

import com.solar.entity.Notification;
import com.solar.service.interfaces.NotificationService;
import com.solar.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Controller
@Scope("prototype")
@RequestMapping("/notify")
public class NotificationController {
    private NotificationService notificationService;

    @RequestMapping("/delete.do")
    @ResponseBody
    public Response delete(@RequestParam("id") String id) {
        Response response = new Response();
        boolean result = notificationService.deleteNotification(id);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/deleteAll.do")
    @ResponseBody
    public Response addAuctionRecord(@RequestParam("userId") String userId) {
        Response response = new Response();
        boolean result = notificationService.deleteAllNotificationByUserId(userId);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getAll.do")
    @ResponseBody
    public Response getAll(@RequestParam("userId") String userId) {
        Response<List<Notification>> response = new Response<>();
        List<Notification> result = notificationService.getNotificationsByUserId(userId);
        if (result != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(result);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
