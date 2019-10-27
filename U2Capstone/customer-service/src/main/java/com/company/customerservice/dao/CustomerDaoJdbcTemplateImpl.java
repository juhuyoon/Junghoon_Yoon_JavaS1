package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao{

    //prepared statements
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer (first_name, last_name, street, city, zip, email, phone) VALUE (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER_SQL = "SELECT * FROM customer WHERE customer_id = ?";
    private static final String SELECT_ALL_CUSTOMERS_SQL = "SELECT * FROM customer";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET first_name = ?, last_name = ?, street = ?, city = ?, zip = ?, email = ?, phone = ? WHERE customer_id = ?";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customer WHERE customer_id = ?";

    //properties
    private JdbcTemplate jdbcTemplate;

    //constructor
    @Autowired
    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //method implementation
    @Override
    public Customer createCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL, customer.getFirst_name(), customer.getLast_name(), customer.getStreet(), customer.getCity(), customer.getZip(), customer.getEmail(), customer.getPhone());
        int id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
        customer.setCustomer_id(id);
        return customer;
    }

    @Override
    public Customer getCustomer(int customer_id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this::mapRowToCustomer, customer_id);
        } catch (EmptyResultDataAccessException e){ return null;}
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, this::mapRowToCustomer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL, customer.getFirst_name(), customer.getLast_name(), customer.getStreet(), customer.getCity(), customer.getZip(), customer.getEmail(), customer.getPhone(), customer.getCustomer_id());
    }

    @Override
    public void deleteCustomer(int customer_id) {
        jdbcTemplate.update(DELETE_CUSTOMER_SQL, customer_id);
    }

    //row mapper
    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException{
        Customer customer = new Customer(
                rs.getInt("customer_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("street"),
                rs.getString("city"),
                rs.getString("zip"),
                rs.getString("email"),
                rs.getString("phone")
        );
        return customer;
    }

}
