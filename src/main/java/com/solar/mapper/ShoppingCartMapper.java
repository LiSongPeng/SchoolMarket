package com.solar.mapper;

import com.solar.entity.ShoppingCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Repository
public interface ShoppingCartMapper {
    int addShoppingCart(@Param("id") String id, @Param("number") int number,
                        @Param("userId") String userId, @Param("productId") String productId);

    List<ShoppingCart> queryByUserId(@Param("userId") String userId);

    int updateNumber(@Param("id") String id, @Param("number") int number);

    int removeShoppingCart(@Param("id") String id);
}
