package com.lhs.dao;

import java.util.Properties;

/**
 * DaoFactory
 *
 * @author longhuashen
 * @since 16/7/10
 */
public class DaoFactory {

    private static UserDao userDao = null;

    private static DaoFactory daoFactory = new DaoFactory();

    private DaoFactory() {
        try {
            Properties prop = new Properties();
            prop.load(DaoFactory.class.getClassLoader().getResourceAsStream("conf.properties"));
            String className = (String) prop.get("userDao");
            userDao = (UserDao) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DaoFactory getInstance() {
        return daoFactory;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
