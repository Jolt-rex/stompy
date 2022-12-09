package com.jolt.stompy.repository;

import com.jolt.stompy.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findProjectsByUsersId(int id);
}