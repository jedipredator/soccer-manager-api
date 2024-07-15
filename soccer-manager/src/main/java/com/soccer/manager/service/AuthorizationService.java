package com.soccer.manager.service;

import com.soccer.manager.domain.dto.request.PlayerUpdateDTO;
import com.soccer.manager.domain.dto.request.TeamUpdateDTO;
import com.soccer.manager.domain.dto.request.TransferListBuyRequestDTO;
import com.soccer.manager.domain.dto.request.TransferListRequestDTO;
import com.soccer.manager.domain.repo.entity.User;

public interface AuthorizationService {
	
	void checkIfRequestIsAllowed(String jwtToken, TeamUpdateDTO teamUpdateDto);

	void checkIfRequestIsAllowed(String jwtToken, PlayerUpdateDTO playerUpdateDto);

	void checkIfRequestIsAllowed(String jwtToken, TransferListRequestDTO transferListRequestDTO);

	User checkIfRequestIsAllowed(String jwtToken, TransferListBuyRequestDTO transferListBuyRequestDto);
}
