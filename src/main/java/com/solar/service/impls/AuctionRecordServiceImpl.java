package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.AuctionRecord;
import com.solar.mapper.AuctionRecordMapper;
import com.solar.service.interfaces.AuctionRecordService;
import com.solar.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Service("auctionRecordService")
public class AuctionRecordServiceImpl implements AuctionRecordService {
    private AuctionRecordMapper auctionRecordMapper;

    @Override
    public boolean auction(String userId, String productId, int price) {
        return auctionRecordMapper.addAuctionRecord(UUIDGenerator.getUUID(), price, userId, productId) > 0;
    }

    @Override
    public PageInfo<AuctionRecord> queryAuctionRecords(String productId, int pageNumber, int pageSize) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNumber, pageSize);
        List<AuctionRecord> list = auctionRecordMapper.queryAuctionRecordByProductId(productId);
        return new PageInfo<>(list);
    }

    @Override
    public boolean deleteAuctionRecords(String productId) {
        return auctionRecordMapper.deleteAuctionRecordsByProductId(productId) > 0;
    }

    @Autowired
    public void setAuctionRecordMapper(AuctionRecordMapper auctionRecordMapper) {
        this.auctionRecordMapper = auctionRecordMapper;
    }
}
