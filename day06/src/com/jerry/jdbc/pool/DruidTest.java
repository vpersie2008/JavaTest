package com.jerry.jdbc.pool;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/*
 * 数据库连接池，Druid 测试
 *
 * */
public class DruidTest {

    @Test
    public void getConnection() {
        Properties props = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

        try {
            props.load(is);
            DataSource source = DruidDataSourceFactory.createDataSource(props);
            Connection connection = source.getConnection();
            System.out.println(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
