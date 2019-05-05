package com.johnsun.rbc.bootbasket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.johnsun.rbc.bootbasket.model.FruitQuantity;
import com.johnsun.rbc.bootbasket.repository.FruitQuantityRepository;

@Service
public class FruitQuantityService {
	   private static final Logger logger = LoggerFactory.getLogger(FruitQuantityService.class);
	   @Autowired
	   private FruitQuantityRepository fruitQuantityRepository;
	   
	public List<FruitQuantity> getAllFruitQuantities() {
		logger.info("Call getAllFruitQuantities");
		//return fruits;
		List<FruitQuantity> fruitQuantities = new ArrayList<>();
		Iterable<FruitQuantity> f = fruitQuantityRepository.findAll();
		f.forEach(fruitQuantities::add);

		return fruitQuantities;
	}

	public FruitQuantity getFruitQuantity(Integer id) {
		logger.info("Call getFruitQuantity");
		Optional<FruitQuantity> fq = fruitQuantityRepository.findById(id);
		if (fq.isPresent())
			return fq.get();
		else
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "FruitQuantity Not Found");
	}


	
	/*
	 * @return 
	 * 	true - if added
	 *  false - if exist
	 */
	public boolean addFruitQuantity(FruitQuantity fruitQuantity) {
		logger.info("Call addFruitQuantity");
		FruitQuantity savedFruitQuantity = fruitQuantityRepository.save(fruitQuantity);
		if (savedFruitQuantity == null)
			return false;
		else {
			logger.info("saved new fruit: id = {}, fruit name = {}, quantity = {}",
					savedFruitQuantity.getQid(), 
					savedFruitQuantity.getFruit().getName(), 
					savedFruitQuantity.getQuantity());
			return true;
		}
	}

	public void updateFruitQuantity(Integer id, FruitQuantity fruitQuantity) {
		logger.info("Call updateFruitQuantity");
		fruitQuantityRepository.save(fruitQuantity);
	}
	
	public void deleteFruitQuantity(Integer id) {
		logger.info("Call deleteFruitQuantity");
		fruitQuantityRepository.deleteById(id);
	}

	//calculate total cost of all fruits in basket
	//method 1: get all fruits, then calculate the total cost in memory
	public double calcTotalCost1() {
		logger.info("Call calcTotalCost1");
		
		return getAllFruitQuantities().stream()
				.map(f -> f.getQuantity() * f.getFruit().getPrice())
				.reduce(0.0, (x, y) -> x + y);
		
	}
	
	//method 2: Aggregate in database using query
	public double calcTotalCost2() {
		logger.info("Call calcTotalCost2");
		Double total = fruitQuantityRepository.findTotalCost();
		if (total == null)
			return 0;
		else
			return total.doubleValue();
		
	}
	

}
