package com.solar.controller;

import com.github.pagehelper.PageInfo;
import com.solar.entity.Category;
import com.solar.entity.Comment;
import com.solar.entity.Product;
import com.solar.entity.User;
import com.solar.service.interfaces.ProductService;
import com.solar.utils.Constant;
import com.solar.utils.UUIDGenerator;
import com.solar.vo.Response;
import com.solar.vo.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author LiHuiBo
 */
@Controller
@Scope("prototype")
@RequestMapping("/product")
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
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

    @GetMapping("/shutdownComment.do")
    @ResponseBody
    public Response shutdownComment(@RequestParam("productId") String productId) {
        boolean result = productService.shutdownComment(productId);
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
    public Response<PageInfo<Comment>> queryComment(@RequestParam("productId") String productId,
                                                    @RequestParam("pageSize") int pageSize,
                                                    @RequestParam("pageNumber") int pageNumber) {
        Response<PageInfo<Comment>> response = new Response<>();
        PageInfo<Comment> list = productService.queryCommentsByProductId(productId, pageSize, pageNumber);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @PostMapping("/addProduct.do")
    @ResponseBody
    public Response addProduct(@RequestParam("name") String name, @RequestParam("price") int price,
                               @RequestParam("disc") String disc,
                               @RequestParam("type") int type,
                               @RequestParam("CategoryId") String categoryId,
                               @RequestParam("number") int number,
                               @RequestParam("userId") String userId,
                               @RequestParam("imgs") MultipartFile[] imgs, HttpSession session) {
        Response response = new Response();
        String path = session.getServletContext().getRealPath(Constant.UPLOAD_DIRECTORY);
        String[] imgNames = new String[imgs.length];
        for (int i = 0; i < imgNames.length; i++) {
            String imgName = imgs[i].getOriginalFilename();
            imgNames[i] = path + File.pathSeparator + UUIDGenerator.getUUID() + imgName.substring(imgName.indexOf('.'));
            File file = new File(imgNames[i]);
            try {
                imgs[i].transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Product product = new Product();
        product.setId(UUIDGenerator.getUUID());
        product.setName(name);
        product.setPrice(price);
        product.setDisc(disc);
        product.setType(type);
        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);
        product.setNumber(number);
        User user = new User();
        user.setId(userId);
        product.setPublisher(user);
        product.setImga(imgNames[0]);
        product.setImgb(imgNames[1]);
        product.setImgc(imgNames[2]);
        product.setImgd(imgNames[3]);
        boolean result = productService.addProduct(product);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getProductNumber.do")
    @ResponseBody
    public Response getProductNumber(@RequestParam("productId") String productId) {
        Response response = new Response();
        response.setFlag(Response.SUCCESS);
        response.setData(productService.queryNumber(productId));
        return response;
    }

    @RequestMapping("/deleteProduct.do")
    @ResponseBody
    public Response deleteProduct(@RequestParam("productId") String productId) {
        Response response = new Response();
        boolean result = productService.deleteProduct(productId);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/soldOutProduct.do")
    @ResponseBody
    public Response soldOutProduct(@RequestParam("productId") String productId) {
        Response response = new Response();
        boolean result = productService.soldOut(productId);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getNamesByKeyWord.do")
    @ResponseBody
    public Response<List<String>> getNamesByKeyWord(@RequestParam("keyWord") String keyWord) {
        Response<List<String>> response = new Response<>();
        List<String> result = productService.getProductNames(keyWord);
        if (result != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(result);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getByKeyWord.do")
    @ResponseBody
    public Response<PageInfo<Product>> getByKeyWord(@RequestParam("keyWord") String keyWord,
                                                    @RequestParam("pageNumber") int pageNumber,
                                                    @RequestParam("pageSize") int pageSize) {
        Response<PageInfo<Product>> response = new Response<>();
        PageInfo<Product> list = productService.queryProductByKeyWord(keyWord, pageNumber, pageSize);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/updateProduct.do")
    @ResponseBody
    public Response updateProduct(@RequestParam("productId") String product_Id, @RequestParam("number") int number,
                                  @RequestParam("price") int price, @RequestParam("category") String category) {
        Response response = new Response();
        boolean result = productService.updateProduct(product_Id, number, price, category);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getProductByType.do")
    @ResponseBody
    public Response<PageInfo<Product>> getProductByType(@RequestParam("type") int type,
                                                        @RequestParam("pageNumber") int pageNumber,
                                                        @RequestParam("pageSize") int pageSize) {
        Response<PageInfo<Product>> response = new Response<>();
        PageInfo<Product> list = productService.queryProductByType(type, pageNumber, pageSize);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getAllProduct.do")
    @ResponseBody
    public Response<PageInfo<Product>> getAllProduct(@RequestParam("pageNumber") int pageNumber,
                                                     @RequestParam("pageSize") int pageSize) {
        Response<PageInfo<Product>> response = new Response<>();
        PageInfo<Product> list = productService.queryAll(pageNumber, pageSize);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getProductByCategory.do")
    @ResponseBody
    public Response<PageInfo<Product>> getProductByCategory(@RequestParam("category") String category,
                                                            @RequestParam("pageNumber") int pageNumber,
                                                            @RequestParam("pageSize") int pageSize) {
        Response<PageInfo<Product>> response = new Response<>();
        PageInfo<Product> list = productService.queryProductByCategoryId(category, pageNumber, pageSize);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getProductByUserIdAndStatus.do")
    @ResponseBody
    public Response<PageInfo<Product>> getProductByUserIdAndStatus(@RequestParam("userId") String userId,
                                                                   @RequestParam("status") int status,
                                                                   @RequestParam("pageNumber") int pageNumber,
                                                                   @RequestParam("pageSize") int pageSize) {
        Response<PageInfo<Product>> response = new Response<>();
        PageInfo<Product> list = productService.queryProductByUserIdAndStatus(userId, status, pageNumber, pageSize);
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
