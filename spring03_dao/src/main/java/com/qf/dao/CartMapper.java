package com.qf.dao;

import com.qf.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    Cart findByIdAndGid(@Param("id") int id, @Param("pid") int pid);

    void add(Cart cart);

    void update(Cart cart);

    List<Cart> findById(@Param("id") int id);

    void deleteByUid(@Param("uid") int uid);

    void delete(@Param("uid") int uid, @Param("pid") int pid);
}
