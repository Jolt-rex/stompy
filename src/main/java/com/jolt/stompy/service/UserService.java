package com.jolt.stompy.service;

import com.jolt.stompy.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int id);

    public void save(User user);

    public void deleteById(int id);
}
