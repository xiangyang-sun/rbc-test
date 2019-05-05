package com.johnsun.rbc.bootbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.johnsun.rbc.bootbasket.model.Fruit;
import com.johnsun.rbc.bootbasket.model.FruitQuantity;
import com.johnsun.rbc.bootbasket.service.FruitQuantityService;
import com.johnsun.rbc.bootbasket.service.FruitService;

@RestController
public class BasketCostController {
	@Autowired
	private FruitQuantityService fruitQuantityService;
	@Autowired
	private FruitService fruitService;
	
	////////////////////////////////////////////////////////
	//Fruit APIs
	////////////////////////////////////////////////////////
	@RequestMapping(value="/fruits", method=RequestMethod.GET)
	public List<Fruit> getAllFruits() {
		return fruitService.getAllFruits();
	}
	
	@RequestMapping(value="/fruits/{id}", method=RequestMethod.GET)
	public Fruit getFruit(@PathVariable Integer id) {
		return fruitService.getFruit(id);
	}
	
	@RequestMapping(value="/fruits", method=RequestMethod.POST)
	public ResponseEntity<String> addFruit(@RequestBody Fruit fruit) {	
		if (fruitService.addFruit(fruit)) {
			return ResponseEntity.ok().build();
		}
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@RequestMapping(value="/fruits/{id}", method=RequestMethod.PUT)
	public void updateFruit(@PathVariable int id, @RequestBody Fruit fruit) {	
		fruitService.updateFruit(id, fruit);
	}
	
	@RequestMapping(value="/fruits/{id}", method=RequestMethod.DELETE)
	public void deleteFruit(@PathVariable int id) {
		 fruitService.deleteFruit(id);
	}

	////////////////////////////////////////////////////////////////
	//Fruit Quantity APIs
	///////////////////////////////////////////////////////////////
	@RequestMapping(value="/fruitquantities", method=RequestMethod.GET)
	public List<FruitQuantity> getAllFruitQuantities() {
		return fruitQuantityService.getAllFruitQuantities();
	}
	
	@RequestMapping(value="/fruitquantities/{id}", method=RequestMethod.GET)
	public FruitQuantity getFruitQuantity(@PathVariable Integer id) {
		return fruitQuantityService.getFruitQuantity(id);
	}
	
	@RequestMapping(value="/fruitquantities", method=RequestMethod.POST)
	public ResponseEntity<String> addFruitQuantity(@RequestBody FruitQuantity fruitQuantity) {	
		if (fruitQuantityService.addFruitQuantity(fruitQuantity)) {
			return ResponseEntity.ok().build();
		}
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@RequestMapping(value="/fruitquantities/{id}", method=RequestMethod.PUT)
	public void updateFruitQuantity(@PathVariable int id, @RequestBody FruitQuantity fruitQuantity) {	
		fruitQuantityService.updateFruitQuantity(id, fruitQuantity);
	}
	
	@RequestMapping(value="/fruitquantities/{id}", method=RequestMethod.DELETE)
	public void deleteFruitQuantity(@PathVariable Integer id) {
		 fruitQuantityService.deleteFruitQuantity(id);
	}

	//API to get total cost
	@RequestMapping(value="/basket-total-cost1", method=RequestMethod.GET)
	public double getBasketTotalCost1() {
		return fruitQuantityService.calcTotalCost1();
	}
	
	//API to get total cost
	@RequestMapping(value="/basket-total-cost2", method=RequestMethod.GET)
	public double getBasketTotalCost2() {
		return fruitQuantityService.calcTotalCost2();
	}
	
}
