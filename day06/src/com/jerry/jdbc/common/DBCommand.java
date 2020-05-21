package com.jerry.jdbc.common;

import com.jerry.jdbc.utility.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;




public class DBCommand {

    private Connection conn = null;

    public DBCommand(Connection connection) {
        this.conn = connection;
    }

    public Connection getConnection() {

        if (this.conn == null) {
            return JDBCUtils.getConnection();
        }

        return null;
    }

    public <T> T queryMethod(Class<T> clazz, String sql, Object... args) {
        if (sql.isEmpty()) {
            System.out.println("请输入sql语句!");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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

    public <T> List<T> queryListMethod(Class<T> clazz, String sql, Object... args) {

        if (sql.isEmpty()) {
            System.out.println("请输入sql语句!");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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

    public int updateMethod(Connection connection, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            return ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //在这里不要关掉连接，因为关掉连接，事务就无法回滚
            JDBCUtils.closeResource(null, ps);
        }

        return 0;
    }
}
