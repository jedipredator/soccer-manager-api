package com.soccer.manager.facade;

import com.soccer.manager.domain.dto.response.UserResponseDTO;

public interface UserFacade {

	UserResponseDTO getUser(String userId);
}
