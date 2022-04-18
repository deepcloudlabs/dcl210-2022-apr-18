package com.example.model;

import java.util.List;

import com.example.service.RandomNumber;

//LotteryViewModel.java -- javac --> LotteryViewModel.class -- jvm:class loader --> PermGen/MetaSpace 
public class LotteryViewModel {
	@RandomNumber(min = 10, max = 20, size = 8, distinct = false, sorted = true)
	private List<Integer> numbers;

	public LotteryViewModel() {
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

}
