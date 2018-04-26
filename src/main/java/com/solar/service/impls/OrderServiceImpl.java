package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.Order;
import com.solar.entity.Product;
import com.solar.mapper.NotificationMapper;
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
    private NotificationMapper notificationMapper;

    @Override
    public boolean order(int number, String userId, String productId, String targetId) {
        Product product = productMapper.queryProductById(productId);
        if (number > product.getNumber()) {
            return false;
        }
        int totalPrice = number * product.getPrice();
        if (productMapper.decreaseNumber(number) == 0) {
            return false;
        }
        if (orderMapper.addOrder(UUIDGenerator.getUUID(), number, userId, productId, product.getPrice(), totalPrice, targetId) > 0) {
            String title = "您的商品有了一份新订单!";
            String content = "您的名为：" + product.getName() + "的商品有了一份新订单！";
            notificationMapper.addNotification(UUIDGenerator.getUUID(), title, content, targetId);
            return true;
        }
        return false;
    }

    @Override
    public boolean confirmDispatching(String orderId) {
        String targetId = orderMapper.queryTargetId(orderId);
        if (orderMapper.dispatchingOrder(orderId) > 0) {
            String title = "您的订单有了新状态!";
            String content = "您的编号为：" + orderId + "的订单已发货！";
            notificationMapper.addNotification(UUIDGenerator.getUUID(), title, content, targetId);
            return true;
        }
        return false;
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

    @Override
    public boolean confirmPay(String orderId) {
        return orderMapper.payOrder(orderId) > 0;
    }

    @Override
    public boolean auctionDeal(String userId, String productId, int auctionPrice) {
        Product product = productMapper.queryProductById(productId);
        if (product == null) {
            return false;
        }
        String id = UUIDGenerator.getUUID();
        String targetId = product.getPublisher().getId();
        if (orderMapper.addAuctionOrder(id, product.getNumber(),
                userId, productId, product.getPrice(), auctionPrice, targetId) > 0) {
            id = UUIDGenerator.getUUID();
            String title = "商品竞拍新动态！";
            String content = "您成功竞拍了名为：" + product.getName() + "的拍卖品，请进入我的订单页面进行支付！";
            notificationMapper.addNotification(id, title, content, userId);
            productMapper.soldOut(productId);
            return true;
        }
        return false;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Autowired
    public void setNotificationMapper(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }
}
