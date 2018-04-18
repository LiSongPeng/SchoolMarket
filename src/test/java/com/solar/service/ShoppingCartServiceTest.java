package com.solar.service;

import com.solar.BaseJunitTest;
import com.solar.service.interfaces.ShoppingCartService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiHuiBo
 */
public class ShoppingCartServiceTest extends BaseJunitTest {
    private ShoppingCartService shoppingCartService;

    @Test
    public void testAddShoppingCart() {
        int number = 1;
        String userId = "22222222222";
        String productId = "adfadf";
        Assert.assertTrue(shoppingCartService.addShoppingCart(number, userId, productId));
    }

    @Test
    public void testQueryByUserId() {
        String userId = "22222222222";
        int pageNumber = 1;
        int pageSize = 5;
        Assert.assertTrue(shoppingCartService.queryByUserId(userId, pageNumber, pageSize).getList().size() > 0);
    }

    @Test
    public void testUpdateNumber() {
        String id = "eff8ed0f-bd26-4809-b5c3-b55089254b8e";
        int number = 2;
        Assert.assertTrue(shoppingCartService.updateNumber(id, number));
    }

    @Test
    public void testRemoveShoppingCart() {
        String id = "eff8ed0f-bd26-4809-b5c3-b55089254b8e";
        Assert.assertTrue(shoppingCartService.removeShoppingCart(id));
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
}
