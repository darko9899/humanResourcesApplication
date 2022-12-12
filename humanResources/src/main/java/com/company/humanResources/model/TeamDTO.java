package com.company.humanResources.model;

import java.io.Serializable;
import java.util.List;

public class TeamDTO implements Serializable {

    private Long id;
    private String name;
    private String allTeamProjects;
    private List<EmployeeDTO> employees ;

    public TeamDTO(Long id, String allTeamProjects, List<EmployeeDTO> employees, String name) {
        this.id = id;
        this.allTeamProjects = allTeamProjects;
        this.employees = employees;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllTeamProjects() {
        return allTeamProjects;
    }

    public void setAllTeamProjects(String allTeamProjects) {
        this.allTeamProjects = allTeamProjects;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
