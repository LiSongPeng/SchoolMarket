package com.solar.controller;

import com.github.pagehelper.PageInfo;
import com.solar.entity.Category;
import com.solar.entity.Comment;
import com.solar.service.interfaces.ProductService;
import com.solar.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Controller
@Scope("prototype")
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @GetMapping("/getProductCategory.do")
    @ResponseBody
    public Response<List<Category>> getProductCategory() {
        List<Category> categories = productService.getCategorys();
        Response<List<Category>> response = new Response<>();
        response.setFlag(Response.SUCCESS);
        response.setData(categories);
        return response;
    }

    @GetMapping("/comment.do")
    @ResponseBody
    public Response comment(@RequestParam("userId") String userId,
                            @RequestParam("productId") String productId,
                            @RequestParam("content") String content,
                            @RequestParam("level") int level) {
        boolean result = productService.comment(userId, productId, content, level);
        Response response = new Response();
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @GetMapping("/queryComment.do")
    @ResponseBody
    public Response queryComment(@RequestParam("productId") String productId,
                                 @RequestParam("pageSize") int pageSize,
                                 @RequestParam("pageNumber") int pageNumber) {
        Response response = new Response();
        PageInfo<Comment> list = productService.queryCommentsByProductId(productId, pageSize, pageNumber);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
