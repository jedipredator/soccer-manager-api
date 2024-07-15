package com.soccer.manager.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.manager.domain.repo.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {

}
