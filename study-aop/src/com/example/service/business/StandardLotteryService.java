package com.example.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import com.example.service.LotteryService;

public class StandardLotteryService implements LotteryService {
	private LotteryService self = this;
	
	public void setSelf(LotteryService self) {
		this.self = self;
	}

	@Override
	public List<Integer> draw(int max, int size) {
		return ThreadLocalRandom.current().ints(1, max).distinct().limit(size).sorted().boxed().toList();
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> self.draw(max, size))
				        .toList();
	}

}
