package com.qf.service;

import com.qf.pojo.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> getTypeByLevel(int level);
    GoodsType findById(int id);
}
