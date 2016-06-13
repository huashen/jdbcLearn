package com.lhs.learn;

import java.io.*;
import java.sql.*;

/**
 * ClobTest
 *
 * @author longhuashen
 * @since 16/6/13
 */
public class ClobTest {

    public static void main(String[] args) throws SQLException, IOException {
//        create();
        read();
    }

    static void create() throws SQLException, IOException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            String sql = "insert into clob_test(big_test) values(?)";
            ps = conn.prepareStatement(sql);
            File file = new File("/Users/longhuasshen/lhs/myProject/jdbc-learn/jdbc01/src/main/java/com/lhs/learn/JdbcUtil.java");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ps.setCharacterStream(1, reader, file.length());

            int i =  ps.executeUpdate();
            System.out.println(i);
            reader.close();
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
    }

    static void read() throws SQLException, IOException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            String sql = "select * from clob_test";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Clob clob = rs.getClob(2);
                Reader reader = clob.getCharacterStream();
                File file = new File("Jdbc_bak.txt");
                Writer writer = new BufferedWriter(new FileWriter(file));
                char[] chars = new char[1024];
                for(int i = 0; (i = reader.read(chars)) > 0;) {
                    writer.write(chars, 0, i);
                }
                writer.close();
                reader.close();
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
    }
}
