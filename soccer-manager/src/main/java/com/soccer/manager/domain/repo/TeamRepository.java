package com.soccer.manager.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.manager.domain.repo.entity.Team;

public interface TeamRepository extends JpaRepository<Team, String> {

}
