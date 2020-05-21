package com.jerry.jdbc;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
* 一、解决时区问题（The server time zone value '�й���׼ʱ��' is unrecognized）
*   在MySql 客户端输入命令：
*   SHOW VARIABLES LIKE '%time_zone%'; //查看当前时区
    SET GLOBAL time_zone = '+8:00'; //设置当前机器时区为东八区即可
*
* */

public class ConnectionTest {

    //方式一、连接通过第三方
    @Test
    public void testConnect1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/world";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "newegg@123");
        Connection connection = driver.connect(url, info);

        System.out.println(connection);
    }

    //方式二、通过反射的方式,在如下的方式中不出现第三方API 使得程序有更好的移植性
    @Test
    public void testConnect2() throws Exception {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/world";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "newegg@123");
        Connection connection = driver.connect(url, info);

        System.out.println(connection);
    }

    //方式三、使用DriverManager 替换Driver
    @Test
    public void testConnect3() throws Exception {
        //1.获取Driver实现类的对象
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //2.提供三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "newegg@123";
        DriverManager.registerDriver(driver);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式四、省略DriverManager
    @Test
    public void testConnect4() throws Exception {
        //1.提供三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "newegg@123";

        //2.获取Driver实现类的对象,这里注册driver源码帮我们做过了,我们这里只是加载驱动，不用注册驱动
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式五、读取配置文件方式
    @Test
    public void testConnect5() throws Exception {
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(is);

        //1.提供三个连接的基本信息
        String url = props.getProperty("url");
        String user =props.getProperty("user");
        String password = props.getProperty("password");
        String driverClass = props.getProperty("driverClass");

        //2.获取Driver实现类的对象,这里注册driver源码帮我们做过了,我们这里只是加载驱动，不用注册驱动
        Class clazz = Class.forName(driverClass);
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

}
