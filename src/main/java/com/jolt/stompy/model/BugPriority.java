package com.jolt.stompy.model;

import jakarta.persistence.*;

@Entity
@Table(name="priorities")
public class BugPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="priority_id")
    private int priorityId;

    @Column(name="name")
    private String name;

    public BugPriority() {};

    public BugPriority(int priorityId, String name) {
        this.priorityId = priorityId;
        this.name = name;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
