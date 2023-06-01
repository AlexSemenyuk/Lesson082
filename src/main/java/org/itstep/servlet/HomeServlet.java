package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.itstep.data.CurrentPostsNav;
import org.itstep.data.Post;
import org.itstep.data.User;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("numberOfPages") == null){
            session.setAttribute("numberOfPages", 1);
        }

        List<Post> posts = postService.getCurrentPosts((Integer) session.getAttribute("numberOfPages"));
        if (posts != null) {
            session.setAttribute("posts", posts);
            System.out.println("GET posts");
            posts.stream().forEach(post -> System.out.println(post.toString()));
        }

        CurrentPostsNav nav = postService.getCurrentPostsNav((Integer) session.getAttribute("numberOfPages"));
        if (nav != null){
                System.out.println("GET: " + nav.toString());
                session.setAttribute("newer", nav.getNewer());
                session.setAttribute("older", nav.getOlder());
                session.setAttribute("buttons", nav.getButtons());
        }

        req.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String numberOfPagesInput = req.getParameter("numberOfPages");

        if (numberOfPagesInput != null && !numberOfPagesInput.isBlank() ) {
            System.out.println("numberOfPagesInput = " + numberOfPagesInput);
            switch (numberOfPagesInput){
                case "newer" -> {
                    if ((Integer)session.getAttribute("numberOfPages") > 1){
                        session.setAttribute("numberOfPages", (Integer)session.getAttribute("numberOfPages") - 1);
                    }
                }
                case "older" -> {
                    int amountOfButtons = postService.getAmountOfButtons();
                    if ((Integer)session.getAttribute("numberOfPages") < amountOfButtons){
                        session.setAttribute("numberOfPages", (Integer)session.getAttribute("numberOfPages") + 1);
                    }
                }
                default -> {
                    int numberOfPages = Integer.parseInt(numberOfPagesInput);
                    session.setAttribute("numberOfPages", numberOfPages);
                }
            }
        }

        resp.sendRedirect(req.getServletContext().getContextPath() + "/home");
    }

}
