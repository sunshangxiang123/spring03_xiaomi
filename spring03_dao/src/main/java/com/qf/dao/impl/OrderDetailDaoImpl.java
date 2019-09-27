package com.qf.dao.impl;

import com.qf.dao.OrderDetailMapper;
import com.qf.pojo.OrderDetail;
import com.qf.utils.Druid_DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class OrderDetailDaoImpl implements OrderDetailMapper {
    QueryRunner qr = new QueryRunner();
    @Override
    public void add(OrderDetail orderDetail) {
        Object[] params = {orderDetail.getOid(),orderDetail.getPid(),orderDetail.getNum(),orderDetail.getMoney()};
        try {
            qr.update(Druid_DbUtils.getConnection(), "insert into tb_orderdetail(oid,pid,num,money) values(?,?,?,?) ",params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加订单详情表失败", e);
        }
    }
}
