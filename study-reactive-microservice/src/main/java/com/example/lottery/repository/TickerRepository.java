package com.example.lottery.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.lottery.document.TickerDocument;

import reactor.core.publisher.Flux;

public interface TickerRepository extends ReactiveMongoRepository<TickerDocument, String>{
	
	Flux<TickerDocument> findAllByPriceBetween(double begin,double end);
}
