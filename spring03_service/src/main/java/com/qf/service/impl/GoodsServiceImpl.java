package com.qf.service.impl;
import com.github.pagehelper.PageInfo;
import com.qf.dao.GoodsMapper;
import com.qf.dao.GoodsTypeMapper;
import com.qf.pojo.Goods;
import com.qf.pojo.GoodsType;
import com.qf.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public PageInfo<Goods> findPageByWhere(String condition) {


//        //包装一下condition
//        condition = "typeid="+condition;

        //long totalCount = goodsDao.getTotalCount(condition);

        System.out.println(condition); //typeId = 1
        List<Goods> data = goodsMapper.findPageByWhere("typeid=1");

        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        for (Goods datum : data) {
            System.out.println(datum);
        }
        System.out.println("bbbbbbbbbbbbbbbbb");

        PageInfo<Goods> pageInfo = PageInfo.of(data);
        return pageInfo;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Goods findById(int id) {
        Goods goods = goodsMapper.findById(id);
        //为商品添加商品类型的属性
        //根据商品id查询商品类型
        Integer id1 = goods.getTypeid();
        GoodsType goodsType = goodsTypeMapper.findById(id1);
        goods.setGoodsType(goodsType);
        return goods;
    }
}
