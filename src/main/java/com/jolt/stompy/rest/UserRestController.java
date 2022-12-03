package com.jolt.stompy.rest;

import com.jolt.stompy.entity.User;
import com.jolt.stompy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    // inject user dao
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // expose "/users" and return list of users
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    // GET a single user
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User user = userService.findById(userId);

        if(user == null) {
            throw new RuntimeException("User ID not found - " + userId);
        }

        return user;
    }

    // add new user
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.setId(0);
        userService.save(user);

        return user;
    }

}
