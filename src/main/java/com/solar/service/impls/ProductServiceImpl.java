package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.Category;
import com.solar.entity.Comment;
import com.solar.entity.Product;
import com.solar.mapper.CategoryMapper;
import com.solar.mapper.CommentMapper;
import com.solar.mapper.ProductMapper;
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
    private ProductMapper productMapper;

    @Override
    public List<Category> getCategorys() {
        return categoryMapper.queryAll();
    }

    @Override
    public boolean comment(String userId, String productId, String content) {
        return commentMapper.addComment(UUIDGenerator.getUUID(), content, productId, userId) > 0;
    }

    @Override
    public PageInfo<Comment> queryCommentsByProductId(String productId, int pageSize, int pageNumber) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Comment> list = commentMapper.queryComments(productId);
        return new PageInfo<>(list);
    }

    @Override
    public boolean addProduct(Product product) {
        return productMapper.addProduct(product) > 0;
    }

    @Override
    public int queryNumber(String id) {
        return productMapper.queryNumber(id);
    }

    @Override
    public boolean deleteProduct(String id) {
        return productMapper.deleteProduct(id) > 0;
    }

    @Override
    public boolean updateProduct(String id, int number, int price) {
        return productMapper.updateProduct(id, number, price) > 0;
    }

    @Override
    public PageInfo<Product> queryProductByType(int type, int pageNumber, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Product> list = productMapper.queryProductByType(type);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Product> queryAll(int pageNumber, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Product> list = productMapper.queryAll();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Product> queryProductByCategoryId(String categoryId, int pageNumber, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Product> list = productMapper.queryProductByCategoryId(categoryId);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Product> queryProductByUserIdAndStatus(String userId, int status, int pageNumber, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Product> list = productMapper.queryProductByUserIdAndStatus(userId, status);
        return new PageInfo<>(list);
    }

    @Override
    public boolean soldOut(String id) {
        return productMapper.soldOut(id) > 0;
    }

    @Override
    public List<String> getProductNames(String keyWord) {
        keyWord = "%" + keyWord + "%";
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(1, 20);
        return productMapper.queryProductNameByKeyWord(keyWord);
    }

    @Override
    public PageInfo<Product> queryProductByKeyWord(String keyWord, int pageNumber, int pageSize) {
        keyWord = "%" + keyWord + "%";
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Product> list = productMapper.queryProductByKeyWord(keyWord);
        return new PageInfo<>(list);
    }

    @Override
    public boolean shutdownComment(String productId) {
        return productMapper.shutdownComment(productId) > 0;
    }

    @Override
    public PageInfo<Product> queryProductByUserId(String userId, int pageNumber, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Product> list = productMapper.queryProductByUserId(userId);
        return new PageInfo<>(list);
    }

    @Override
    public boolean turnOnComment(String productId) {
        return productMapper.turnOnComment(productId) > 0;
    }

    @Override
    public Product getProductById(String id) {
        return productMapper.queryProductById(id);
    }

    @Override
    public PageInfo<Product> queryProductByKeyWordAndCategory(String keyWord, int pageNumber, int pageSize, String category) {
        keyWord = "%" + keyWord + "%";
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<Product> list = productMapper.queryProductByKeyWordAndCategory(keyWord, category);
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

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
}
