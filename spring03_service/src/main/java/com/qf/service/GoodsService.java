package com.qf.service;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.Goods;
//import com.qf.pojo.PageBean;

public interface GoodsService {
//    //返回一个pageBean对象  很久页码 页面显示的条数和条件 typeid
//    PageBean<Goods> findPageByWhere(int pageNum, int pageSize, String condition);

    Goods findById(int id);

    PageInfo<Goods> findPageByWhere(String condition);

}
