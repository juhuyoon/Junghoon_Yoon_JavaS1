package com.company.CoffeeInventoryDaoJunghoonYoon.dao;

import com.company.CoffeeInventoryDaoJunghoonYoon.dto.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {

//    List<Coffee> findByRoaster_Id(int roaster_id);
    List<Coffee> findByType(String Type);
//    List<Coffee> findByRoasterAndType(int roaster_id, String Type);
}
