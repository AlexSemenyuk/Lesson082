package org.itstep.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;
import org.itstep.DbUtils;


@WebListener
public class InitDatabaseListener implements ServletContextListener, HttpSessionListener {
//    protected static DbUtils dbUtils;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String url = "jdbc:mariadb://localhost/myblog";
        String username = "root";
        String password = "";
        final DbUtils dbUtils = DbUtils.getInstance();;
        dbUtils.init(url, username, password);
    }
}

