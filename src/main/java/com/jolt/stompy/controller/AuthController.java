package com.jolt.stompy.controller;

import com.jolt.stompy.model.User;
import com.jolt.stompy.repository.UserRepository;
import com.jolt.stompy.shared.Authorization;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws HttpClientErrorException.BadRequest {
        User user = userRepository.findByEmail(request.get("email"));

        if(user == null)
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);

        if(!BCrypt.checkpw(request.get("password"), user.getPassword()))
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Authorization.generateJwt(user));
    }
}
