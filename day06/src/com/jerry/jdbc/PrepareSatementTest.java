package com.jerry.jdbc;

import com.jerry.jdbc.utility.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PrepareSatementTest {

    @Test
    public void testAdd() {
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        Connection connection = null;

        PreparedStatement ps = null;
        try {
            props.load(is);

            //1.提供三个连接的基本信息
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String driverClass = props.getProperty("driverClass");

            //2.获取Driver实现类的对象,这里注册driver源码帮我们做过了,我们这里只是加载驱动，不用注册驱动
            Class clazz = Class.forName(driverClass);
            //获取连接
            connection = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO City(NAME,CountryCode,District,Population) VALUES(?,?,?,?)";

            ps = connection.prepareStatement(sql);
            ps.setString(1, "Jerry");
            ps.setString(2, "CHN");
            ps.setString(3, "Xi'an");
            ps.setInt(4, 10000);
            if (ps.execute()) {
                System.out.println("添加数据成功");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void testUpdate() {

        Connection conn = JDBCUtils.getConnection();
        String sql = "UPDATE City SET NAME =? WHERE ID = ? ";

        PreparedStatement ps = null;
        try {
            //预编译sql语句，返回PreparedStatement 的实例
            ps = conn.prepareStatement(sql);
            ps.setString(1, "Jeremy2");
            ps.setInt(2, 4080);
            //execute方法，true，则返回结果集，如果是false则返回更受影响行数大于0，或者是没有结果
            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("更新数据成功!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JDBCUtils.closeResource(conn, ps);
    }

    @Test
    public void testUpdate2() {
        String sql = "UPDATE City SET NAME =? WHERE ID = ? ";
        this.ExcuteSql(sql, "jerry1", 4080);
    }

    @Test
    public void testQuery() {
        String sql = "SELECT `name`,`id`,`countryCode`,`district` FROM City WHERE countryCode = ?";

        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<City> cities = new ArrayList<City>();
        try {
            ps = conn.prepareStatement(sql);
            //填充占位符，第一个为占位符索引，第二个为占位符具体值
            ps.setObject(1, "USA");
            rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString(1);
                int id = rs.getInt(2);
                String countryCode = rs.getString(3);
                String district = rs.getString(4);
                cities.add(new City(name, id, countryCode, district));
            }

            //cities.forEach(item -> System.out.println(item.getName()));

            System.out.println(cities);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
    }


    //通用SQL ,Update,insert,Delete
    public void ExcuteSql(String sql, Object... args) {

        if (sql.isEmpty()) {
            System.out.println("请输入sql语句!");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            //预编译sql语句，返回PreparedStatement 的实例
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            if (ps.execute()) {
                System.out.println("更新数据成功!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }


}
