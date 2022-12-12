package com.company.humanResources.repo;

import com.company.humanResources.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team, Long> {
    void deleteTeamById(Long id);
}


