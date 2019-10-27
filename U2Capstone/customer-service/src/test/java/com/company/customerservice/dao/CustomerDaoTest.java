package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    private CustomerDao dao;

    @Before
    public void setUp() throws Exception {
        dao.getAllCustomers().stream().forEach(
                c -> dao.deleteCustomer(c.getCustomer_id())
        );
    }

    @Test
    public void expectCustomerIdOnCreateCustomer() {
        Customer sample1 = new Customer(
            "Lorem",
            "Ipsum",
            "123 Lorem Ipsum Dr.",
            "Atlanta",
            "30301",
            "loremipsum@email.com",
            "404-123-4567"
        );

        Customer result = dao.createCustomer(sample1);

        assertEquals(sample1.getFirst_name(),result.getFirst_name());
        assertNotNull(result.getCustomer_id());
    }

    @Test
    public void expectCustomerOnReadById() {
        Customer sample1 = new Customer(
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );
        sample1 = dao.createCustomer(sample1);

        Customer checkCustomer = dao.getCustomer(sample1.getCustomer_id());

        assertEquals(sample1, checkCustomer);
    }

    @Test
    public void expectListOfCustomersOnReadAll() {
        Customer sample1 = new Customer(
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );
        sample1 = dao.createCustomer(sample1);
        Customer sample2 = new Customer(
                "Dolor",
                "Sit",
                "123 Dolor Sit Ln.",
                "Atlanta",
                "30301",
                "dolorsit@email.com",
                "404-987-6543"
        );
        sample2 = dao.createCustomer(sample2);

        List<Customer> localList = new ArrayList<>();
        localList.add(sample1);
        localList.add(sample2);

        List<Customer> checkList = dao.getAllCustomers();

        assertEquals(localList, checkList);
    }

    @Test
    public void expectUpdatedCustomerAfterUpdate() {
        Customer sample1 = new Customer(
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );
        sample1 = dao.createCustomer(sample1);

        String updatedValue = "Amet";
        sample1.setLast_name(updatedValue);

        dao.updateCustomer(sample1);

        Customer checkCustomer = dao.getCustomer(sample1.getCustomer_id());

        assertEquals(updatedValue, checkCustomer.getLast_name());
        assertEquals(sample1, checkCustomer);

    }

    @Test
    public void expectNullAfterDelete() {
        Customer sample1 = new Customer(
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );
        sample1 = dao.createCustomer(sample1);

        dao.deleteCustomer(sample1.getCustomer_id());

        assertNull(dao.getCustomer(sample1.getCustomer_id()));
    }
}