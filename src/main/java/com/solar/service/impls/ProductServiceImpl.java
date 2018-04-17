package com.solar.service.impls;

import com.solar.entity.Category;
import com.solar.mapper.CategoryMapper;
import com.solar.mapper.CommentMapper;
import com.solar.service.interfaces.ProductService;
import com.solar.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private CategoryMapper categoryMapper;
    private CommentMapper commentMapper;

    @Override
    public List<Category> getCategorys() {
        return categoryMapper.queryAll();
    }

    @Override
    public boolean comment(String userId, String productId, String content) {
        return commentMapper.addComment(UUIDGenerator.getUUID(), content, productId, userId) > 0;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
}
