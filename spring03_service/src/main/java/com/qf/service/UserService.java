package com.qf.service;

//import com.qf.domain.PageBean;
import com.qf.pojo.User;

import java.util.List;

public interface UserService {
    //注册
    void register(User user);
    //检查用户名
    User checkUserName(String username);

    //登录
    User login(String username, String password);

    List<User> findAll();

    //PageBean<User> findByPage(int pageNum, int pageSize, String condition);

    void updateFlag(int uid);
}
