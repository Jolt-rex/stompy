package com.jolt.stompy.controller;

import com.jolt.stompy.model.Project;
import com.jolt.stompy.model.User;
import com.jolt.stompy.repository.ProjectRepository;
import com.jolt.stompy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<Project>> findAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable("id") int id) {
        Optional<Project> project = projectRepository.findById(id);

        if(!project.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(project.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addProject(@RequestBody Project projectReq) {
        Project project = projectRepository.findProjectByName(projectReq.getName());
        if(project != null)
            return new ResponseEntity<>("Project with this name already exists", HttpStatus.BAD_REQUEST);

        Project newProject = projectRepository.save(projectReq);
        if(newProject == null)
            return new ResponseEntity<>("Failed to create new Project", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>("Project added", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") int id, @RequestBody Project projectReq) {
        Optional<Project> project = projectRepository.findById(id);
        if(!project.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        project.get().setName(projectReq.getName());
        project.get().setDescription(projectReq.getDescription());

        Project newProject = projectRepository.save(project.get());
        if(newProject == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(newProject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") int id) {
        projectRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
