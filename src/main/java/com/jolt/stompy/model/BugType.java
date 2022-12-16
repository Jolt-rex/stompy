package com.jolt.stompy.model;

import jakarta.persistence.*;

@Entity
@Table(name="bug_types")
public class BugType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bug_type_id")
    private int bugTypeId;

    @Column(name="name")
    private String name;

    public BugType() {}

    public BugType(int bugTypeId, String name) {
        this.bugTypeId = bugTypeId;
        this.name = name;
    }

    public int getBugTypeId() {
        return bugTypeId;
    }

    public void setBugTypeId(int bugTypeId) {
        this.bugTypeId = bugTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
