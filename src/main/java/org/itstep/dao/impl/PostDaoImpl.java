package org.itstep.dao.impl;

import org.itstep.DbUtils;
import org.itstep.data.Draft;
import org.itstep.data.Post;
import org.itstep.data.User;
import org.itstep.service.UserService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class PostDaoImpl implements PostDao {
    final DbUtils dbUtils;


    private final static String SELECT_ALL_POSTS = "SELECT * FROM posts;";
    private final static String SELECT_CURRENT_POSTS =
            "SELECT * FROM posts WHERE draft_id = 2 ORDER BY id DESC LIMIT %s OFFSET %s;";
    private final static String SELECT_POSTS_COUNT =
            "SELECT COUNT(*) 'posts_count' FROM posts WHERE draft_id = 2;";
    private final static String SELECT_POST_BY_ID = "SELECT * FROM posts WHERE id = %s;";
    private final static String INSERT_POST = "INSERT INTO posts (title, published, author_id, image_path, content, draft_id)" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    public PostDaoImpl() {
        dbUtils = DbUtils.getInstance();
//        dbUtils.init(url, username, password);
    }

    @Override
    public void insert(Post post) {
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    var stmt = connection.prepareStatement(INSERT_POST);
                    stmt.setString(1, post.getTitle());
                    stmt.setString(2, getPublishedString(post.getPublished()));
                    stmt.setInt(3, post.getUser().getId());
                    stmt.setString(4, post.getImagePath());
                    stmt.setString(5, post.getContent());
                    stmt.setInt(6, post.getDraft().num());
                    stmt.executeUpdate();
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
    }

    private String getPublishedString(LocalDateTime time) {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format((TemporalAccessor) time);
    }

    @Override
    public List<Post> selectAll() {
        List<Post> posts = new CopyOnWriteArrayList<>();
        System.out.println("method selectAllUsers() isn't implemented now");
        return posts;
    }

    @Override
    public List<Post> selectCurrentPosts(int offset, int limit) {
        List<Post> posts = new CopyOnWriteArrayList<>();
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(SELECT_CURRENT_POSTS.formatted(limit, offset));
                    while (resultSet.next()) {
                        int idDB = resultSet.getInt("id");
                        String titleDB = resultSet.getString("title");
                        String publishedDB = resultSet.getString("published");
                        int authorIdDB = resultSet.getInt("author_id");
                        String imagePathDB = resultSet.getString("image_path");
                        String contentDB = resultSet.getString("content");
                        int draftIdDB = resultSet.getInt("draft_id");

                        LocalDateTime published = getPublished (publishedDB);
                        Draft draft = getDraft(draftIdDB);

                        posts.add(new Post(idDB, titleDB, published, authorIdDB, imagePathDB, contentDB, draft));
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
        return posts;
    }

    private LocalDateTime getPublished(String publishedDB) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return LocalDateTime.parse(publishedDB, formatter);
    }

    private static Draft getDraft(int draftIdDB) {
        Draft draft = null;
        switch (draftIdDB) {
            case 1 -> draft = Draft.YES;
            case 2 -> draft = Draft.NO;
        }
        return draft;
    }

    public Post selectById(Integer id) {
        String select = SELECT_POST_BY_ID.formatted(id);
        System.out.println("select = " + select);
        Post [] rezultPost = new Post[1];
        rezultPost[0] = null;
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(select);
                    while (resultSet.next()) {
                        int idDB = resultSet.getInt("id");
                        String titleDB = resultSet.getString("title");
                        String publishedDB = resultSet.getString("published");
                        int authorIdDB = resultSet.getInt("author_id");
                        String imagePathDB = resultSet.getString("image_path");
                        String contentDB = resultSet.getString("content");
                        int draftIdDB = resultSet.getInt("draft_id");

                        LocalDateTime published = getPublished (publishedDB);

                        Draft draft = getDraft(draftIdDB);

                        rezultPost[0] = new Post (idDB, titleDB, published, authorIdDB, imagePathDB, contentDB, draft);
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
        return rezultPost[0];
    }

    @Override
    public int selectPostsCount (){
        String select = SELECT_POSTS_COUNT;
        System.out.println("select = " + select);
        int [] rezultPost = new int[1];
        rezultPost[0] = 0;
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(select);
                    while (resultSet.next()) {
                        rezultPost[0] = resultSet.getInt("posts_count");
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
        return rezultPost[0];
    }

}
