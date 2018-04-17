package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.Order;
import com.solar.mapper.OrderMapper;
import com.solar.service.interfaces.OrderService;
import com.solar.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;

    @Override
    public boolean order(int number, String userId, String productId, int price) {
        int totalPrice = number * price;
        return orderMapper.addOrder(UUIDGenerator.getUUID(), number, userId, productId, price, totalPrice) > 0;
    }

    @Override
    public boolean confirmDispatching(String orderId) {
        return orderMapper.dispatchingOrder(orderId) > 0;
    }

    @Override
    public boolean confirmFinished(String orderId) {
        return orderMapper.finishOrder(orderId) > 0;
    }

    @Override
    public PageInfo<Order> queryOrders(String userId, int pageSize, int pageNumber) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Order> list = orderMapper.queryOrderByUserId(userId);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Order> queryOrders(String userId, int pageSize, int pageNumber, int status) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Order> list = orderMapper.queryOrderByUserIdAndStatus(userId, status);
        return new PageInfo<>(list);
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
}
