package org.itstep.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itstep.dao.UserDaoImpl;
import org.itstep.data.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//@RequiredArgsConstructor
@Slf4j
public class UserService {

    private UserDaoImpl userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
        log.info("Injected userDao {}", userDao);
    }

//    public UserDao getUserDao() {
//        return userDao;
//    }
//
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
    public User findUserByLoginAndPassword (String login, String password){
        User userRezult = null;
        List<User> users = userDao.findAll();
        for (User user:users){
            if (login.equals(user.getLogin()) &&
                    password.equals(user.getPassword())){
                userRezult = user;
                break;
            }
        }
        return userRezult;
    }

    public void addPostToUser(User user) {
        userDao.addPostToUser(user);
    }
//
//    public User findUserById(int authorIdDB) {
//        return userDao.selectById(authorIdDB);
//    }
}
