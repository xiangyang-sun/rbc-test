package com.johnsun.rbc.bootbasket.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.johnsun.rbc.bootbasket.model.FruitQuantity;

public interface FruitQuantityRepository extends CrudRepository<FruitQuantity, Integer> {
    @Query(value = "select sum(f.price * fq.quantity) from fruit f, fruit_quantity fq where f.id =fq.fruit_id", nativeQuery = true)
    public Double findTotalCost();
}


