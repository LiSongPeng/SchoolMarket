package com.solar.controller;

import com.github.pagehelper.PageInfo;
import com.solar.entity.Order;
import com.solar.service.interfaces.OrderService;
import com.solar.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LiHuiBo
 */
@Controller
@Scope("prototype")
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @RequestMapping("/order.do")
    @ResponseBody
    public Response order(@RequestParam("number") int number, @RequestParam("userId") String userId,
                          @RequestParam("productId") String productId, @RequestParam("targetId") String targetId) {
        Response response = new Response();
        boolean result = orderService.order(number, userId, productId, targetId);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/confirmDispatching.do")
    @ResponseBody
    public Response confirmDispatching(@RequestParam("orderId") String orderId) {
        Response response = new Response();
        boolean result = orderService.confirmDispatching(orderId);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/confirmFinished.do")
    @ResponseBody
    public Response confirmFinished(@RequestParam("orderId") String orderId) {
        Response response = new Response();
        boolean result = orderService.confirmFinished(orderId);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/queryByUserId.do")
    @ResponseBody
    public Response<PageInfo<Order>> queryOrderByUserId(@RequestParam("userId") String userId,
                                                        @RequestParam("pageSize") int pageSize,
                                                        @RequestParam("pageNumber") int pageNumber) {
        Response<PageInfo<Order>> response = new Response<>();
        PageInfo<Order> list = orderService.getOrdersByUserId(userId, pageSize, pageNumber);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/queryByUserIdAndStatus.do")
    @ResponseBody
    public Response<PageInfo<Order>> queryOrderByUserIdAndStatus(@RequestParam("userId") String userId,
                                                                 @RequestParam("pageSize") int pageSize,
                                                                 @RequestParam("pageNumber") int pageNumber,
                                                                 @RequestParam("status") int status) {
        Response<PageInfo<Order>> response = new Response<>();
        PageInfo<Order> list = orderService.getOrdersByUserIdAndStatus(userId, pageSize, pageNumber, status);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/queryByTargetId.do")
    @ResponseBody
    public Response<PageInfo<Order>> queryOrderByTargetId(@RequestParam("targetId") String targetId,
                                                          @RequestParam("pageSize") int pageSize,
                                                          @RequestParam("pageNumber") int pageNumber) {
        Response<PageInfo<Order>> response = new Response<>();
        PageInfo<Order> list = orderService.getOrdersByTargetId(targetId, pageSize, pageNumber);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/queryByTargetIdAndStatus.do")
    @ResponseBody
    public Response<PageInfo<Order>> queryOrderByTargetIdAndStatus(@RequestParam("targetId") String targetId,
                                                                   @RequestParam("pageSize") int pageSize,
                                                                   @RequestParam("pageNumber") int pageNumber,
                                                                   @RequestParam("status") int status) {
        Response<PageInfo<Order>> response = new Response<>();
        PageInfo<Order> list = orderService.getOrdersByTargetIdAndStatus(targetId, pageSize, pageNumber, status);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
