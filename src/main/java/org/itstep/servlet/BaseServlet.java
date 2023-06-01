package org.itstep.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import org.itstep.service.PostService;
import org.itstep.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.nio.file.Path;

public abstract class BaseServlet extends HttpServlet {
    protected UserService userService;
    protected PostService postService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = new FileSystemXmlApplicationContext("e:/JAVA/Projects/GitHub Homeworks/Lesson082/src/main/webapp/WEB-INF/config/application.xml");
        userService = context.getBean("userService", UserService.class);
        postService = context.getBean("postService", PostService.class);
//        userService = new UserService();
//        postService = new PostService();
    }
}




