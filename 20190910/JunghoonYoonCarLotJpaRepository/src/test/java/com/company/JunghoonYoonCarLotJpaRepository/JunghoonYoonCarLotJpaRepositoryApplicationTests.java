package com.company.JunghoonYoonCarLotJpaRepository;

import com.company.JunghoonYoonCarLotJpaRepository.dao.CarLotRepository;
import com.company.JunghoonYoonCarLotJpaRepository.dto.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JunghoonYoonCarLotJpaRepositoryApplicationTests {

	@Autowired
	CarLotRepository carLotRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createTest(){
		carLotRepository.deleteAll();

		Car car = new Car();
		car.setMake("Honda");
		car.setModel("CRV");
		car.setYear("2019");
		car.setColor("Brown");
		carLotRepository.save(car);

		car = new Car();
		car.setMake("Toyota");
		car.setModel("RAV");
		car.setYear("2011");
		car.setColor("Gray");
		carLotRepository.save(car);

		List<Car> cList = carLotRepository.findAll();
		assertEquals(1, cList.size());

		List<Car> cList2 = carLotRepository.findByColor("Brown");
		assertEquals(1, cList2.size());

		List<Car> cList3 = carLotRepository.findByMake("Toyota");
		assertEquals(1, cList3.size());

		List<Car> cList4 = carLotRepository.findByMakeAndColor("Honda", "Brown");
		assertEquals(1, cList4.size());
	}

}
