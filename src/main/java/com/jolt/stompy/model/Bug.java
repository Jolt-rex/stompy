package com.jolt.stompy.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bug_id")
    private int id;

    @Column(name="summary")
    private String summary;

    @Column(name="description")
    private String description;

    @Column(name="created_by_id")
    private int raisedById;

    @Column(name="created_date")
    private Date identifiedDate;

    @Column(name="project_id")
    private int projectId;

    @Column(name="assigned_to_id")
    private int assignedToId;

    @Column(name="status_id")
    private int statusId;

    @Column(name="priority")
    private int priority;

    @Column(name="target_resolution_date")
    private Date targetResolutionDate;

    @Column(name="progress")
    private String progress;

    @Column(name="is_resolved")
    private boolean isResolved;

    @Column(name="resolution_date")
    private Date resolutionDate;

    @Column(name="resolution_summary")
    private String resolutionSummary;

    @Column(name="modified_on")
    private Date modifiedOn;

    @Column(name="modified_by")
    private String modifiedBy;

    public Bug() {}

    public Bug(int id, String summary, String description, int raisedById, Date identifiedDate, int projectId, int assignedToId, int statusId, int priority, Date targetResolutionDate, String progress, boolean isResolved, Date resolutionDate, String resolutionSummary, Date modifiedOn, String modifiedBy) {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.raisedById = raisedById;
        this.identifiedDate = identifiedDate;
        this.projectId = projectId;
        this.assignedToId = assignedToId;
        this.statusId = statusId;
        this.priority = priority;
        this.targetResolutionDate = targetResolutionDate;
        this.progress = progress;
        this.isResolved = isResolved;
        this.resolutionDate = resolutionDate;
        this.resolutionSummary = resolutionSummary;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRaisedById() {
        return raisedById;
    }

    public void setRaisedById(int raisedById) {
        this.raisedById = raisedById;
    }

    public Date getIdentifiedDate() {
        return identifiedDate;
    }

    public void setIdentifiedDate(Date identifiedDate) {
        this.identifiedDate = identifiedDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(int assignedToId) {
        this.assignedToId = assignedToId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getTargetResolutionDate() {
        return targetResolutionDate;
    }

    public void setTargetResolutionDate(Date targetResolutionDate) {
        this.targetResolutionDate = targetResolutionDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public String getResolutionSummary() {
        return resolutionSummary;
    }

    public void setResolutionSummary(String resolutionSummary) {
        this.resolutionSummary = resolutionSummary;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
