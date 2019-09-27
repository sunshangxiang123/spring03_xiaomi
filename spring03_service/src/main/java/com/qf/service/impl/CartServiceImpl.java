package com.qf.service.impl;

import com.qf.dao.CartMapper;
import com.qf.dao.GoodsMapper;
import com.qf.pojo.Cart;
import com.qf.pojo.Goods;
import com.qf.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public Cart findByIdAndGid(int id,int pid) {
        return cartMapper.findByIdAndGid(id,pid);
    }

    @Override
    public void add(Cart cart) {
        cartMapper.add(cart);
    }

    @Override
    public void update(Cart cart) {
        cartMapper.update(cart);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    public List<Cart> findById(Integer id) {
        List<Cart> carts = cartMapper.findById(id);
        //根据商品id 查询商品的属性
        for (Cart cart : carts) {
            Goods goods = goodsMapper.findById(cart.getPid());
            cart.setGoods(goods);
        }
        return carts;





    }

    @Override
    public void deleteByUid(int uid) {
        cartMapper.deleteByUid(uid);
    }

    @Override
    public void delete(int uid, int pid) {
        cartMapper.delete(uid,pid);
    }
}
