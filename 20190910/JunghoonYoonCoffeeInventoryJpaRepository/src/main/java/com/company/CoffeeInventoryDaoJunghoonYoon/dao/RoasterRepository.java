package com.company.CoffeeInventoryDaoJunghoonYoon.dao;

import com.company.CoffeeInventoryDaoJunghoonYoon.dto.Roaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoasterRepository extends JpaRepository<Roaster, Integer> {
}
