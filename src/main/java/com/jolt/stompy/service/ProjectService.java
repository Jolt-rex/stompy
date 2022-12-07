package com.jolt.stompy.service;

import com.jolt.stompy.entity.Project;

import java.util.List;

public interface ProjectService {

    public List<Project> findAll();

    public Project findById(int id);

    public List<Project> findByUserId(int id);

    public void save(Project project);

    public void deleteById(int id);
}
