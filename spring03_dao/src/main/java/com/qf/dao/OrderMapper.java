package com.qf.dao;

import com.qf.pojo.Order;
import com.qf.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    void add(Order order);

    List<Order> findByUid(@Param("id") int id);

    Order findByOid(@Param("oid") String oid);

    List<OrderDetail> findDetailByOid(@Param("oid") String oid);
}
