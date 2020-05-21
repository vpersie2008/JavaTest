package com.jerry.jdbc;

import com.jerry.jdbc.utility.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/*
 * 针对表中列明和SQL 语句中获取的列名不一样时，应该是用getColumnLabel() 方法来替换getColumnName() 方法来获取
 *
 * */

public class QueryTest {
    @Test
    public void testForQuery() {
        String sql1 = "SELECT `name`,`id`,`countryCode` ,`district` FROM City WHERE ID = ?";
        City city = this.QueryMethod(sql1, 4080);
        System.out.println(city);
        System.out.println("****************************************************************");

        //二、使用泛型做一个查询单个对象的通用方法
        String sql2 = "SELECT `name`,`id`,`countryCode` FROM City WHERE ID = ?";
        City city1 = this.QueryMethod(City.class, sql2, 4080);
        System.out.println(city1);

        System.out.println("****************************************************************");

        //一、查询list对象的通用方法
        String sql3 = "SELECT `name`,`id`,`countryCode` FROM City WHERE CountryCode = ? LIMIT 0,10";
        List<City> cities = this.QueryListMethod(City.class, sql3, "USA");
        System.out.println(cities);
    }

    //通用SQL ,Update,insert,Delete
    public City QueryMethod(String sql, Object... args) {

        if (sql.isEmpty()) {
            System.out.println("请输入sql语句!");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            //预编译sql语句，返回PreparedStatement 的实例
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //查询语句
            rs = ps.executeQuery();

            //获取元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            //获取列数
            int columnCount = rsmd.getColumnCount();
            //按行遍历，目前只取第一行
            if (rs.next()) {
                City city = new City();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    //使用getColumnLabel 可以通过实际SQL语句中的名字来映射列名。
                    //注意，列索引是从 1 开始的
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //通过反射，通过列名来获取真实的字段
                    Field field = City.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(city, columnValue);
                }

                return city;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;
    }

    public <T> T QueryMethod(Class<T> clazz, String sql, Object... args) {

        if (sql.isEmpty()) {
            System.out.println("请输入sql语句!");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            //预编译sql语句，返回PreparedStatement 的实例
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //查询语句
            rs = ps.executeQuery();

            //获取元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            //获取列数
            int columnCount = rsmd.getColumnCount();
            //按行遍历，目前只取第一行
            if (rs.next()) {
                T t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    //使用getColumnLabel 可以通过实际SQL语句中的名字来映射列名。
                    //注意，列索引是从 1 开始的
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //通过反射，通过列名来获取真实的字段
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                return (T) t;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;
    }

    public <T> List<T> QueryListMethod(Class<T> clazz, String sql, Object... args) {

        if (sql.isEmpty()) {
            System.out.println("请输入sql语句!");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            //预编译sql语句，返回PreparedStatement 的实例
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //查询语句
            rs = ps.executeQuery();

            //获取元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            //获取列数
            int columnCount = rsmd.getColumnCount();
            List<T> list = new ArrayList<T>();

            while (rs.next()) {
                T t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    //使用getColumnLabel 可以通过实际SQL语句中的名字来映射列名。
                    //注意，列索引是从 1 开始的
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //通过反射，通过列名来获取真实的字段
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);

                }

                list.add(t);
            }

            return (List<T>) list;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;
    }
}
