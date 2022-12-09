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
public class UserRestController {

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
    @PostMapping("/projects/{projectId}/users")
    public ResponseEntity<Project> addUserToProject(@PathVariable(value="projectId") int projectId, @RequestBody User userRequest) {
        Optional<Project> result = projectRepository.findById(projectId);
        if(!result.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Project project = result.get();

        Optional<User> user = userRepository.findById(userRequest.getId());
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        project.addUser(user.get());




        return new ResponseEntity<>(HttpStatus.OK);
    }

    // update a user
    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userRepository.save(user);

        return user;
    }

    // delete a user
    @DeleteMapping("/users/{userId}")
    public String deleteEmployee(@PathVariable int userId) {
        Optional<User> tempUser = userRepository.findById(userId);
        if(tempUser == null) {
            throw new RuntimeException("User ID not found - " + userId);
        }

        userRepository.deleteById(userId);

        return "Removed User - " + tempUser;
    }
}
