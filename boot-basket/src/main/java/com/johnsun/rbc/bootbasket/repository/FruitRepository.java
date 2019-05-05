package com.johnsun.rbc.bootbasket.repository;

import org.springframework.data.repository.CrudRepository;

import com.johnsun.rbc.bootbasket.model.Fruit;

public interface FruitRepository extends CrudRepository<Fruit, Integer> {

}
