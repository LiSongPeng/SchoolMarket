package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.ShoppingCart;
import com.solar.mapper.ShoppingCartMapper;
import com.solar.service.interfaces.ShoppingCartService;
import com.solar.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public boolean addShoppingCart(int number, String userId, String productId) {
        return shoppingCartMapper.addShoppingCart(UUIDGenerator.getUUID(), number, userId, productId) > 0;
    }

    @Override
    public PageInfo<ShoppingCart> queryByUserId(String userId, int pageNumber, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<ShoppingCart> list = shoppingCartMapper.queryByUserId(userId);
        return new PageInfo<>(list);
    }

    @Override
    public boolean updateNumber(String id, int number) {
        return shoppingCartMapper.updateNumber(id, number) > 0;
    }

    @Override
    public boolean removeShoppingCart(String id) {
        return shoppingCartMapper.removeShoppingCart(id) > 0;
    }

    @Autowired
    public void setShoppingCartMapper(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }
}
