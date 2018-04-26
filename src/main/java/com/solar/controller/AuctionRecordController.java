package com.solar.controller;

import com.github.pagehelper.PageInfo;
import com.solar.entity.AuctionRecord;
import com.solar.service.interfaces.AuctionRecordService;
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
@RequestMapping("/auction")
public class AuctionRecordController {
    private AuctionRecordService auctionRecordService;

    @RequestMapping("/addAuctionRecord.do")
    @ResponseBody
    public Response addAuctionRecord(@RequestParam("price") int price, @RequestParam("userId") String userId,
                                     @RequestParam("productId") String productId) {
        Response response = new Response();
        boolean result = auctionRecordService.auction(userId, productId, price);
        if (result) {
            response.setFlag(Response.SUCCESS);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @RequestMapping("/queryAuctionRecord.do")
    @ResponseBody
    public Response<PageInfo<AuctionRecord>> queryAuctionRecord(@RequestParam("productId") String productId,
                                                                @RequestParam("pageSize") int pageSize,
                                                                @RequestParam("pageNumber") int pageNumber) {
        Response<PageInfo<AuctionRecord>> response = new Response<>();
        PageInfo<AuctionRecord> list = auctionRecordService.queryAuctionRecords(productId, pageNumber, pageSize);
        if (list != null) {
            response.setFlag(Response.SUCCESS);
            response.setData(list);
        } else {
            response.setFlag(Response.FAIL);
        }
        return response;
    }

    @Autowired
    public void setAuctionRecordService(AuctionRecordService auctionRecordService) {
        this.auctionRecordService = auctionRecordService;
    }
}
