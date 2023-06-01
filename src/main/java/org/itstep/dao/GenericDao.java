package org.itstep.dao;

import org.itstep.data.Post;
import org.itstep.data.User;

import java.time.LocalDateTime;
import java.util.List;

// Data Access Object
public interface GenericDao<T, I> {
    void insert (T model);
    List<T> selectAll ();
    T selectById (I id);
    
}


