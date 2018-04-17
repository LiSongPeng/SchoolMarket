package com.solar.mapper;

import com.solar.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHuiBo
 */
@Repository
public interface CategoryMapper {
    List<Category> queryAll();
}
