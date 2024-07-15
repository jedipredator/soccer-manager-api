package com.soccer.manager.domain.mapper;

import org.mapstruct.Mapper;

import com.soccer.manager.domain.dto.response.PlayerResponseDTO;
import com.soccer.manager.domain.repo.entity.Player;

@Mapper(componentModel = "spring",  uses = { TransferListMapper.class })
public interface PlayerMapper {

	PlayerResponseDTO mapToPlayerResponseDto(Player player);
}
