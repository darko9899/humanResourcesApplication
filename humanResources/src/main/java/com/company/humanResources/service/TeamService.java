package com.company.humanResources.service;

import com.company.humanResources.model.*;
import com.company.humanResources.repo.EmployeeRepo;
import com.company.humanResources.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TeamService {
    private final TeamRepo teamRepo;
    private final EmployeeRepo employeeRepo;

    @Autowired
    public TeamService(TeamRepo teamRepo, EmployeeRepo employeeRepo) {
        this.teamRepo = teamRepo;
        this.employeeRepo = employeeRepo;
    }

    public Team addOrUpdateTeam(NewTeamDTO newTeamDTO) {
        Team team = new Team();
        team.setName(newTeamDTO.getName());
        List<Employee> employees = new ArrayList<>();
        for(Long employeeId: newTeamDTO.getEmployeeIds()) {
            employees.add(employeeRepo.findById(employeeId).get());
        }
        team.setEmployees(employees);
        Long id = newTeamDTO.getId();
        if(id == null) {
            id = Long.valueOf((long) Math.random());
        }
        team.setId(id);
        return teamRepo.save(team);
    }

    public List<TeamDTO> findAllTeams() {
        List<Team> teams = teamRepo.findAll();
        List<TeamDTO> teamDTOs = new ArrayList<>();
        Set<String> allProjects = new HashSet<>();
        for(Team team: teams) {
            List<EmployeeDTO> employees = new ArrayList<>();
            for(Employee employee: team.getEmployees()) {
                List<String> employeeProjects = employee.getProjects().stream().map(Project::getName).toList();
                allProjects.addAll(employeeProjects);
                String projects = String.join(", ", employeeProjects);
                EmployeeDTO teamEmployeeDTO = new EmployeeDTO(employee.getName(), employee.getJobTitle(), employee.getEmail(), projects);
                employees.add(teamEmployeeDTO);
            }
            TeamDTO teamDTO = new TeamDTO(team.getId(), String.join(", ", allProjects), employees, team.getName());
            teamDTOs.add(teamDTO);
        }
        return teamDTOs;
    }

    @Transactional
    public void deleteTeam(Long id){
        Team team = teamRepo.getById(id);
        team.setEmployees(new ArrayList<>());
        teamRepo.save(team);
        teamRepo.deleteTeamById(id);
    }
}


