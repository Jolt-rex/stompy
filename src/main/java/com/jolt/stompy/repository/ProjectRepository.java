package com.jolt.stompy.repository;

import com.jolt.stompy.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findProjectsByUsersId(int id);

    @Query(value="SELECT * FROM projects p WHERE p.name=:name", nativeQuery = true)
    Project findProjectByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value="DELETE p, pu FROM projects p INNER JOIN projects_users pu WHERE p.project_id=pu.project_id AND p.project_id=:id", nativeQuery = true)
    void deleteById(@Param("id") int id);
}
