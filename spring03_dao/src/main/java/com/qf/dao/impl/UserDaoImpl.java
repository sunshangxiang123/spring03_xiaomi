package com.qf.dao.impl;

import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.utils.Druid_DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserMapper {
    private QueryRunner qr = new QueryRunner(Druid_DbUtils.getDataSource());
    @Override
    public List<User> findAll() {
        try {
            return qr.query("select * from tb_user where flag!=2 ", new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("查询全部用户失败", e);
        }

    }
    @Override
    public User findById(Integer id) {
        try {
            return qr.query("select * from tb_user where id=?", new BeanHandler<>(User.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("根据id查询用户失败", e);
        }
    }
    @Override
    public User findByUserNameAndPassword(String username, String password) {
        try {
            return qr.query("select * from tb_user where username = ? and password = ?", new BeanHandler<>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("根据用户名密码查询用户失败", e);
        }

    }

    @Override
    public User checkUserName(String username) {
        try {
            return qr.query("select * from tb_user where username = ?", new BeanHandler<>(User.class),username);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("根据用户名查询用户失败", e);
        }
    }

    @Override
    public void add(User user) {
        Object[] param = {user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getFlag(),user.getRole(),user.getCode()};
        try {
            qr.update("insert into tb_user(username,password,email,gender,flag,role,code) values(?,?,?,?,?,?,?)",param );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加用户失败", e);
        }
    }

    @Override
    public void update(User user) {
        Object[] param = {user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getFlag(),user.getId()};
        try {
            qr.update("update tb_user set username=?,password=?,email= ? gender=?,flag=? where id=?", param);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改用户失败", e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            qr.update("delete from tb_user where id = ?",id );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除用户失败", e);
        }
    }

    @Override
    public long findCount(String condition) {
        try {
            return qr.query("select Count(*) from tb_user"+condition, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询用户总人数失败", e);
        }
    }

//    @Override
//    public List<User> findByPage(int pageNum, int pageSize, String condition) {
//        try {
//            return qr.query("select * from tb_user "+condition+" limit ?,?", new BeanListHandler<>(User.class),(pageNum-1)*pageSize,pageSize);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("查询用户总人数失败", e);
//        }
//    }

    @Override
    public void updateFlag(int uid) {
        try {
            qr.update("update tb_user set flag=2 where id=?", uid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("修改用户失败", e);
        }
    }
}
