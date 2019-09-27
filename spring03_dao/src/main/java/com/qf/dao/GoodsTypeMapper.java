package com.qf.dao;

import com.qf.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsTypeMapper {
    List<GoodsType> findTypeByLevel(@Param("level") int level);

    GoodsType findById(@Param("id") int id);
}
