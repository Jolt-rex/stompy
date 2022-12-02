package com.jolt.stompy.rest;

import com.jolt.stompy.dao.UserDAO;
import com.jolt.stompy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserDAO userDAO;

    // inject user dao
    @Autowired
    public UserRestController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // expose "/users" and return list of users
    @GetMapping("/users")
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
