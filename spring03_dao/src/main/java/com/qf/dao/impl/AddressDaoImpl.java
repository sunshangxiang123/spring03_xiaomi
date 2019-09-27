package com.qf.dao.impl;

import com.qf.dao.AddressMapper;
import com.qf.pojo.Address;
import com.qf.utils.Druid_DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressMapper {
    QueryRunner qr = new QueryRunner(Druid_DbUtils.getDataSource());
    @Override
    public List<Address> findById(int uid) {

        try {
            return qr.query("select * from tb_address where  uid = ?", new BeanListHandler<>(Address.class ),uid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询地址失败", e);
        }
    }
    @Override
    public void add(Address address) {
        Object[] params = {address.getDetail(),address.getName(),address.getPhone(),address.getUid(),address.getLevel()};
        try {
            qr.update("insert into tb_address(detail,name,phone,uid,level) values(?,?,?,?,?)",params );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateDefault(int id, int uid) {
        try {
            qr.update("update tb_address set level = 0 where uid = ?", uid);
            qr.update("update tb_address set level = 1 where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("更改默认设置失败", e);
        }
    }
    @Override
    public void deleteAddress(int id) {
        try {
            qr.update("delete from tb_address where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("删除地址失败", e);
        }
    }
    @Override
    public void update(Address address) {
        try {
            Object[] params = {address.getName(),address.getPhone(),address.getDetail(),address.getId()};
            qr.update("update tb_address set name=? ,phone = ?,detail = ? where id = ?", params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("修改地址失败", e);
        }
    }
    @Override
    public Address findByAid(int aid) {
        try {
            return qr.query("select * from tb_address where id = ?", new BeanHandler<>(Address.class),aid);
        } catch (SQLException e) {
            e.printStackTrace();
            //
            throw  new RuntimeException("根据地址ID查询失败", e);
        }
    }
}
