package com.jolt.stompy.controller;

import com.jolt.stompy.model.Project;
import com.jolt.stompy.model.User;
import com.jolt.stompy.repository.ProjectRepository;
import com.jolt.stompy.repository.UserRepository;
import com.jolt.stompy.shared.Authorization;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // expose "/users" and return list of users - must be admin
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();

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

    // register a new user
    @PostMapping("/users/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody User user) throws HttpClientErrorException.BadRequest {
        User userWithSameEmail = userRepository.findByEmail(user.getEmail());
        if(userWithSameEmail != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User newUser = userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail(), encryptedPassword, false));

        HttpHeaders authHeader = new HttpHeaders();
        authHeader.set("x-auth-token", Authorization.generateJwt(newUser));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(authHeader)
                .body("Created new user");
    }



    // assign a user to a project
    @PostMapping("/projects/{projectId}/assignUser/{userId}")
    public ResponseEntity<String> addUserToProject(@PathVariable("projectId") int projectId, @PathVariable("userId") int userId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if(!project.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        project.get().addUser(user.get());
        userRepository.save(user.get());

        String response = "Assigned user " + user.get().getFirstName() + " " + user.get().getLastName() + " to project " + project.get().getName();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // change user password - TODO change to Map in request body and move id to body from path
    @PutMapping("/users/changePassword/{id}")
    public ResponseEntity<String> changePassword(@PathVariable("id") int id, @RequestBody String password) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        user.get().setPassword(password);
        User newUser = userRepository.save(user.get());

        HttpHeaders authHeader = new HttpHeaders();
        authHeader.set("x-auth-token", Authorization.generateJwt(newUser));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(authHeader)
                .body("Changed password");
    }

    // remove a user from a project
    @DeleteMapping("/projects/{projectId}/removeUsers/{userId}")
    public ResponseEntity<String> removeUserFromProject(@PathVariable("projectId") int projectId, @PathVariable("userId") int userId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if(!project.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        project.get().removeUser(userId);
        projectRepository.save(project.get());

        String response = "Removed user from project " + project.get().getName();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int userId) {
        userRepository.deleteById(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
