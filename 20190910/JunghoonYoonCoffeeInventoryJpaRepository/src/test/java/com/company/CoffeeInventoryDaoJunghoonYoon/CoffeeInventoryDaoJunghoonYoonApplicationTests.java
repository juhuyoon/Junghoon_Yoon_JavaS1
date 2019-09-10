package com.company.CoffeeInventoryDaoJunghoonYoon;

import com.company.CoffeeInventoryDaoJunghoonYoon.dao.CoffeeRepository;
import com.company.CoffeeInventoryDaoJunghoonYoon.dao.RoasterRepository;
import com.company.CoffeeInventoryDaoJunghoonYoon.dto.Coffee;
import com.company.CoffeeInventoryDaoJunghoonYoon.dto.Roaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoffeeInventoryDaoJunghoonYoonApplicationTests {

	@Autowired
	CoffeeRepository coffeeRepository;
	@Autowired
	RoasterRepository roasterRepository;
	@Test
	public void contextLoads() {
	}

	@Test
	public void createTest() {
		Roaster roaster = new Roaster();
		roaster.setCity("Savannah");
		roaster.setEmail("totallyrealemail@perc.com");
		roaster.setName("PERC");
		roaster.setNote("Delicious coffee.");
		roaster.setPhone("912-555-5555");
		roaster.setPostal_code("31401");
		roaster.setState("GA");
		roaster.setStreet("Broad St");

		roasterRepository.save(roaster);

		Coffee coffee = new Coffee();
		coffee.setRoasterId(roaster.getRoasterId());
		coffee.setName("Juggernaut");
		coffee.setCount(73);
		coffee.setUnit_price(12.99);
		coffee.setDescription("Bold and full flavored, this coffee will start your day right.");
		coffee.setType("Espresso");

		coffeeRepository.save(coffee);

		List<Coffee> cList = coffeeRepository.findAll();
		assertEquals(1, cList.size());

//		List<Coffee> cList2 = coffeeRepository.findByRoaster_Id(roaster.getRoaster_id());
//		assertEquals(1, cList2.size());

//		List<Coffee> cList3 = coffeeRepository.findByType("Espresso");
//		assertEquals(1, cList3.size());

//		List<Coffee> cList4 = coffeeRepository.findByRoasterAndType(roaster.getRoaster_id(), "Espresso");
//		assertEquals(1, cList4.size());

	}

}
