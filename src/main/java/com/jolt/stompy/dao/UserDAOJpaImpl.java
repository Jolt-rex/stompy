package com.jolt.stompy.dao;

import com.jolt.stompy.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOJpaImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        // create query
        Query query = entityManager.createQuery("FROM User");

        // execute query and get result list
        List<User> users = query.getResultList();

        // return results
        return users;
    }

    @Override
    public User findById(int id) {
        User user = entityManager.find(User.class, id);

        return user;
    }

    @Override
    public void save(User user) {
        User dbUser = entityManager.merge(user);

        user.setId(dbUser.getId());
    }

    @Override
    public void deleteById(int id) {
        Query query = entityManager.createQuery("DELETE FROM User WHERE id=:userId");

        query.setParameter("userId", id);

        query.executeUpdate();
    }
}
