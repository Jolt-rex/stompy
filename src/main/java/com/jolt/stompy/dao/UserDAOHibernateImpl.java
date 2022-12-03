package com.jolt.stompy.dao;

import com.jolt.stompy.entity.User;
import jakarta.persistence.EntityManager;
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
    public List<User> findAll() {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create query
        Query<User> query = currentSession.createQuery("FROM User", User.class);

        // execute the query
        List<User> users = query.getResultList();

        // return results
        return users;
    }

    @Override
    public User findById(int id) {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the user
        User user = currentSession.get(User.class, id);

        return user;
    }

    @Override
    public void save(User user) {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save user
        currentSession.saveOrUpdate(user);
    }

    @Override
    public void deleteById(int id) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query query = currentSession.createQuery(
                "DELETE FROM users WHERE id=:userId", User.class);
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
