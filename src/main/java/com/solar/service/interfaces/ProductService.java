package com.solar.service.interfaces;

import com.solar.entity.Category;

import java.util.List;

/**
 * @author LiHuiBo
 */
public interface ProductService {
    /**
     * 获取商品类别
     * @return
     */
    List<Category> getCategorys();

    /**
     * 商品评价
     * @param userId
     * @param productId
     * @param content
     * @return
     */
    boolean comment(String userId, String productId, String content);
}
