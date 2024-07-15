package com.soccer.manager.domain.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.soccer.manager.domain.repo.entity.TransferList;

@DataJpaTest
class TransferListRepositoryTest {

	@Autowired TransferListRepository transferListRepository;
	
	@Test
    void testRepository() {
		var transferList = new TransferList();
		
        transferList.setActiveOffer(true);
        transferList.setPrice(BigDecimal.valueOf(2_000_000L));
		
        transferListRepository.save(transferList);
         
        assertNotNull(transferList.getId());
    }
}
