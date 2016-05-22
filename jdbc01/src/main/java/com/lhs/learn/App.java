package com.lhs.learn;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        //2.建立连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");

        //3.创建语句
        Statement st = conn.createStatement();

        //4.执行语句
        ResultSet rs = st.executeQuery("select * from t_user");

        //5.处理结果
        while (rs.next()) {
System.out.println(rs.getObject(1) + ":" + rs.getObject(2) + ":" + rs.getObject(3) + ":" + rs.getObject(4));
        }

        //6.释放资源
        rs.close();
        st.close();
        conn.close();
    }
}
