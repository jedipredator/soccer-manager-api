package com.soccer.manager.domain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.manager.domain.repo.entity.TransferList;

public interface TransferListRepository extends JpaRepository<TransferList, String> {

	TransferList findByPlayerIdAndActiveOffer(String playerId, boolean activeOffer);
	
	TransferList findByIdAndPlayerId(String id, String playerId);
	
	List<TransferList> findByActiveOffer(boolean activeOffer);
}
