package com.soccer.manager.facade;

import java.util.List;

import com.soccer.manager.domain.dto.request.TransferListBuyRequestDTO;
import com.soccer.manager.domain.dto.request.TransferListRequestDTO;
import com.soccer.manager.domain.dto.response.TransferListResponseDTO;

public interface TransferListFacade {

	TransferListResponseDTO putPlayerOnTransferList(String jwtToken, TransferListRequestDTO transferListRequestDto);

	void buyPlayerOnTransferList(String jwtToken, TransferListBuyRequestDTO transferListBuyRequestDto);

	List<TransferListResponseDTO> getAllActive();
}
