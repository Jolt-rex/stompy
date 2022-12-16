package com.jolt.stompy.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bug_id")
    private int bugId;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name="project_id")
    private Project project;

    @Column(name="summary")
    private String summary;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="created_user_id")
    private User createdByUser;

    @Column(name="created_on")
    private Date createdOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="priority_id")
    private BugPriority priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="bug_status_id")
    private BugStatus status;

    @ManyToMany(mappedBy = "bugs")
    private List<User> assignedUsers;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="bug_type_id")
    private BugType type;

    @Column(name="bug_version")
    private String version;

    @Column(name="resolution_version")
    private String resolutionVersion;

    @Column(name="resolution_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime resolutionDate;

    public Bug() {}

    public Bug(int bugId, Project project, String summary, String description, User createdByUser, Date createdOn, BugPriority priority, BugStatus status, List<User> assignedUsers, BugType type, String version, String resolutionVersion, LocalDateTime resolutionDate) {
        this.bugId = bugId;
        this.project = project;
        this.summary = summary;
        this.description = description;
        this.createdByUser = createdByUser;
        this.createdOn = createdOn;
        this.priority = priority;
        this.status = status;
        this.assignedUsers = assignedUsers;
        this.type = type;
        this.version = version;
        this.resolutionVersion = resolutionVersion;
        this.resolutionDate = resolutionDate;
    }

    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public BugPriority getPriority() {
        return priority;
    }

    public void setPriority(BugPriority priority) {
        this.priority = priority;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public BugType getType() {
        return type;
    }

    public void setType(BugType type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResolutionVersion() {
        return resolutionVersion;
    }

    public void setResolutionVersion(String resolutionVersion) {
        this.resolutionVersion = resolutionVersion;
    }

    public LocalDateTime getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(LocalDateTime resolutionDate) {
        this.resolutionDate = resolutionDate;
    }
}
