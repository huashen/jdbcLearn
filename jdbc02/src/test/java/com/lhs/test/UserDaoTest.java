package com.lhs.test;

import com.lhs.dao.DaoFactory;
import com.lhs.dao.UserDao;
import com.lhs.domain.User;
import org.junit.Test;

/**
 * UserDaoTest
 *
 * @author longhuashen
 * @since 16/7/10
 */
public class UserDaoTest {

    @Test
    public void test() {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        System.out.println(userDao);
        User user = userDao.getUser(1);
    }
}
