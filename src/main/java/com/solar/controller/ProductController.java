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

    @GetMapping("/turnOnComment.do")
    @ResponseBody
    public Response turnOnComment(@RequestParam("productId") String productId) {
        boolean result = productService.turnOnComment(productId);
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

    @PostMapping(value = "/addProduct.do", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addProduct(@RequestParam("name") String name, @RequestParam("price") int price,
                             @RequestParam("disc") String disc,
                             @RequestParam("type") int type,
                             @RequestParam("categoryId") String categoryId,
                             @RequestParam("number") int number,
                             @RequestParam("userId") String userId,
                             @RequestParam("imgs") MultipartFile[] imgs, HttpSession session) {
        if (imgs.length != 4) {
            return "请上传四张图片！";
        }
        String path = session.getServletContext().getRealPath(Constant.UPLOAD_DIRECTORY);
        String[] imgNames = new String[imgs.length];
        for (int i = 0; i < imgNames.length; i++) {
            String imgName = imgs[i].getOriginalFilename();
            String uuid = UUIDGenerator.getUUID();
            imgNames[i] = Constant.UPLOAD_DIRECTORY + "/" + uuid + imgName.substring(imgName.indexOf('.'));
            File file = new File(path + File.separator + uuid + imgName.substring(imgName.indexOf('.')));
            try {
                imgs[i].transferTo(file);
            } catch (IOException e) {
                LOG.error("文件写入错误");
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
        if (imgNames[0] != null) {
            product.setImga(imgNames[0]);
        }
        if (imgNames[1] != null) {
            product.setImgb(imgNames[1]);
        }
        if (imgNames[2] != null) {
            product.setImgc(imgNames[2]);
        }
        if (imgNames[3] != null) {
            product.setImgd(imgNames[3]);
        }
        boolean result = productService.addProduct(product);
        if (result) {
            return "商品发布成功！";
        } else {
            return "商品发布失败！";
        }
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

    @RequestMapping("/getByKeyWordAndCategory.do")
    @ResponseBody
    public Response<PageInfo<Product>> getByKeyWordAndCategory(@RequestParam("keyWord") String keyWord, @RequestParam("pageNumber") int pageNumber,
                                                               @RequestParam("pageSize") int pageSize, @RequestParam("category") String category) {
        Response<PageInfo<Product>> response = new Response<>();
        PageInfo<Product> list = productService.queryProductByKeyWordAndCategory(keyWord, pageNumber, pageSize, category);
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
    public Response updateProduct(@RequestParam("productId") String productId, @RequestParam("number") int number,
                                  @RequestParam("price") int price) {
        Response response = new Response();
        boolean result = productService.updateProduct(productId, number, price);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getByType.do")
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

    @RequestMapping("/getByCategory.do")
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

    @RequestMapping("/getByUserIdAndStatus.do")
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

    @RequestMapping("/getByUserId.do")
    @ResponseBody
    public Response<PageInfo<Product>> getProductByUserId(@RequestParam("userId") String userId,
                                                          @RequestParam("pageNumber") int pageNumber,
                                                          @RequestParam("pageSize") int pageSize) {
        Response<PageInfo<Product>> response = new Response<>();
        PageInfo<Product> list = productService.queryProductByUserId(userId, pageNumber, pageSize);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/getById.do")
    @ResponseBody
    public Response<Product> getProductById(@RequestParam("id") String id) {
        Response<Product> response = new Response<>();
        Product product = productService.getProductById(id);
        if (product != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(product);
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
