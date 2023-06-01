package org.itstep.service;

import org.itstep.dao.impl.PostDao;
import org.itstep.dao.impl.UserDao;
import org.itstep.dao.impl.UserDaoImpl;
import org.itstep.data.User;

public class UserService {
    private UserDao userDao;
    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserByLoginAndPassword (String login, String password){
        return userDao.selectByLoginAndPassword(login, password);
    }

    public User findUserById(int authorIdDB) {
        return userDao.selectById(authorIdDB);
    }
}
