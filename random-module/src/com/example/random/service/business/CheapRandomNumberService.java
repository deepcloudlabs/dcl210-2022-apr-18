package com.example.random.service.business;

import java.util.concurrent.ThreadLocalRandom;

import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberService;
import com.example.random.service.ServiceQuality;

@ServiceQuality(value=QualityLevel.CHEAP)
public class CheapRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("CheapRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
