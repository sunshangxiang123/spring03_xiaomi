package com.qf.dao.impl;

import com.qf.dao.OrderMapper;
import com.qf.pojo.Order;
import com.qf.pojo.OrderDetail;
import com.qf.utils.Druid_DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderMapper {
    QueryRunner qr = new QueryRunner(Druid_DbUtils.getDataSource());
    @Override
    public void add(Order order) {
        Object[] params = {order.getId(),order.getUid(),order.getMoney(),order.getStatus(),order.getTime(),order.getAid()};
        try {
            qr.update(Druid_DbUtils.getConnection(), "insert into tb_order(id,uid,money,status,time,aid)values(?,?,?,?,?,?)",params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加订单表失败", e);
        }
    }
    @Override
    public List<Order> findByUid(int id) {
        try {
            return qr.query("select * from tb_order where uid = ?", new BeanListHandler<>(Order.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            //根据用户ID查询订单失败
            throw new RuntimeException("根据用户ID查询订单失败", e);
        }
    }
    @Override
    public Order findByOid(String oid) {
        try {
            return qr.query("select * from tb_order where id = ?", new BeanHandler<>(Order.class),oid);
        } catch (SQLException e) {
            e.printStackTrace();
            //根据用户ID查询订单失败
            throw new RuntimeException("根据订单查询订单失败", e);
        }
    }
    @Override
    public List<OrderDetail> findDetailByOid(String oid) {
        try {
            return qr.query("select * from tb_orderdetail where oid = ?", new BeanListHandler<>(OrderDetail.class),oid);
        } catch (SQLException e) {
            e.printStackTrace();
            //根据用户ID查询订单失败
            throw new RuntimeException("根据订单查询订单失败", e);
        }
    }
}
