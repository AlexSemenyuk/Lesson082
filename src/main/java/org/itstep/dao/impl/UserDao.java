package org.itstep.dao.impl;

import org.itstep.dao.GenericDao;
import org.itstep.data.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Integer> {
    User selectByLoginAndPassword (String login, String password);
}




