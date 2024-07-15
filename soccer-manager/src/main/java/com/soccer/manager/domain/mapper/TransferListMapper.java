package com.soccer.manager.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.soccer.manager.domain.dto.request.TransferListRequestDTO;
import com.soccer.manager.domain.dto.response.TransferListResponseDTO;
import com.soccer.manager.domain.repo.entity.TransferList;

@Mapper(componentModel = "spring")
public interface TransferListMapper {

	@Mapping(target = "playerId", source = "player.id")
	TransferListResponseDTO mapToTransferListResponseDTO(TransferList transferList);
	
	@Mapping(target = "player.id", source = "playerId")
	@Mapping(target = "activeOffer", expression = ("java(true)"))
	TransferList mapToTransferList(TransferListRequestDTO transferListRequestDto);
}
