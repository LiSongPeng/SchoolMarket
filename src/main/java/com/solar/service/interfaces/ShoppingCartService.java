package com.solar.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.solar.entity.ShoppingCart;

/**
 * @author LiHuiBo
 */
public interface ShoppingCartService {
    /**
     * 添加到购物车
     *
     * @param number
     * @param userId
     * @param productId
     * @return
     */
    boolean addShoppingCart(int number, String userId, String productId);

    /**
     * 查询购物车
     *
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<ShoppingCart> queryByUserId(String userId, int pageNumber, int pageSize);

    /**
     * 更新购物车商品数量
     *
     * @param id
     * @param number
     * @return
     */
    boolean updateNumber(String id, int number);

    /**
     * 删除购物车
     *
     * @param id
     * @return
     */
    boolean removeShoppingCart(String id);
}
