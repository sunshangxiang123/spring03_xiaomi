package com.qf.service;

import com.qf.pojo.Cart;

import java.util.List;

public interface CartService {
    Cart findByIdAndGid(int id, int pid);

    void add(Cart cart);
    void update(Cart cart);

    List<Cart> findById(Integer id);

    void deleteByUid(int uid);

    void delete(int uid, int pid);
}
