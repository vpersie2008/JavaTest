package com.jerry.jdbc.dbutils;

/*
 * Apache DBUtils测试
 * 它是一个对于JDBC 的一个工具类库，封装了对数据库的增删改查操作
 *
 * */

import com.jerry.jdbc.City;
import com.jerry.jdbc.model.BalanceEntity;
import com.jerry.jdbc.utility.JDBCUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtilsTest {

    @Test
    public void testInsert() {
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO balance (`user`,`password`,`balance`) VALUES(?,?,?)";
        Connection conn = JDBCUtils.getConnectionByDruid();
        try {
            int result = runner.update(conn, sql, "Jerrywang", "1234567", 1000);
            System.out.println("添加了" + result + "条记录");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        QueryRunner runner = new QueryRunner();
        String sql = "UPDATE balance SET `balance` = `balance`-100 WHERE id = ?";
        Connection conn = JDBCUtils.getConnectionByDruid();
        try {
            int result = runner.update(conn, sql, 1);
            System.out.println("更改了" + result + "条记录");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testRemove() {
        QueryRunner runner = new QueryRunner();
        String sql = "DELETE FROM balance WHERE id = ?";
        Connection conn = JDBCUtils.getConnectionByDruid();
        try {
            int result = runner.execute(conn, sql, 4);
            System.out.println("删除了" + result + "条记录");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testQuery() {
        QueryRunner runner = new QueryRunner();

        Connection conn = JDBCUtils.getConnectionByDruid();
        try {
            //1. BeanHandler查询单条数据
            String slq1 = "SELECT `user`,`password`,balance FROM balance WHERE USER = ?";
            BeanHandler<BalanceEntity> handler = new BeanHandler<BalanceEntity>(BalanceEntity.class);
            BalanceEntity balance = runner.query(conn, slq1, handler, "jerry");
            System.out.println(balance);

            System.out.println("************************List 查询结果**********************************************" + "\n");

            //2.BeanListHandler 查询多条数据
            String sql2 = "SELECT `id`,`name`,`countryCode`, `district`,`population` FROM city WHERE countryCode = ? LIMIT 0,5";
            BeanListHandler<City> listHandler = new BeanListHandler<>(City.class);
            List<City> cities = runner.query(conn, sql2, listHandler, "AUS");
            System.out.println(cities);

            //3.MapHandler 查询多条数据
            System.out.println("************************Map 查询结果**********************************************" + "\n");
            String sql3 = "SELECT `id`,`name`,`countryCode`, `district`,`population` FROM city WHERE countryCode = ?";
            MapHandler mapHandler = new MapHandler();
            Map<String, Object> mapCities = runner.query(conn, sql3, mapHandler, "AUS");
            System.out.println(mapCities);

            //4.MapListHandler 查询多条数据
            System.out.println("************************MapList 查询结果**********************************************" + "\n");
            String sql4 = "SELECT `id`,`name`,`countryCode`, `district`,`population` FROM city WHERE countryCode = ?";
            MapListHandler mapListandler = new MapListHandler();
            List<Map<String, Object>> maplist = runner.query(conn, sql4, mapListandler, "AUS");
            maplist.forEach(map -> {
                System.out.println(map);
            });

            //5.ScalarHandler 用于查询表中的特殊字段
            System.out.println("************************Scalar 查询结果**********************************************" + "\n");
            String sql5 = "SELECT count(*) FROM city WHERE countryCode = ?";
            ScalarHandler scalarHandler = new ScalarHandler();
            Long count = (Long) runner.query(conn, sql5, scalarHandler, "AUS");
            System.out.println("表中有" + count + "条记录");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /*
     * 自定义handler查询
     * */
    @Test
    public void testQuery2() {
        QueryRunner runner = new QueryRunner();

        Connection conn = JDBCUtils.getConnectionByDruid();
        try {


            System.out.println("************************查询结果**********************************************" + "\n");
            String sql = "SELECT `id`,`name`,`countryCode`, `district`,`population` FROM city WHERE id = ?";

            //自定义实现handler接口
            ResultSetHandler<City> handler = new ResultSetHandler<City>() {
                @Override
                public City handle(ResultSet resultSet) throws SQLException {
                    System.out.println("Running.........");
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String countryCode = resultSet.getString("countryCode");
                        String district = resultSet.getString("district");
                        long population = resultSet.getLong("population");
                        System.out.println("Population is :" + population);
                        return new City(name, id, countryCode, district);
                    }

                    return null;
                }
            };


            City cityEntity = runner.query(conn, sql, handler, 65);
            System.out.println(cityEntity);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //使用DbUtils 封装的关闭连接
            DbUtils.closeQuietly(conn);
        }
    }


}
