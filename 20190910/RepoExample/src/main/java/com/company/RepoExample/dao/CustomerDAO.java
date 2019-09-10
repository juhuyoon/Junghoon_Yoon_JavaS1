package com.company.RepoExample.dao;

import com.company.RepoExample.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//JpaRepository<Table Name, Primary Key Type>
public interface CustomerDAO extends JpaRepository<Customer, Integer>{
    List<Customer> findByLastNameAndCompany(String lastName, String company);
}

