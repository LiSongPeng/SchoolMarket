package com.solar.service;

import com.solar.BaseJunitTest;
import com.solar.entity.Category;
import com.solar.entity.Product;
import com.solar.entity.User;
import com.solar.service.interfaces.ProductService;
import com.solar.utils.UUIDGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author LiHuiBo
 */
public class ProductServiceTest extends BaseJunitTest {
    private ProductService productService;

    @Test
    public void testGetCategorys() {
        Assert.assertNotNull(productService.getCategorys());
    }

    @Test
    public void testComment() {
        String userId = "22222222222";
        String productId = "adfadf";
        String content = "Hello world!";
        int level = 2;
        Assert.assertTrue(productService.comment(userId, productId, content, level));
    }

    @Test
    public void testQueryCommentsByProductId() {
        String productId = "adfadf";
        int pageSize = 5;
        int pageNumber = 1;
        Assert.assertTrue(productService.queryCommentsByProductId(productId, pageSize, pageNumber).getList().size() > 0);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setId(UUIDGenerator.getUUID());
        product.setName("mysql");
        product.setPrice(56);
        product.setDisc("this is a good book!");
        product.setType(Product.TYPE_AUCTION);
        Category category = new Category();
        category.setId("adfasdfas");
        product.setCategory(category);
        product.setNumber(2);
        User user = new User();
        user.setId("22222222222");
        product.setPublisher(user);
        product.setImga("img1.png");
        product.setImgb("img2.png");
        product.setImgc("img3.png");
        product.setImgd("img4.png");
        Assert.assertTrue(productService.addProduct(product));
    }

    @Test
    public void testQueryNumber() {
        String productId = "adfadf";
        Assert.assertTrue(productService.queryNumber(productId) == 19);
    }

    @Test
    public void testDeleteProduct() {
        String productId = "9b489458-17c1-4266-a965-ad9379857548";
        Assert.assertTrue(productService.deleteProduct(productId));
    }

    @Test
    public void testUpdateProduct() {
        String productId = "adfadf";
        int number = 120;
        int price = 44;
        Assert.assertTrue(productService.updateProduct(productId, number, price));
    }


    @Test
    public void testQueryProductByType() {
        Assert.assertTrue(productService.queryProductByType(Product.TYPE_NORMAL, 1, 5).getList().size() > 0);
    }

    @Test
    public void testQueryAll() {
        Assert.assertTrue(productService.queryAll(1, 5).getList().size() > 0);
    }

    @Test
    public void testQueryProductByCategoryId() {
        String category = "adfasdfas";
        Assert.assertTrue(productService.queryProductByCategoryId(category, 1, 5).getList().size() > 0);
    }

    @Test
    public void testQueryProductByUserIdAndStatus() {
        String userId = "234234324234";
        Assert.assertTrue(productService.queryProductByUserIdAndStatus(userId, Product.STATUS_ON_SALE, 1, 5).getList().size() > 0);
    }

    @Test
    public void testSoldOut() {
        String productId = "adfadf";
        Assert.assertTrue(productService.soldOut(productId));
    }

    @Test
    public void testGetProductNames() {
        List<String> list = productService.getProductNames("d");
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testQueryProductByKeyWord() {
        String keyWord = "d";
        Assert.assertTrue(productService.queryProductByKeyWord(keyWord, 1, 5).getList().size() > 0);
    }

    @Test
    public void testShutdownComment() {
        String productId = "adfadf";
        Assert.assertTrue(productService.shutdownComment(productId));
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
