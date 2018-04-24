package com.solar.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.solar.entity.Category;
import com.solar.entity.Comment;
import com.solar.entity.Product;

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

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    boolean addProduct(Product product);

    /**
     * 查询商品数量
     *
     * @param id
     * @return
     */
    int queryNumber(String id);

    /**
     * 删除指定商品
     *
     * @param id
     * @return
     */
    boolean deleteProduct(String id);

    /**
     * 更新商品特定信息
     *
     * @param id
     * @param number
     * @param price
     * @return
     */
    boolean updateProduct(String id, int number, int price);

    /**
     * 根据商品类别查询商品
     *
     * @param type
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<Product> queryProductByType(int type, int pageNumber, int pageSize);

    /**
     * 查询所有商品
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<Product> queryAll(int pageNumber, int pageSize);

    /**
     * 查询特定类别商品
     *
     * @param categoryId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<Product> queryProductByCategoryId(String categoryId, int pageNumber, int pageSize);

    /**
     * 根据商品发布用户和商品状态查询
     *
     * @param userId
     * @param status
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<Product> queryProductByUserIdAndStatus(String userId, int status, int pageNumber, int pageSize);

    /**
     * 下架特定商品
     *
     * @param id
     * @return
     */
    boolean soldOut(String id);

    /**
     * 根据关键字模糊查询商品名称
     *
     * @param keyWord
     * @return
     */
    List<String> getProductNames(String keyWord);

    /**
     * 根据关键字模糊查询商品
     *
     * @param keyWord
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<Product> queryProductByKeyWord(String keyWord, int pageNumber, int pageSize);

    /**
     * 关闭商品评论功能
     *
     * @param productId
     * @return
     */
    boolean shutdownComment(String productId);

    /**
     * 根据发布人查找商品
     *
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<Product> queryProductByUserId(String userId, int pageNumber, int pageSize);

    /**
     * 开启评论功能
     *
     * @param productId
     * @return
     */
    boolean turnOnComment(String productId);

    /**
     * 根据商品id获取商品
     *
     * @param id
     * @return
     */
    Product getProductById(String id);
}
