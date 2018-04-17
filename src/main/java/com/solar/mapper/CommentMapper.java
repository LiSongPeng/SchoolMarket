package com.solar.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author LiHuiBo
 */
@Repository
public interface CommentMapper {
    int addComment(@Param("id") String id, @Param("content") String content, @Param("productId") String productId, @Param("userId") String userId);

    int increaseLikeById(@Param("id") String commentId);

    int increaseDislikeById(@Param("id") String commentId);

}
