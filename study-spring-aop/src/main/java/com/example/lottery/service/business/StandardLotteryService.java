package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.lottery.aspect.Audited;
import com.example.lottery.aspect.Profiled;
import com.example.lottery.service.LotteryService;

@Service
@Profiled
public class StandardLotteryService implements LotteryService {
	
	@Override
	@Audited
	public List<Integer> draw(int max, int size) {
		return ThreadLocalRandom.current().ints(1, max).distinct().limit(size).sorted().boxed().toList();
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> this.draw(max, size))
				        .toList();
	}

}
