package org.itstep.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.itstep.data.entity.Post;
import org.itstep.data.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
//@RequiredArgsConstructor
public class PostDaoImpl {
    private final static String SELECT_POSTS_COUNT =
            "SELECT COUNT(*) 'posts_count' FROM post;";
    private final static String SELECT_CURRENT_POSTS =
            "SELECT * FROM post ORDER BY id DESC LIMIT %s OFFSET %s;";
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void addPost(Post post) {
        entityManager.persist(post);
    }


    public int selectPostsCount() {
        String select = SELECT_POSTS_COUNT;
        System.out.println("select = " + select);
        int[] count = new int[1];
        count[0] = 0;
        Query nativeQuery = entityManager.createNativeQuery(select, Integer.class);
        List<Integer> resultList = nativeQuery.getResultList();
        if (resultList != null) {
            resultList.stream().forEach(a -> count[0] = (int)a);
//            resultList.stream().forEach(System.out::println);

        }
        return count[0];
    }

    public List<Post> selectCurrentPosts(int offset, int limit) {
//        List<Post> posts = new CopyOnWriteArrayList<>();
        String select = SELECT_CURRENT_POSTS.formatted(limit, offset);
        Query nativeQuery = entityManager.createNativeQuery(select, Post.class);
        List<Post> resultList = nativeQuery.getResultList();
        if (resultList != null) {
            resultList.stream().forEach(System.out::println);
        }
        return resultList;
    }

    public Post findById(int id) {
        return entityManager.find(Post.class, id);
    }
}
