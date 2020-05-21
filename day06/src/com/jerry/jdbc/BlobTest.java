package com.jerry.jdbc;

import com.jerry.jdbc.utility.JDBCUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BlobTest {

    /*
     * Blob 数据类型用于存储二进制文件，如图片、视频等，类型对应的存储大小如下
     * TinyBlob 最大255B
     * Blob 最大65K
     * MediumBlob 最大16M
     * LongBlob 最大 4G
     * */

    @Test
    public void testBlob() throws Exception {
        //该例子，数据库是test
        Connection connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO photo (id,name,birth,photo)  VALUES(?,?,?,?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, 2);
        ps.setObject(2, "Jerry2");
        FileInputStream fs = new FileInputStream(new File("src\\image1.png"));
        ps.setObject(3, "2000-01-01");
        ps.setObject(4, fs);

        ps.execute();
        JDBCUtils.closeResource(connection, ps);
    }
}
