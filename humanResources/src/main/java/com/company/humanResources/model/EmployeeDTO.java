package com.company.humanResources.model;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {

    private String name;
    private String role;
    private String email;
    private String projects;

    public EmployeeDTO(String name, String role, String email, String projects) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }
}
