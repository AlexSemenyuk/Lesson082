package org.itstep.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.itstep.data.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor
public class UserDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;


    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u",
                User.class);
        List<User> users = query.getResultList();
        if (users != null){
            users.stream().forEach(System.out::println);
        }
        return users;
    }
    @Transactional
    public void addPostToUser(User user) {
        entityManager.merge(user);
    }

//    public User selectByLoginAndPassword(String login, String password) {
//        String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = '%s' and password = '%s';";
//        String selectTotal = SELECT_USER_BY_LOGIN_AND_PASSWORD.formatted(login, password);
//        System.out.println("selectTotal = " + selectTotal);
//        return findUserBySelect(selectTotal);
//    }

}
