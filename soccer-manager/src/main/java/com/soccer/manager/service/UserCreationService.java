package com.soccer.manager.service;

import com.soccer.manager.domain.dto.request.UserCreationRequestDTO;
import com.soccer.manager.domain.repo.entity.User;

public interface UserCreationService {

	User create(UserCreationRequestDTO user);
	
	User save(User user);
}
