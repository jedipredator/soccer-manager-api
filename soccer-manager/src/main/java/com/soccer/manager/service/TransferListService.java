package com.soccer.manager.service;

import java.util.List;

import com.soccer.manager.domain.dto.request.TransferListBuyRequestDTO;
import com.soccer.manager.domain.dto.request.TransferListRequestDTO;
import com.soccer.manager.domain.repo.entity.TransferList;
import com.soccer.manager.domain.repo.entity.User;

public interface TransferListService {

	TransferList putPlayerOnTransferList(TransferListRequestDTO transferListRequestDto);

	void buyPlayerOnTransferList(User user, TransferListBuyRequestDTO transferListBuyRequestDto);

	List<TransferList> getAllActive();
}
