package com.qf.service.impl;


import com.qf.dao.GoodsTypeMapper;
import com.qf.pojo.GoodsType;
import com.qf.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public List<GoodsType> getTypeByLevel(int level) {

        return goodsTypeMapper.findTypeByLevel(level);
    }

    @Override
    public GoodsType findById(int id) {
        return goodsTypeMapper.findById(id);
    }
}
