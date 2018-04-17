package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.Category;
import com.solar.entity.Comment;
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
    public boolean comment(String userId, String productId, String content, int level) {
        return commentMapper.addComment(UUIDGenerator.getUUID(), content, productId, userId, level) > 0;
    }

    @Override
    public PageInfo<Comment> queryCommentsByProductId(String productId, int pageSize, int pageNumber) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Comment> list = commentMapper.queryComments(productId);
        return new PageInfo<>(list);
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
