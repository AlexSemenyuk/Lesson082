package org.itstep.dao.impl;

import org.itstep.dao.GenericDao;
import org.itstep.data.Post;
import org.itstep.data.User;

import java.util.List;

public interface PostDao extends GenericDao<Post, Integer> {

    List<Post> selectCurrentPosts(int numberPage, int postsAmountOnPage);
    int selectPostsCount ();
}