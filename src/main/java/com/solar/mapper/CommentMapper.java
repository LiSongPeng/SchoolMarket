package com.solar.mapper;

import com.solar.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Repository
public interface CommentMapper {
    int addComment(@Param("id") String id, @Param("content") String content,
                   @Param("productId") String productId,
                   @Param("userId") String userId);

    List<Comment> queryComments(@Param("productId") String productId);
}
