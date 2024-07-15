package com.soccer.manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RandomGeneratorServiceTest {
	
	@Autowired
	private RandomGeneratorService randomGeneratorService;

	@Test
	void test_generatePlayerAge() {
		
		Set<Integer> ages = new HashSet<>();
		
		for (int i = 0; i < 200_000; i++) {
			ages.add(randomGeneratorService.generatePlayerAge());
		}
		
		assertEquals(40 - 18 + 1, ages.size());
	}
	
	@Test
	void test_generatePlayerIncreaseValueFactor() {
		
		boolean allInRange = true;
		
		for (int i = 0; i < 200_000; i++) {
			allInRange = allInRange && checkRange(randomGeneratorService.generatePlayerIncreaseValueFactor());
		}
		
		assertTrue(allInRange);
	}

	private boolean checkRange(BigDecimal factor) {
		return factor.compareTo(new BigDecimal("1.1")) >= 0
				&& factor.compareTo(new BigDecimal("2.0")) <= 0;
	}
}
