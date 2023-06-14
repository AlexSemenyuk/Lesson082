package org.itstep.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.itstep.data.dto.PostDTO;
import org.itstep.data.entity.Draft;
import org.itstep.data.entity.Post;
import org.itstep.data.entity.User;
import org.itstep.service.PostService;
import org.itstep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
//@RequiredArgsConstructor
public class BlogController {
    private PostService postService;
    private UserService userService;


    public BlogController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
        log.info("Injected postService {}", postService);
        log.info("Injected userService {}", userService);
    }

    @GetMapping("/")
    public String signinGet() {
        return "signin";
    }

    @PostMapping("/")
    public String signinPost(String login, String password, HttpSession session) {
        User user = null;
        if (login != null && !login.isBlank() &&
                password != null && !password.isBlank()) {
            System.out.println("login = " + login);
            System.out.println("password = " + password);
            user = userService.findUserByLoginAndPassword(login, password);
            if (user != null) {
                System.out.println("SignIn-Post: " + user.toString());
                session.setAttribute("user", user);
            }
        }
//        model.addAttribute("posts", postDao.findAll());
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminGet() {
        return "admin";
    }

    @PostMapping("/admin")
    public String adminAddPost(PostDTO postDTO, String draft, HttpSession session) {
        if (postDTO != null) {
            System.out.println("Post Controller: postDTO = " + postDTO);

            System.out.println("Post Controller: draft = " + draft);

            User user = (User) session.getAttribute("user");
            postService.addPostToDb(postDTO, draft, user);
        }
        return "redirect:/admin";
    }

    @GetMapping("/home")
    public String homeGet(HttpSession session) {
        if (session.getAttribute("numberOfPages") == null) {
            session.setAttribute("numberOfPages", 1);
        }
        List<Post> posts = postService.getCurrentPosts((Integer) session.getAttribute("numberOfPages"));
        if (posts != null) {
            System.out.println("homeGET posts");
            posts.forEach(System.out::println);
            session.setAttribute("posts", posts);
            posts.forEach(post -> System.out.println(post.toString()));
        }
        return "home";
    }

    @GetMapping("/post/{id}")
    public String postDetailGet(@PathVariable int id, HttpSession session) {
        log.info("postDetailGet id = {}", id);
        Post post = postService.findById(id);
        if (post != null){
            System.out.println(post.toString());
            session.setAttribute("post", post);
        }
        return "post";
    }

}
