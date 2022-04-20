package com.example.random.service;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader.Provider;

public interface RandomNumberService {
	int generate(int min, int max);
	
	public static Optional<RandomNumberService> getRandomNumberService(List<Provider<RandomNumberService>> services,QualityLevel qualityLevel) {
		return services.stream()
		             .map(Provider::get)
		             .filter( randomNumberService -> {
		            	 var clazz = randomNumberService.getClass();
		            	 if (clazz.isAnnotationPresent(ServiceQuality.class)) {
		            		 var serviceQuality = clazz.getAnnotation(ServiceQuality.class);
		            		 return qualityLevel.equals(serviceQuality.value());
		            	 }
		            	 return false;
		             })
		             .findFirst();
	}
}
