package com.jolt.stompy.controller;

import com.jolt.stompy.model.Project;
import com.jolt.stompy.model.User;
import com.jolt.stompy.repository.ProjectRepository;
import com.jolt.stompy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // expose "/users" and return list of users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        if(users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // GET a single user
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent())
            return new ResponseEntity<>(user.get(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // add new user
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userWithSameEmail = userRepository.findByEmail(user.getEmail());
        if(userWithSameEmail != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User newUser = userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // assign a user to a project
    @PostMapping("/projects/{projectId}/assignUser/{userId}")
    public ResponseEntity<User> addUserToProject(@PathVariable("projectId") int projectId, @PathVariable("userId") int userId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if(!project.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        project.get().addUser(user.get());
        userRepository.save(user.get());

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    // change user password
    @PutMapping("/users/changePassword/{id}")
    public ResponseEntity<User> changePassword(@PathVariable("id") int id, @RequestBody String password) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        user.get().setPassword(password);
        userRepository.save(user.get());

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    // remove a user from a project
    @DeleteMapping("/projects/{projectId}/users/{userId}")
    public ResponseEntity<User> removeUserFromProject(@PathVariable("projectId") int projectId, @PathVariable("userId") int userId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if(!project.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        project.get().removeUser(userId);
        projectRepository.save(project.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int userId) {
        userRepository.deleteById(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
