package com.soccer.manager.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.soccer.manager.domain.dto.request.TransferListBuyRequestDTO;
import com.soccer.manager.domain.dto.request.TransferListRequestDTO;
import com.soccer.manager.domain.dto.response.TransferListResponseDTO;
import com.soccer.manager.domain.mapper.TransferListMapper;
import com.soccer.manager.facade.TransferListFacade;
import com.soccer.manager.service.AuthorizationService;
import com.soccer.manager.service.SynchronizationObjectsService;
import com.soccer.manager.service.TransferListService;

@Component
@Transactional
public class TransferListFacadeImpl implements TransferListFacade {

	private AuthorizationService authorizationService;
	private TransferListService transferListService;
	private TransferListMapper transferListMapper;
	private SynchronizationObjectsService synchronizationObjectsService;
	
	@Autowired
	public TransferListFacadeImpl(AuthorizationService authorizationService, TransferListService transferListService,
									TransferListMapper transferListMapper, SynchronizationObjectsService synchronizationObjectsService) {
		this.authorizationService = authorizationService;
		this.transferListService = transferListService;
		this.transferListMapper = transferListMapper;
		this.synchronizationObjectsService = synchronizationObjectsService;
	}
	
	@Override
	public TransferListResponseDTO putPlayerOnTransferList(String jwtToken, TransferListRequestDTO transferListRequestDto) {
		synchronized (synchronizationObjectsService.get(transferListRequestDto.getPlayerId())) {
			authorizationService.checkIfRequestIsAllowed(jwtToken, transferListRequestDto);
			return transferListMapper.mapToTransferListResponseDTO(transferListService.putPlayerOnTransferList(transferListRequestDto));
		}
	}

	@Override
	public void buyPlayerOnTransferList(String jwtToken, TransferListBuyRequestDTO transferListBuyRequestDto) {
		synchronized (synchronizationObjectsService.get(transferListBuyRequestDto.getPlayerId())) {
			var user = authorizationService.checkIfRequestIsAllowed(jwtToken, transferListBuyRequestDto);
			transferListService.buyPlayerOnTransferList(user, transferListBuyRequestDto);
		}
	}

	@Override
	public List<TransferListResponseDTO> getAllActive() {
		return transferListService.getAllActive().stream()
				.map(transferListMapper::mapToTransferListResponseDTO)
				.collect(Collectors.toList());
	}
}
