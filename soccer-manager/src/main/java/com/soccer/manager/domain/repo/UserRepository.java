package com.soccer.manager.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.manager.domain.repo.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);
}
