package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.Order;
import com.solar.mapper.OrderMapper;
import com.solar.mapper.ProductMapper;
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
    private ProductMapper productMapper;

    @Override
    public boolean order(int number, String userId, String productId, int price, String targetId) {
        int productNumber = productMapper.queryNumber(productId);
        if (number > productNumber) {
            return false;
        }
        int totalPrice = number * price;
        if (productMapper.decreaseNumber(number) == 0) {
            return false;
        }
        return orderMapper.addOrder(UUIDGenerator.getUUID(), number, userId, productId, price, totalPrice, targetId) > 0;
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
    public PageInfo<Order> getOrdersByUserId(String userId, int pageSize, int pageNumber) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Order> list = orderMapper.queryOrderByUserId(userId);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Order> getOrdersByUserIdAndStatus(String userId, int pageSize, int pageNumber, int status) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Order> list = orderMapper.queryOrderByUserIdAndStatus(userId, status);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Order> getOrdersByTargetId(String targetId, int pageSize, int pageNumber) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Order> list = orderMapper.queryOrderByTargetId(targetId);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Order> getOrdersByTargetIdAndStatus(String targetId, int pageSize, int pageNumber, int status) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Order> list = orderMapper.queryOrderByTargetIdAndStatus(targetId, status);
        return new PageInfo<>(list);
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
}
