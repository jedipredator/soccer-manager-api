package com.soccer.manager.service;

import com.soccer.manager.domain.dto.request.PlayerUpdateDTO;
import com.soccer.manager.domain.repo.entity.Player;

public interface PlayerService {

	Player updateNameAndCountry(PlayerUpdateDTO playerUpdateDto);
}
