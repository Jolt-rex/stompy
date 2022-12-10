package com.jolt.stompy.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="project_id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="created_on")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdOn;

    @Column(name="due_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueDate;

    @ManyToMany(fetch=FetchType.LAZY, cascade =  { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name="projects_users", joinColumns = { @JoinColumn(name="project_id") },
            inverseJoinColumns = { @JoinColumn(name="user_id")})
    private Set<User> users = new HashSet<>();

    public Project() {};

    public Project(String name, String description, LocalDateTime createdOn, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.createdOn = createdOn;
        this.dueDate = dueDate;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getProjects().add(this);
    }

    public void removeUser(int userId) {
        User user = this.users.stream().filter(usr -> usr.getId() == userId).findFirst().orElse(null);
        if(user != null) {
            this.users.remove(user);
            user.getProjects().remove(this);
        }
    }

    public int getId() {
        return id;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
