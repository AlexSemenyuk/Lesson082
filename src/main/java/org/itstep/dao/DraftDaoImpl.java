package org.itstep.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.itstep.data.entity.Draft;
import org.itstep.data.entity.Post;
import org.itstep.data.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DraftDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Draft> findAll() {
        TypedQuery<Draft> query = entityManager.createQuery("select d from Draft d",
                Draft.class);
        List<Draft> drafts = query.getResultList();
        if (drafts != null){
            drafts.stream().forEach(System.out::println);
        }
        return drafts;
    }

}
