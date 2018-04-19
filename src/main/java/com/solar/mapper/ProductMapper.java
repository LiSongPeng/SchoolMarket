package com.solar.mapper;

import com.solar.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Repository
public interface ProductMapper {
    int addProduct(@Param("product") Product product);

    int queryNumber(@Param("id") String id);

    int deleteProduct(@Param("id") String id);

    Product queryProductById(@Param("id") String id);

    int updateProduct(@Param("id") String id, @Param("number") int number,
                      @Param("price") int price, @Param("category") String category);

    int soldOut(@Param("id") String id);

    List<Product> queryProductByType(@Param("type") int type);

    List<Product> queryAll();

    List<Product> queryProductByCategoryId(@Param("categoryId") String categoryId);

    List<Product> queryProductByUserIdAndStatus(@Param("userId") String userId,
                                                @Param("status") int status);

    List<String> queryProductNameByKeyWord(@Param("keyWord") String keyWord);

    List<Product> queryProductByKeyWord(@Param("keyWord") String keyWord);

    int shutdownComment(@Param("id") String id);

    int decreaseNumber(@Param("number") int number);
}
