package com.qf.dao.impl;

import com.qf.dao.CartMapper;
import com.qf.pojo.Cart;
import com.qf.utils.Druid_DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartMapper {
    QueryRunner qr = new QueryRunner(Druid_DbUtils.getDataSource());
    @Override
    public Cart findByIdAndGid(int id ,int pid) {
        try {
            return qr.query(Druid_DbUtils.getConnection(),"select * from tb_cart where id=? and pid = ?", new BeanHandler<>(Cart.class),id,pid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败", e);
        }
    }

    @Override
    public void add(Cart cart) {
        Object[] params = {cart.getId(),cart.getPid(),cart.getNum(),cart.getMoney()};
        try {
            qr.update("insert into tb_cart(id,pid,num,money) values(?,?,?,?)", params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加购物车失败", e);
        }
    }

    @Override
    public void update(Cart cart) {
        Object[] params = {cart.getNum(),cart.getMoney(),cart.getId(),cart.getPid()};
        try {
            qr.update("update tb_cart set num=?,money = ? where id = ? and pid = ? ", params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加购物车失败", e);
        }
    }


    @Override
    public List<Cart> findById(int id) {
        try {
            return qr.query("select * from tb_cart where id = ?", new BeanListHandler<>(Cart.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据id查询购物车", e);
        }


    }

    @Override
    public void deleteByUid(int uid) {
        QueryRunner qr = new QueryRunner();
        try {
            qr.update(Druid_DbUtils.getConnection(), "delete from tb_cart where id = ?",uid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败", e);
        }
    }

    @Override
    public void delete(int uid, int pid) {
        try {
            qr.update("delete from tb_cart where id = ? and pid = ?",uid,pid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败", e);
        }
    }
}
