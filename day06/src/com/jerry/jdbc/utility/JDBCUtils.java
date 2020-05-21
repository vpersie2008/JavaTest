package com.jerry.jdbc.utility;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    public static Connection getConnection() {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        Connection connection = null;

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return connection;
    }

    public static void closeResource(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static DataSource source = null;

    static {
        Properties props = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

        try {
            props.load(is);
            source = DruidDataSourceFactory.createDataSource(props);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnectionByDruid() {
        Connection connection = null;
        try {
            connection = source.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        return connection;
    }
}
