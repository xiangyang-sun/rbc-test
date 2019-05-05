package com.johnsun.simplebasket;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.johnsun.simplebasket.model.Fruit;
import com.johnsun.simplebasket.model.FruitQuantity;
import com.johnsun.simplebasket.services.BasketCostService;
import com.opencsv.CSVReader;

public class BasketMain {

	private BasketCostService basketCostService = new BasketCostService();
	
	public static BasketMain createInstance() {
		return new BasketMain();
	}
	
	public static void main(String[] args) {
		BasketMain instanceMain = BasketMain.createInstance();
		List<Fruit> fruitList = new ArrayList<>();
		List<FruitQuantity> fruitQuantityList = new ArrayList<>();
		List<String[]> listStringArray;

		// build fruit objects
		try (CSVReader csvReader = new CSVReader(Files.newBufferedReader(Paths.get("fruit.csv")))) {
			listStringArray = csvReader.readAll();
			for (int i = 1; i < listStringArray.size(); i++) { // skip first line
				String[] str = listStringArray.get(i);
				fruitList.add(new Fruit(Integer.parseInt(str[0]), str[1], Double.parseDouble(str[2])));
			}
			//System.out.println(fruitList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// build fruit quantity objects in basket
		try (CSVReader csvReader = new CSVReader(Files.newBufferedReader(Paths.get("fruit_quantity.csv")))) {
			listStringArray = csvReader.readAll();
			for (int i = 1; i < listStringArray.size(); i++) { // skip first line
				String[] str = listStringArray.get(i);
				fruitQuantityList.add(new FruitQuantity(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
			}
			//System.out.println(fruitQuantityList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Find Fruit object with id, then set it in FruitQuantity object
		fruitQuantityList.forEach(q -> {
			// get Fruit object matching id
			Fruit matchedfruit = fruitList.stream().filter(f -> f.getId() == q.getFruitId()).findFirst().get();
			//System.out.println(matchedfruit);
			q.setFruit(matchedfruit);
		});


		System.out.println("Total cost in the basket is " 
				+ String.format("%.2f", instanceMain.basketCostService.calcTotalCost(fruitQuantityList)));
	}



}
