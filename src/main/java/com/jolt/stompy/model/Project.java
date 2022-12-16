package com.jolt.stompy.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="project_id")
    private int projectId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="created_on")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdOn;

    @Column(name="version")
    private String version;

    @OneToMany(mappedBy = "project")
    List<Bug> bugs;

    public Project() {};

    public Project(String name, String description, LocalDateTime createdOn, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.createdOn = createdOn;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }


}
