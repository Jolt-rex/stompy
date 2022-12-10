package com.jolt.stompy.model;

import jakarta.persistence.*;

@Entity
@Table(name="status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="status_id")
    private int id;

    @Column(name="status")
    private String status;

    public Status() {}

    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
