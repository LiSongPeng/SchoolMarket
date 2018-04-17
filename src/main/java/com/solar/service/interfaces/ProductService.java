package com.solar.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.solar.entity.Category;
import com.solar.entity.Comment;

import java.util.List;

/**
 * @author LiHuiBo
 */
public interface ProductService {
    /**
     * 获取商品类别
     *
     * @return
     */
    List<Category> getCategorys();

    /**
     * 商品评价
     *
     * @param userId
     * @param productId
     * @param content
     * @param level
     * @return
     */
    boolean comment(String userId, String productId, String content, int level);

    /**
     * 查询商品评价
     *
     * @param productId
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageInfo<Comment> queryCommentsByProductId(String productId, int pageSize, int pageNumber);
}
