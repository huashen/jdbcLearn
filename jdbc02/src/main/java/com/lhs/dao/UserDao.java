package com.lhs.dao;

import com.lhs.domain.User;

/**
 * UserDao
 *
 * @author longhuashen
 * @since 16/6/30
 */
public interface UserDao {

    void addUser(User user);

    User getUser(int userId);

    User findUser(String loginName, String password);

    void update(User user);

    void delete(User user);
}
