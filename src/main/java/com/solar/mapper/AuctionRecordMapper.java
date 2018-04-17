package com.solar.mapper;

import com.solar.entity.AuctionRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Repository
public interface AuctionRecordMapper {
    int addAuctionRecord(@Param("id") String id, @Param("price") int price,
                         @Param("userId") String userId, @Param("productId") String productId);

    List<AuctionRecord> queryAuctionRecordByProductId(@Param("productId") String productId);

    int deleteAuctionRecordsByProductId(@Param("productId") String productId);
}
