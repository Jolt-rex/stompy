package com.jolt.stompy.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    @Email
    private String email;

    @Column(name="password")
    private String password;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;

    @ManyToMany
    @JoinTable(
        name="bugs_users",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="bug_id")
    )
    private List<Bug> bugs;

    // constructors
    public User() {

    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) { this.role = role; }

    public String getRole() { return role; }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }

    public void assignBug(Bug bug) {
        this.bugs.add(bug);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='*" + '\'' +
                ", role='" + role.getName() + '\'' +
                '}';
    }
}
