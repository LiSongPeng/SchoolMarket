package com.solar.service;

import com.solar.BaseJunitTest;
import com.solar.service.interfaces.AuctionRecordService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiHuiBo
 */
public class AuctionRecordServiceTest extends BaseJunitTest {
    private AuctionRecordService auctionRecordService;

    @Test
    public void testAuction() {
        String userId = "22222222222";
        String productId = "adfadf";
        int price = 100;
        Assert.assertTrue(auctionRecordService.auction(userId, productId, price));
    }

    @Test
    public void testQueryAuctionRecords() {
        String productId = "adfadf";
        int pageNumber = 1;
        int pageSize = 5;
        Assert.assertTrue(auctionRecordService.queryAuctionRecords(productId, pageNumber, pageSize).getList().size() > 0);
    }

    @Test
    public void testDeleteAuctionRecords() {
        String productId = "adfadf";
        Assert.assertTrue(auctionRecordService.deleteAuctionRecords(productId));
    }

    @Autowired
    public void setAuctionRecordService(AuctionRecordService auctionRecordService) {
        this.auctionRecordService = auctionRecordService;
    }
}
