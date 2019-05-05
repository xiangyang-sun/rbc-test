package com.johnsun.rbc.bootbasket;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnsun.rbc.bootbasket.repository.FruitQuantityRepository;
import com.johnsun.rbc.bootbasket.repository.FruitRepository;
import com.johnsun.rbc.bootbasket.service.FruitQuantityService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional 
public class BootBasketApplicationTests {
 
	@Resource
    private FruitRepository fruitRepository;

	@Resource
    private FruitQuantityRepository fruitQuantityRepository;
 
    @Autowired
    private FruitQuantityService fruitQuantityService;
    
    // write test cases here
 
	@Test
	public void thenTotalCost() {
	    // given	
		/*
		fruitRepository.save(new Fruit(1, "Orange", 1.00));
		fruitRepository.save(new Fruit(2, "Banana", 1.50));
		fruitRepository.save(new Fruit(3, "Apple", 2.00));
		fruitRepository.save(new Fruit(4, "Lemon", 2.50));
		fruitRepository.save(new Fruit(5, "Peach", 3.00));

	    FruitQuantity fq = new FruitQuantity();
	    fq.setQid(1000001);
	    fq.setFruit(new Fruit(1, "", 0.0));
	    fq.setQuantity(1);
	    fruitQuantityRepository.save(fq);
	    
	    fq.setQid(1000002);
	    fq.setFruit(new Fruit(2, "", 0.0));
	    fq.setQuantity(2);
	    fruitQuantityRepository.save(fq);
	    
	    fq.setQid(1000003);
	    fq.setFruit(new Fruit(5, "", 0.0));
	    fq.setQuantity(3);   
	    fruitQuantityRepository.save(fq);
	    */
 
	    // when
	    double cost1 = fruitQuantityService.calcTotalCost1();
	 
	    // then
	    assertEquals(13, cost1, 0.001);
	    
	    //when
	    double cost2 = fruitQuantityService.calcTotalCost2();

	    // then
	    assertEquals(13, cost2, 0.001);
	    
	}
}
