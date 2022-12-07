package com.jolt.stompy.service;

import com.jolt.stompy.entity.Project;
import com.jolt.stompy.entity.User;
import com.jolt.stompy.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(int id) {
        Optional<Project> result = projectRepository.findById(id);

        Project project = null;
        if(result.isPresent())
            project = result.get();
        else
            throw new RuntimeException("Did not find project id - " + id);

        return project;
    }

    @Override
    public List<Project> findByUserId(int id) {
        return projectRepository.findProjectsByUsersId(id);
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteById(int id) {
        projectRepository.deleteById(id);
    }
}
