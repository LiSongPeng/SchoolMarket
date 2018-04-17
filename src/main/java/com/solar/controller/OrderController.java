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
                          @RequestParam("productId") String productId, @RequestParam("price") int price) {
        Response response = new Response();
        boolean result = orderService.order(number, userId, productId, price);
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

    @RequestMapping("/queryOrderByUserId.do")
    @ResponseBody
    public Response<PageInfo<Order>> queryOrderByUserId(@RequestParam("userId") String userId,
                                                        @RequestParam("pageSize") int pageSize,
                                                        @RequestParam("pageNumber") int pageNumber) {
        Response<PageInfo<Order>> response = new Response<>();
        PageInfo<Order> list = orderService.queryOrders(userId, pageSize, pageNumber);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/queryOrderByUserIdAndStatus.do")
    @ResponseBody
    public Response<PageInfo<Order>> queryOrderByUserIdAndStatus(@RequestParam("userId") String userId,
                                                                 @RequestParam("pageSize") int pageSize,
                                                                 @RequestParam("pageNumber") int pageNumber,
                                                                 @RequestParam("status") int status) {
        Response<PageInfo<Order>> response = new Response<>();
        PageInfo<Order> list = orderService.queryOrders(userId, pageSize, pageNumber, status);
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