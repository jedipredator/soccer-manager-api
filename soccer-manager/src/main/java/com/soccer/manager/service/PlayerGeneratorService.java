package com.soccer.manager.service;

import com.soccer.manager.domain.repo.entity.Player;
import com.soccer.manager.domain.repo.entity.PlayerType;

public interface PlayerGeneratorService {

	Player generatePlayer(PlayerType playerType);
}
