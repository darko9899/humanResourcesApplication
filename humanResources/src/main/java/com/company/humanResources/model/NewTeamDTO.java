package com.company.humanResources.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewTeamDTO implements Serializable {

    private Long id;
    private String name;
    private List<Long> employeeIds = new ArrayList<>();

    public NewTeamDTO(Long id, String name, List<Long> employeeIds) {
        this.id = id;
        this.name = name;
        if(employeeIds != null) {
            this.employeeIds = employeeIds;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }
}
