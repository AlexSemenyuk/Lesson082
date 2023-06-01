package org.itstep.dao.impl;

import org.itstep.DbUtils;
import org.itstep.data.Role;
import org.itstep.data.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserDaoImpl implements UserDao  {
    final DbUtils dbUtils;
    private final static String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = '%s' and password = '%s';";
    private final static String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = %s;";
    private final static String SELECT_ALL = "SELECT * FROM users;";

    public UserDaoImpl() {
            dbUtils = DbUtils.getInstance();
    }

    @Override
    public void insert(User user){
        System.out.println("method insertUser() isn't implemented now");
    }
    @Override
    public List<User> selectAll() {
        List<User> users = new CopyOnWriteArrayList<>();
        System.out.println("method selectAllUsers() isn't implemented now");
        return users;
    }
    @Override
    public User selectById(Integer id){
        String selectTotal = SELECT_USER_BY_ID.formatted(id);
        System.out.println("selectTotal = " + selectTotal);
        return findUserBySelect(selectTotal);
    }

    @Override
    public User selectByLoginAndPassword(String login, String password) {
        String selectTotal = SELECT_USER_BY_LOGIN_AND_PASSWORD.formatted(login, password);
        System.out.println("selectTotal = " + selectTotal);
        return findUserBySelect(selectTotal);
    }

    private User findUserBySelect (String select){
        User [] rezultUser = new User[1];
        rezultUser[0] = null;
        if (dbUtils != null){
            System.out.println("Hello find user" + dbUtils);
        }
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            if (optionalConnection != null){
            System.out.println("Hello find user2" + optionalConnection);
            } else {
                System.out.println("Hello find user2 - null");
            }
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(select);
                    while (resultSet.next()) {
                        int idDB = resultSet.getInt("id");
                        String firstNameDB = resultSet.getString("first_name");
                        String lastNameDB = resultSet.getString("last_name");
                        String avatarDB = resultSet.getString("avatar");
                        String loginDB = resultSet.getString("login");
                        String passwordDB = resultSet.getString("password");
                        int roleDB = resultSet.getInt("role_id");
                        Role role = null;
                        switch (roleDB){
                            case 1 -> role = Role.USER;
                            case 2 -> role = Role.ADMIN;
//                            default: role = Role.USER;
                        }
                        rezultUser[0] = new User(idDB, firstNameDB, lastNameDB, avatarDB, loginDB, passwordDB, role);
//                        System.out.println("BlogDaoImpl " + rezultUser[0].toString());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rezultUser[0];
    }
}
