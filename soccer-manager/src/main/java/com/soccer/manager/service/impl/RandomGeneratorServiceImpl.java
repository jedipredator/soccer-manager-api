package com.soccer.manager.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soccer.manager.service.RandomGeneratorService;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

	private Random rand = new Random();
	
	@Value("${player.age.min}")
	private int playerAgeMinimum;
	
	@Value("${player.age.max}")
	private int playerAgeMaximum;

	@Override
	public int generatePlayerAge() {
		return playerAgeMinimum + rand.nextInt(playerAgeMaximum - playerAgeMinimum + 1); 
	}

	@Override
	public BigDecimal generatePlayerIncreaseValueFactor() {
		return BigDecimal.valueOf(110L + rand.nextInt(91)).divide(BigDecimal.valueOf(100L),8, RoundingMode.HALF_UP);
	}
}
