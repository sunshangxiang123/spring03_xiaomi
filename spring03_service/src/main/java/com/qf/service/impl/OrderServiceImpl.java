package com.qf.service.impl;

import com.qf.dao.*;
import com.qf.pojo.Address;
import com.qf.pojo.Goods;
import com.qf.pojo.Order;
import com.qf.pojo.OrderDetail;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    @Transactional(isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    public void saveOrder(Order order, List<OrderDetail> orderDetailList) {
            //添加订单 向订单表中添加数据
            orderMapper.add(order);
            //向订单详情表中添加数据
            for (OrderDetail orderDetail : orderDetailList) {
                orderDetailMapper.add(orderDetail);
            }
            //清空购物车
            cartMapper.deleteByUid(order.getUid());
            //System.out.println(order.getUid());
            //提交事务
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Order> findByUid(int id) {
        //因为前端需要地址的信息
        List<Order> orderList =orderMapper.findByUid(id);
        //便利下查询道德订单列表
        for (Order order : orderList) {
            //为每一个order添加地址属性
            //order.getAid();  // 地址的编号
            Address address =  addressMapper.findByAid(order.getAid());
            order.setAddress(address);
        }
        //System.out.println(orderList);
        return orderList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    public Order findByOid(String oid) {
        //根据oid查询订单  返回的是一条数据
        Order order= orderMapper.findByOid(oid);
        //为order添加orderDetail属性 为orderdetail添加goods属性
        //先查询订单详情
        List<OrderDetail> orderDetailList= orderMapper.findDetailByOid(oid);
        order.setOrderDetails(orderDetailList);
        //查询地址属性
        Address address = addressMapper.findByAid(order.getAid());
        //把地址属性放进订单里面
        order.setAddress(address);
        //遍历订单详情集合添加goods属性
        for (OrderDetail od : orderDetailList) {
            Goods good = goodsMapper.findById(od.getPid());
            od.setGoods(good);
        }
        return order;
    }

}
