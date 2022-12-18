package com.jolt.stompy.controller;

import com.jolt.stompy.model.Bug;
import com.jolt.stompy.model.Project;
import com.jolt.stompy.model.User;
import com.jolt.stompy.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    @Autowired
    BugRepository bugRepository;

    @GetMapping("/")
    public ResponseEntity<List<Bug>> getAllBugs() {
        System.out.println("Get request for all bugs");
        List<Bug> bugs = bugRepository.findAll();

        return new ResponseEntity<>(bugs, HttpStatus.OK);
    }

//    // assign a user to a project
//    @PostMapping("/{projectId}/assignUser/{userId}")
//    public ResponseEntity<String> addUserToProject(@PathVariable("projectId") int projectId, @PathVariable("userId") int userId) {
//        Optional<Project> project = projectRepository.findById(projectId);
//        if(!project.isPresent())
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        Optional<User> user = userRepository.findById(userId);
//        if(!user.isPresent())
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        project.get().addUser(user.get());
//        userRepository.save(user.get());
//
//        String response = "Assigned user " + user.get().getUsername() + " to project " + project.get().getName();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    // remove a user from a project
//    @DeleteMapping("/projects/{projectId}/removeUsers/{userId}")
//    public ResponseEntity<String> removeUserFromProject(@PathVariable("projectId") int projectId, @PathVariable("userId") int userId) {
//        Optional<Project> project = projectRepository.findById(projectId);
//        if(!project.isPresent())
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        project.get().removeUser(userId);
//        projectRepository.save(project.get());
//
//        String response = "Removed user from project " + project.get().getName();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
