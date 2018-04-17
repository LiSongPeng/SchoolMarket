package com.solar.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.solar.entity.Order;

/**
 * @author LiHuiBo
 */
public interface OrderService {
    /**
     * 下订单
     *
     * @param number
     * @param userId
     * @param productId
     * @param price
     * @return
     */
    boolean order(int number, String userId, String productId, int price);

    /**
     * 确认订单发货
     *
     * @param orderId
     * @return
     */
    boolean confirmDispatching(String orderId);

    /**
     * 确认订单完成
     *
     * @param orderId
     * @return
     */
    boolean confirmFinished(String orderId);

    /**
     * 分页查询订单
     *
     * @param userId
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageInfo<Order> queryOrders(String userId, int pageSize, int pageNumber);

    /**
     * 分页查询订单
     *
     * @param userId
     * @param pageSize
     * @param pageNumber
     * @param status
     * @return
     */
    PageInfo<Order> queryOrders(String userId, int pageSize, int pageNumber, int status);
}
