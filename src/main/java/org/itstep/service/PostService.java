package org.itstep.service;

import jakarta.servlet.http.Part;
import org.itstep.dao.impl.PostDao;
import org.itstep.dao.impl.PostDaoImpl;
import org.itstep.data.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PostService {
    private PostDao postDao;
    private UserService userService;

    protected static final int amountPostsOnPage = 4;

    public PostService() {
        this.postDao = new PostDaoImpl();
        this.userService = new UserService();
    }

    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void addPostToDb(String titleInput, User userSession, Part fileInput, String contentInput, String draftInput, Path pathOfContext) {
        Post post = null;

        // write image file to Project / Tomcat
        try {
            writeImage(fileInput, pathOfContext);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LocalDateTime published = LocalDateTime.now();

        Draft draft = getDraft(draftInput);

        String imagePath = "resources/images/" + fileInput.getSubmittedFileName();
        post = new Post(0, titleInput, published, userSession, imagePath, contentInput, draft);
        if (post != null) {
            System.out.println(post.toString());
        }
        postDao.insert(post);
    }

    private static void writeImage(Part file, Path pathOfContext) throws IOException {
        Path pathOnProject = Path.of("e:/JAVA/Projects/GitHub Homeworks/Lesson081/src/main/webapp/resources/images");
        writeFile(file, pathOnProject);
        Path pathOnTomcat = Path.of(pathOfContext.toString(), "resources", "images");
        writeFile(file, pathOnTomcat);
    }

    private static void writeFile(Part file, Path path) throws IOException {
        Path filePath = null;
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        filePath = Path.of(path.toString(), file.getSubmittedFileName());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Saved successfully to " + filePath);
    }

    private static Draft getDraft(String draft) {
        Draft rezultDraft = null;
        if (draft == null) {
            draft = "off";
        }
        switch (draft) {
            case "on" -> rezultDraft = Draft.YES;
            case "off" -> rezultDraft = Draft.NO;
        }
        return rezultDraft;
    }

    public Post findPostById(int postId) {
        Post post = postDao.selectById(postId);
        int userId = post.getUserId();
        User user = userService.findUserById(userId);
        post.setUser(user);
        return post;
    }

    public List<Post> getCurrentPosts(int numberOfPages) {
        int offset = (numberOfPages - 1) * amountPostsOnPage;
        int amountOfPosts = postDao.selectPostsCount();
        int limit;
        if (amountOfPosts - offset >= amountPostsOnPage) {
            limit = amountPostsOnPage;
        } else {
            limit = amountOfPosts - offset;
        }
        List<Post> posts = postDao.selectCurrentPosts(offset, limit);
        for (Post post: posts){
            int userId = post.getUserId();
            User user = userService.findUserById(userId);
            post.setUser(user);
        }
        return posts;
    }

    public CurrentPostsNav getCurrentPostsNav(int numberOfPages) {
        int amountOfButtons = getAmountOfButtons();
        int amountOfVisibleButtons;
        if (amountOfButtons < CurrentPostsNav.amountButtonsOfNav) {
            amountOfVisibleButtons = amountOfButtons;
        } else {
            amountOfVisibleButtons = CurrentPostsNav.amountButtonsOfNav;
        }
        ButtonCurrentPostsNav[] buttons = new ButtonCurrentPostsNav[amountOfVisibleButtons];
        String on = CurrentPostsNav.on;
        String off = CurrentPostsNav.off;

        String newer = off;
        if (numberOfPages > 2) {
            newer = on;
        }

        String older = off;
        if ((amountOfButtons - numberOfPages > 2 && numberOfPages == 1) ||
                (amountOfButtons - numberOfPages > 1 && numberOfPages != 1)) {
            older = on;
        }

        int meanTMP;
        if (numberOfPages == 1){
            meanTMP = numberOfPages;
        } else if (numberOfPages == amountOfButtons){
            meanTMP = numberOfPages - 2;
        } else {
            meanTMP = numberOfPages - 1;
        }

        for (int i = 0; i < buttons.length; i++) {
            int btnMean = meanTMP + i;
            String btnActive;
            if (btnMean == numberOfPages) {
                btnActive = on;
            } else {
                btnActive = off;
            }
            buttons[i] = new ButtonCurrentPostsNav(btnMean, btnActive);
        }

        CurrentPostsNav nav = new CurrentPostsNav(newer, buttons, older);
        if (nav != null){
            System.out.println("PostService: " + nav.toString());
        }
        return nav;
    }

    public int getAmountOfButtons() {
        int amountOfPosts = postDao.selectPostsCount();
        int amountOfButtons = (int) (Math.ceil(((double)amountOfPosts) / ((double)amountPostsOnPage)));
        return amountOfButtons;
    }

}
