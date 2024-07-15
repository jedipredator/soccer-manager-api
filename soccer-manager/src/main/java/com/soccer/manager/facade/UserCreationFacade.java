package com.soccer.manager.facade;

import com.soccer.manager.domain.dto.request.UserCreationRequestDTO;
import com.soccer.manager.domain.dto.response.UserCreationResponseDTO;

public interface UserCreationFacade {

	UserCreationResponseDTO create(UserCreationRequestDTO userRequestDto);
}
