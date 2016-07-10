package com.lhs.dao.impl;

import com.lhs.dao.DaoException;
import com.lhs.dao.UserDao;
import com.lhs.domain.User;
import com.lhs.util.JdbcUtil;

import java.sql.*;

/**
 * UserDaoJdbcImpl
 *
 * @author longhuashen
 * @since 16/6/30
 */
public class UserDaoJdbcImpl implements UserDao {

    public void addUser(User user) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            try {
                conn = JdbcUtil.getConn();
                String sql = "insert into t_user(name,birthday,money) values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, user.getName());
                ps.setDate(2, new Date(user.getBirthDay().getTime()));
                ps.setFloat(3, user.getMoney());

                ps.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage(), e);
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
    }

    public User getUser(int userId) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = null;
        try {
            try {
                conn = JdbcUtil.getConn();
                String sql = "select * from t_user where id=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, userId);

                rs = ps.executeQuery();

                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setBirthDay(rs.getDate("birthday"));
                    user.setMoney(rs.getFloat("money"));
                }
                return user;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException(e.getMessage(), e);
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
    }

    public User findUser(String loginName, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "select * from t_user where name=? and password =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, loginName);
            ps.setString(2, password);

            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setBirthDay(rs.getDate("birthday"));
                user.setMoney(rs.getFloat("money"));
                return user;
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return null;
    }

    public void update(User user) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "update t_user set name=?, birthday=?, money=? where id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setDate(2, new Date(user.getBirthDay().getTime()));
            ps.setFloat(3, user.getMoney());
            ps.setInt(4, user.getId());

            ps.execute();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
    }

    public void delete(User user) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "delete t_user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
    }
}
