package com.jolt.stompy.model;

import jakarta.persistence.*;

@Entity
@Table(name="bug_status")
public class BugStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bug_status_id")
    private int bugStatusId;

    @Column(name="status")
    private String status;

    public BugStatus() {}

    public BugStatus(int id, String status) {
        this.bugStatusId = id;
        this.status = status;
    }

    public int getId() {
        return bugStatusId;
    }

    public void setId(int id) {
        this.bugStatusId = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
