package com.qf.dao;

import com.qf.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //查询所有
    List<User> findAll();
    //根据id进行查找
    User findById(@Param("id") Integer id);
    //根据用户名密码查询
    User findByUserNameAndPassword(@Param("username") String username,@Param("password") String password);
    //根据用户名查找
    User checkUserName(@Param("username") String username);
    //添加
    void add(User user);
    //修改
    void update(User user);
    //删除
    void delete(@Param("id") Integer id);

    long findCount(@Param("condition") String condition);

    //List<User> findByPage(int pageNum, int pageSize, String condition);

    void updateFlag(@Param("uid") int uid);
}
