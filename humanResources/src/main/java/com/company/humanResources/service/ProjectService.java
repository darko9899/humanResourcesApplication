package com.company.humanResources.service;

import com.company.humanResources.model.*;
import com.company.humanResources.repo.EmployeeRepo;
import com.company.humanResources.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final EmployeeRepo employeeRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo, EmployeeRepo employeeRepo) {
        this.projectRepo = projectRepo;
        this.employeeRepo = employeeRepo;
    }

    public Project addOrUpdateProject(NewProjectDTO newProjectDTO) {
        Project project = new Project();
        Long id = newProjectDTO.getId();
        if(id != null) {
            project.setId(id);
        }

        project.setName(newProjectDTO.getName());
        Project savedProject = projectRepo.save(project);

        for (Employee employee : employeeRepo.findAll()) {
            if (newProjectDTO.getEmployeeIds().contains(employee.getId())) {
                List<Project> projects = employee.getProjects();
                if (!projects.contains(project)){
                    projects.add(project);
                    employee.setProjects(projects);
                    employeeRepo.save(employee);
                }

            } else {
                List<Project> projects = employee.getProjects();
                projects.remove(project);
                employee.setProjects(projects);
                employeeRepo.save(employee);
            }
        }
//        for(Long employeeId: newProjectDTO.getEmployeeIds()) {
//            Employee employee = employeeRepo.findEmployeeById(employeeId).get();
//            List<Project> projects = employee.getProjects();
//            projects.add(project);
//            employee.setProjects(projects);
//            employeeRepo.save(employee);
//        }
        return savedProject;
    }

    public List<ProjectDTO> findAllProjects() {
        List<Project> projects = projectRepo.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for(Project project: projects) {
            List<EmployeeDTO> employees = new ArrayList<>();
            for(Employee employee: employeeRepo.findAll()) {
                if (employee.getProjects().contains(project)) {
                    EmployeeDTO projectEmployeeDTO = new EmployeeDTO(employee.getName(), employee.getJobTitle(), employee.getEmail(), null);
                    employees.add(projectEmployeeDTO);
                }
            }
            ProjectDTO projectDTO = new ProjectDTO(project.getId(), project.getName(), employees);
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }

    public Project updateProject(Project project) {
        return projectRepo.save(project);
    }

    @Transactional
    public void deleteProject (Long id){
        Project project = projectRepo.getById(id);
        for(Employee employee: employeeRepo.findAll()) {
            List<Project> projects = employee.getProjects();
            if (projects.contains(project)) {
                projects.remove(project);
                employee.setProjects(projects);
                employeeRepo.save(employee);
            }
        }
        projectRepo.save(project);
        projectRepo.deleteProjectById(id);
    }
}

