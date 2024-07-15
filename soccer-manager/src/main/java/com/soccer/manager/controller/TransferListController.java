package com.soccer.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.manager.domain.dto.request.TransferListBuyRequestDTO;
import com.soccer.manager.domain.dto.request.TransferListRequestDTO;
import com.soccer.manager.domain.dto.response.TransferListResponseDTO;
import com.soccer.manager.facade.TransferListFacade;

@CrossOrigin
@RestController
public class TransferListController {

	private TransferListFacade transferListFacade;
	
	@Autowired
	public TransferListController(TransferListFacade transferListFacade) {
		this.transferListFacade = transferListFacade;
	}
	
	@GetMapping(value = "/transferlists")
	public ResponseEntity<List<TransferListResponseDTO>> getAllActive() {
		return ResponseEntity.ok(transferListFacade.getAllActive());
	}
	
	@PostMapping(value = "/transferlists")
    public ResponseEntity<TransferListResponseDTO> putPlayerOnTransferList(@RequestHeader("Authorization") String jwtToken,
    																@RequestBody TransferListRequestDTO transferListRequestDto) {
		return ResponseEntity.ok(transferListFacade.putPlayerOnTransferList(jwtToken, transferListRequestDto));
	}
	
	@PostMapping(value = "/transferlists/buy")
    public ResponseEntity<Void> buyPlayerOnTransferList(@RequestHeader("Authorization") String jwtToken,
    																@RequestBody TransferListBuyRequestDTO transferListBuyRequestDTO) {
		transferListFacade.buyPlayerOnTransferList(jwtToken, transferListBuyRequestDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
