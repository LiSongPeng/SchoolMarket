package com.solar.controller;

import com.github.pagehelper.PageInfo;
import com.solar.entity.ShoppingCart;
import com.solar.service.interfaces.ShoppingCartService;
import com.solar.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LiHuiBo
 */
@Controller
@Scope("prototype")
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;


    @RequestMapping("/addShoppingCart.do")
    @ResponseBody
    public Response addShoppingCart(@RequestParam("number") int number, @RequestParam("userId") String userId,
                                    @RequestParam("productId") String productId) {
        Response response = new Response();
        boolean result = shoppingCartService.addShoppingCart(number, userId, productId);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/updateNumber.do")
    @ResponseBody
    public Response updateNumber(@RequestParam("id") String id, @RequestParam("number") int number) {
        Response response = new Response();
        boolean result = shoppingCartService.updateNumber(id, number);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/removeShoppingCart.do")
    @ResponseBody
    public Response removeShoppingCart(@RequestParam("id") String id) {
        Response response = new Response();
        boolean result = shoppingCartService.removeShoppingCart(id);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/queryShoppingCartByUserId.do")
    @ResponseBody
    public Response<PageInfo<ShoppingCart>> queryOrderByUserId(@RequestParam("userId") String userId,
                                                               @RequestParam("pageSize") int pageSize,
                                                               @RequestParam("pageNumber") int pageNumber) {
        Response<PageInfo<ShoppingCart>> response = new Response<>();
        PageInfo<ShoppingCart> list = shoppingCartService.queryByUserId(userId, pageNumber, pageSize);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
}
