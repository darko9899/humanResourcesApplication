package com.company.humanResources.repo;

import com.company.humanResources.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    void deleteProjectById(Long id);
}
