package com.qf.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Druid_DbUtils {
    private static DruidDataSource dataSource;

    private static ThreadLocal<Connection> threadLocal;
    static {
        try {
            threadLocal = new ThreadLocal<>();
            Properties properties = new Properties();
            InputStream is = Druid_DbUtils.class.getClassLoader().getResourceAsStream("database.properties");
            properties.load(is);
            is.close();
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection()throws Exception{
        Connection connection = threadLocal.get();
        if (connection== null){
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    //开启事务
    public static void startTransAction()throws Exception{
        getConnection().setAutoCommit(false);
    }
    //提交事务
    public static void commit()throws Exception{
        getConnection().commit();
    }
    //回滚
    public static void rollback()throws Exception{
        getConnection().rollback();
    }

    //关闭连接解除绑定
    public static  void close()throws Exception{
        getConnection().close();
        threadLocal.remove();
    }


}
