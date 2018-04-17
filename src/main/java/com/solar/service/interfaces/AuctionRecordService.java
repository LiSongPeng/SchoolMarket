package com.solar.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.solar.entity.AuctionRecord;

/**
 * @author LiHuiBo
 */
public interface AuctionRecordService {
    /**
     * 发起竞拍
     *
     * @param userId
     * @param productId
     * @param price
     * @return
     */
    boolean auction(String userId, String productId, int price);

    /**
     * 查询竞拍记录
     *
     * @param productId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<AuctionRecord> queryAuctionRecords(String productId, int pageNumber, int pageSize);

    /**
     * 删除竞拍记录
     *
     * @param productId
     * @return
     */
    boolean deleteAuctionRecords(String productId);
}
