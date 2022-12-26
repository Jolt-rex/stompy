package com.jolt.stompy.controller;

import com.jolt.stompy.model.Role;
import com.jolt.stompy.model.User;
import com.jolt.stompy.repository.ProjectRepository;
import com.jolt.stompy.repository.RoleRepository;
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
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RoleRepository roleRepository;

    // expose "/users" and return list of users - must be admin
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.setPassword("*"));

        if(users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // GET a single user
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getUserById(@RequestAttribute("id") Integer id) {
        System.out.println(id);
        Optional<User> user = userRepository.findById(id);

        System.out.println(user);

        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        user.get().setPassword("*");
        Map<String, String> response = new HashMap<>();
        response.put("username", user.get().getUsername());
        response.put("email", user.get().getEmail());
        response.put("role", user.get().getRole().getName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) throws HttpClientErrorException.BadRequest {
        User userWithSameEmail = userRepository.findByEmail(user.getEmail());
        if(userWithSameEmail != null)
            return new ResponseEntity<>("Email already registered", HttpStatus.BAD_REQUEST);

        Optional<Role> role = roleRepository.findById(user.getRole().getId());
        if(!role.isPresent())
            return new ResponseEntity<>("Role is not valid", HttpStatus.BAD_REQUEST);

        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User newUser = userRepository.save(new User(user.getUsername(), user.getEmail(), encryptedPassword, role.get()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Authorization.generateJwt(newUser));
    }

    // change user password
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam(name="id") Integer id, @RequestBody Map<String, String> request) throws HttpClientErrorException.BadRequest {
        Optional<User> requestedUser = userRepository.findById(id);
        if(!requestedUser.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        String encryptedPassword = BCrypt.hashpw(request.get("password"), BCrypt.gensalt());

        requestedUser.get().setPassword(encryptedPassword);
        User newUser = userRepository.save(requestedUser.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Changed password");
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") int userId) {
        userRepository.deleteById(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
