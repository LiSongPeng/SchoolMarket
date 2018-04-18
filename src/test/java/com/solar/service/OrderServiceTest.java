package com.solar.service;

import com.solar.BaseJunitTest;
import com.solar.service.interfaces.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiHuiBo
 */
public class OrderServiceTest extends BaseJunitTest {
    private OrderService orderService;

    @Test
    public void testOrder() {
        int number = 1;
        String userId = "22222222222";
        String productId = "adfadf";
        int price = 23;
        String targetId = "234234324234";
        Assert.assertTrue(orderService.order(number, userId, productId, price, targetId));
    }

    @Test
    public void testConfirmDispatching() {
        String orderId = "072a58c3-0a34-463f-9d65-2fb8717b5d4c";
        Assert.assertTrue(orderService.confirmDispatching(orderId));
    }

    @Test
    public void testConfirmFinished() {
        String orderId = "893bdbc7-e316-482d-b6c5-5b3712da8224";
        Assert.assertTrue(orderService.confirmFinished(orderId));
    }

    @Test
    public void testGetOrdersByUserId() {
        String userId = "22222222222";
        int pageSize = 5;
        int pageNumber = 1;
        Assert.assertTrue(orderService.getOrdersByUserId(userId, pageSize, pageNumber).getList().size() > 0);
    }

    @Test
    public void testGetOrdersByUserIdAndStatus() {
        String userId = "22222222222";
        int pageSize = 5;
        int pageNumber = 1;
        int status = 2;
        Assert.assertTrue(orderService.getOrdersByUserIdAndStatus(userId, pageSize, pageNumber, status).getList().size() > 0);
    }

    @Test
    public void testGetOrdersByTargetId() {
        String targetId = "234234324234";
        int pageSize = 5;
        int pageNumber = 1;
        Assert.assertTrue(orderService.getOrdersByTargetId(targetId, pageSize, pageNumber).getList().size() > 0);
    }

    @Test
    public void testGetOrdersByTargetIdAndStatus() {
        String targetId = "234234324234";
        int pageSize = 5;
        int pageNumber = 1;
        int status = 2;
        Assert.assertTrue(orderService.getOrdersByTargetIdAndStatus(targetId, pageSize, pageNumber, status).getList().size() > 0);
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
