package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.itstep.data.User;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
@MultipartConfig
@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        Part file = req.getPart("file");
        String content = req.getParameter("content");
        String draft = req.getParameter("draft");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Path pathOfContext = null;

        if (title != null && !title.isBlank() &&
                file != null &&
                user != null &&
                content != null && !content.isBlank() ) {
            try {
                pathOfContext = Path.of(req.getServletContext().getResource("").toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            postService.addPostToDb(title, user, file, content, draft, pathOfContext);
        }

        resp.sendRedirect(req.getServletContext().getContextPath() + "/admin");
    }
}

