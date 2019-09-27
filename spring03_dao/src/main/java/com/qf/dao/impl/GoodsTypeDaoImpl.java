package com.qf.dao.impl;

import com.qf.dao.GoodsTypeMapper;
import com.qf.pojo.GoodsType;
import com.qf.utils.Druid_DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsTypeDaoImpl implements GoodsTypeMapper {
    QueryRunner qr = new QueryRunner(Druid_DbUtils.getDataSource());
    @Override
    public List<GoodsType> findTypeByLevel(int level) {
        try {
            return qr.query("select * from tb_goods_type where level = ? ", new BeanListHandler<>(GoodsType.class),level);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询商品类别信息失败", e);
        }

    }
    @Override
    public GoodsType findById(int id) {
        try {
            return qr.query("select * from tb_goods_type where id = ?", new BeanHandler<>(GoodsType.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据id查询商品类别", e);
        }
    }
}
