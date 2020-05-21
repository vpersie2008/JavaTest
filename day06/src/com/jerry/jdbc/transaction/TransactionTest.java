package com.jerry.jdbc.transaction;

import com.jerry.jdbc.common.DBCommand;
import com.jerry.jdbc.model.BalanceEntity;
import com.jerry.jdbc.utility.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 一次事务，若要回滚事务：
 * 1.设置师傅的额提交状态为false，setAutoCommit(false);
 * 2.不能在某次操作时关闭数据库连接，如update时，此时连接不能关闭，一旦关闭则事务回滚就不行了
 * 3.做完所有操作，在调用commit() 方法去提交整个事务。
 * 4.记得抛异常需要rollback
 *
 * */

public class TransactionTest {

    @Test
    public void testUpdateTx() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getAutoCommit());
            //1.取消数据自动提交
            conn.setAutoCommit(false);

            String sql1 = "UPDATE balance SET `balance` = `balance`-100 WHERE id = ?";
            this.update(conn, sql1, 1);

            //中间出现异常，事务设置为不自动提交，则可以回滚本次事务操作
            //int test = 100 / 0;

            String sql2 = "UPDATE balance SET `balance` = `balance`+100 WHERE id = ?";
            this.update(conn, sql2, 2);

            //比如这里挂起10秒钟，上面的update已经都执行了，但是事务在下方提交，未提交则不会将数据库中的数据改掉
            Thread.sleep(10000);

            //提交了之后，数据才会永久改变，否则
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException connEx) {
                connEx.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public int update(Connection connection, String sql, Object... args) {
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

    @Test
    public void testTransactionSelect() {

        Connection conn = JDBCUtils.getConnection();
        try {

            //获取取数据库的隔离级别,当前为TRANSACTION_REPEATABLE_READ
            //TRANSACTION_REPEATABLE_READ 为可重复读，避免了脏读和可不可重复读问题.
            //TRANSACTION_READ_UNCOMMITTED 读未提交数据，这种隔离级别存在脏读，会读取未提交的数据
            //TRANSACTION_READ_COMMITTED 读已提交的数据，如果修改的数据未提交，则读出来还是老的修改之前的数据
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            System.out.println(conn.getTransactionIsolation());

            //取消自动提交数据
            conn.setAutoCommit(false);
            String sql = "SELECT `user`,`password`,balance FROM balance WHERE `user` = ?";

            DBCommand dbCommand = new DBCommand(conn);
            BalanceEntity balance1 = dbCommand.queryMethod(BalanceEntity.class, sql, "jerry");
            System.out.println(balance1);

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void testTransactionUpdate() {
        Connection conn = JDBCUtils.getConnection();
        DBCommand dbCommand = new DBCommand(conn);

        try {
            String sql = "UPDATE balance SET `balance` = ? WHERE  `user`= ?";
            conn.setAutoCommit(false);

            int updateResult = dbCommand.updateMethod(conn, sql, 6000, "jerry");
            if (updateResult > 0) {
                System.out.println("更新数据成功！");
            }

            Thread.sleep(15000);

            conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
