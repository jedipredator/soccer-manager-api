package com.soccer.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.manager.domain.dto.request.TransferListBuyRequestDTO;
import com.soccer.manager.domain.dto.request.TransferListRequestDTO;
import com.soccer.manager.domain.mapper.TransferListMapper;
import com.soccer.manager.domain.repo.TransferListRepository;
import com.soccer.manager.domain.repo.entity.TransferList;
import com.soccer.manager.domain.repo.entity.User;
import com.soccer.manager.service.RandomGeneratorService;
import com.soccer.manager.service.TeamService;
import com.soccer.manager.service.TransferListService;

@Service
public class TransferListServiceImpl implements TransferListService {

	private TransferListRepository transferListRepository;
	private TransferListMapper transferListMapper;
	private TeamService teamService;
	private RandomGeneratorService randomGeneratorService;
	
	@Autowired
	public TransferListServiceImpl(TransferListRepository transferListRepository, TransferListMapper transferListMapper,
									TeamService teamService, RandomGeneratorService randomGeneratorService) {
		this.transferListRepository = transferListRepository;
		this.transferListMapper = transferListMapper;
		this.teamService = teamService;
		this.randomGeneratorService = randomGeneratorService;
	}
	
	@Override
	public TransferList putPlayerOnTransferList(TransferListRequestDTO transferListRequestDto) {
		var transferListItem = transferListMapper.mapToTransferList(transferListRequestDto);
		var activeTransferListItem =
				transferListRepository.findByPlayerIdAndActiveOffer(transferListRequestDto.getPlayerId(), true);
		
		if (activeTransferListItem != null) { // if active offer for player already exists, then update it, don't create new
			transferListItem.setId(activeTransferListItem.getId());
		}
		
		return transferListRepository.save(transferListItem);
	}

	@Override
	public void buyPlayerOnTransferList(User user, TransferListBuyRequestDTO transferListBuyRequestDto) {
		var transferList = transferListRepository.findByIdAndPlayerId(transferListBuyRequestDto.getId(), transferListBuyRequestDto.getPlayerId());
		
		if (transferList == null) {
			throw new RuntimeException("Wrong combination of transfer list id and player id: " + transferListBuyRequestDto.getId()
											+ ", " + transferListBuyRequestDto.getPlayerId());
		}
		
		if (!transferList.isActiveOffer()) {
			throw new RuntimeException("Transfer list offer is not active, transfer list id: " + transferListBuyRequestDto.getId());
		}
		
		var userBudget = user.getTeam().getBudgetAmount();
		
		if (userBudget.compareTo(transferList.getPrice()) < 0) {
			throw new RuntimeException("Unsufficient funds of user " + user.getId()
										 + " when buying transfer list id " + transferList.getId());
		}
		
		// Otherwise, do transfer
		user.getTeam().setBudgetAmount(userBudget.subtract(transferList.getPrice()));
		
		var budgetOfTeamWhichSellsPlayer = transferList.getPlayer().getTeam().getBudgetAmount();
		
		var player = transferList.getPlayer();
		
		player.getTeam().setBudgetAmount(budgetOfTeamWhichSellsPlayer.add(transferList.getPrice()));
		
		player.setValue(player.getValue().multiply(randomGeneratorService.generatePlayerIncreaseValueFactor()));
		
		player.setTeam(user.getTeam());
		
		transferList.setActiveOffer(false);
		
		teamService.save(user.getTeam());
		teamService.save(transferList.getPlayer().getTeam());
	}

	@Override
	public List<TransferList> getAllActive() {
		return transferListRepository.findByActiveOffer(true);
	}
}
