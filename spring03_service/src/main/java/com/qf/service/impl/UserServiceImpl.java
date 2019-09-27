package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.qf.domain.PageBean;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {

        //密码加密
        user.setPassword(Md5Utils.md5(user.getPassword()));
        //System.out.println(user.getPassword());
        userMapper.add(user);
        //发送邮件
        //EmailUtils.sendEmail(user);
    }

    @Override
    public User checkUserName(String username) {
        return userMapper.checkUserName(username);
    }

    @Override
    public User login(String username, String password) {
        System.out.println("suerMapper"+userMapper);
        //加密后再进行比对
        password = Md5Utils.md5(password);
        return userMapper.findByUserNameAndPassword(username, password);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

//    @Override
//    public PageBean<User> findByPage(int pageNum, int pageSize, String condition) {
//        //查询totalcount
//        long totalCount =  userDao.findCount(condition);
//        //查询data
//        List<User> data= userDao.findByPage(pageNum,pageSize,condition);
//
//        PageBean<User> pageBean = new PageBean<>(pageNum, pageSize, totalCount, data);
//        return pageBean;
//
//    }

    @Override
    public void updateFlag(int uid) {
        userMapper.updateFlag(uid);
    }
}
