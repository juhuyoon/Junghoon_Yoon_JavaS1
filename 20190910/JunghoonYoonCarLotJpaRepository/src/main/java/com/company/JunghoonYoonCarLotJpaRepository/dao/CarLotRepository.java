package com.company.JunghoonYoonCarLotJpaRepository.dao;

import com.company.JunghoonYoonCarLotJpaRepository.dto.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarLotRepository extends JpaRepository<Car, Integer> {
    List<Car> findByMake(String Make);
    List<Car> findByColor(String Color);
    List<Car> findByMakeAndColor(String Make, String Color);

}
