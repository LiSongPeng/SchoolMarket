package com.solar.service.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.solar.entity.AuctionRecord;
import com.solar.entity.Product;
import com.solar.mapper.AuctionRecordMapper;
import com.solar.mapper.NotificationMapper;
import com.solar.mapper.ProductMapper;
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
    private ProductMapper productMapper;
    private NotificationMapper notificationMapper;

    @Override
    public boolean auction(String userId, String productId, int price) {
        if (auctionRecordMapper.addAuctionRecord(UUIDGenerator.getUUID(), price, userId, productId) > 0) {
            Product product = productMapper.queryProductById(productId);
            String title = "拍卖商品新动态！";
            String content = "您的名为：" + product.getName() + "的拍卖商品有了新竞价！";
            notificationMapper.addNotification(UUIDGenerator.getUUID(), title, content, product.getPublisher().getId());
            return true;
        }
        return false;
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

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Autowired
    public void setNotificationMapper(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }
}
