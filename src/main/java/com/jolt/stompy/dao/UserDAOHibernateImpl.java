package com.jolt.stompy.dao;

import com.jolt.stompy.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOHibernateImpl implements UserDAO {

    private EntityManager entityManager;

    // constructor injection
    @Autowired
    public UserDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create query
        Query<User> query = currentSession.createQuery("from User", User.class);

        // execute the query
        List<User> users = query.getResultList();

        // return results
        return users;
    }
}
