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

import com.johnsun.rbc.bootbasket.model.Fruit;
import com.johnsun.rbc.bootbasket.repository.FruitRepository;



@Service
public class FruitService {
	   private static final Logger logger = LoggerFactory.getLogger(FruitService.class);
	   @Autowired
	   private FruitRepository fruitRepository;

	
	public List<Fruit> getAllFruits() {
		logger.info("Call getAllFruits");
		//return fruits;
		List<Fruit> fruits = new ArrayList<>();
		Iterable<Fruit> f = fruitRepository.findAll();
		f.forEach(fruits::add);
		
		return fruits;
	}

	public Fruit getFruit(Integer id) {
		logger.info("Call getFruit");
		Optional<Fruit> f = fruitRepository.findById(id);
		if (f.isPresent())
			return f.get();
		else
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Fruit Not Found");

	}

	/*
	 * @return 
	 * 	true - if added
	 *  false - if exist
	 */
	public boolean addFruit(Fruit fruit) {
		logger.info("Call addFruit");
		Fruit savedFruit = fruitRepository.save(fruit);
		if (savedFruit == null)
			return false;
		else {
			logger.info("saved new fruit: id = {}, name = {}, price = {}",
					savedFruit.getId(), savedFruit.getName(), savedFruit.getPrice());
			return true;
		}
	}

	public void updateFruit(Integer id, Fruit fruit) {
		logger.info("Call updateFruit");
		fruitRepository.save(fruit);
	}
	
	public void deleteFruit(Integer id) {
		logger.info("Call deleteFruit");
		fruitRepository.deleteById(id);
	}

}
