package com.qf.dao;

import com.qf.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    //两个方法 一个查询数据总个数  一个分页查询
    //long getTotalCount(@Param("condition") String condition);

    //List<Goods> findPageByWhere(int pageNum, int pageSize, String condition);
    List<Goods> findPageByWhere(@Param("condition") String condition);

    Goods findById(@Param("id") int id);


}
