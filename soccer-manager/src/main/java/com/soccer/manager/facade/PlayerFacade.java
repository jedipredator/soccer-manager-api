package com.soccer.manager.facade;

import com.soccer.manager.domain.dto.request.PlayerUpdateDTO;
import com.soccer.manager.domain.dto.response.PlayerResponseDTO;

public interface PlayerFacade {

	PlayerResponseDTO updateNameAndCountry(String jwtToken, PlayerUpdateDTO playerUpdateDto);
}
