package com.solar.mapper;

import com.solar.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Repository
public interface OrderMapper {
    int addOrder(@Param("id") String id, @Param("number") int number,
                 @Param("userId") String userId, @Param("productId") String productId,
                 @Param("price") int price, @Param("totalPrice") int totalPrice,
                 @Param("targetId") String targetId);

    int dispatchingOrder(@Param("id") String id);

    int finishOrder(@Param("id") String id);

    List<Order> queryOrderByUserId(@Param("userId") String userId);

    List<Order> queryOrderByUserIdAndStatus(@Param("userId") String userId, @Param("status") int status);

    List<Order> queryOrderByTargetId(@Param("targetId") String targetId);

    List<Order> queryOrderByTargetIdAndStatus(@Param("targetId") String targetId, @Param("status") int status);

    String queryTargetId(@Param("id") String id);

    int payOrder(@Param("id") String id);

    int addAuctionOrder(@Param("id") String id, @Param("number") int number,
                        @Param("userId") String userId, @Param("productId") String productId,
                        @Param("price") int price, @Param("totalPrice") int totalPrice,
                        @Param("targetId") String targetId);
}
