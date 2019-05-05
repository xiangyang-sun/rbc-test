package com.johnsun.simplebasket.services;

import java.util.List;

import com.johnsun.simplebasket.model.FruitQuantity;

public class BasketCostService {
	public double calcTotalCost(List<FruitQuantity> fruitQuantityList) {
		return fruitQuantityList.stream()
				.map(f -> f.getQuantity() * f.getFruit().getPrice())
				.reduce(0.0, (x, y) -> x + y);
		
	}

}
