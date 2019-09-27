package com.qf.service;

import com.qf.pojo.Order;
import com.qf.pojo.OrderDetail;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order, List<OrderDetail> orderDetailList);

    List<Order> findByUid(int id);

    Order findByOid(String oid);
}
