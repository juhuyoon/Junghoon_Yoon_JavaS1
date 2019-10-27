package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import com.company.customerservice.model.CustomerViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {


    private CustomerDao customerDao;
    private ServiceLayer serviceLayer;

    private ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    private ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        serviceLayer = new ServiceLayer(customerDao);
    }

    private void setUpCustomerDaoMock(){
        customerDao = mock(CustomerDao.class);

        CustomerViewModel cvmNew = new CustomerViewModel(
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        CustomerViewModel cvmNew2 = new CustomerViewModel(
                "Dolor",
                "Sit",
                "123 Dolor Sit Ln.",
                "Atlanta",
                "30301",
                "dolorsit@email.com",
                "404-987-6543"
        );

        Customer customerNew = new Customer(
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        Customer customerNew2 = new Customer(
                "Dolor",
                "Sit",
                "123 Dolor Sit Ln.",
                "Atlanta",
                "30301",
                "dolorsit@email.com",
                "404-987-6543"
        );

        CustomerViewModel cvmSaved = new CustomerViewModel(
                1,
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        CustomerViewModel cvmSaved2 = new CustomerViewModel(
                2,
                "Dolor",
                "Sit",
                "123 Dolor Sit Ln.",
                "Atlanta",
                "30301",
                "dolorsit@email.com",
                "404-987-6543"
        );

        Customer customerSaved = new Customer(
                1,
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        Customer customerSaved2 = new Customer(
                2,
                "Dolor",
                "Sit",
                "123 Dolor Sit Ln.",
                "Atlanta",
                "30301",
                "dolorsit@email.com",
                "404-987-6543"
        );

        List<Customer> customerToSave = new ArrayList<>();
        customerToSave.add(customerSaved);
        customerToSave.add(customerSaved2);

        List<CustomerViewModel> cvmToSave = new ArrayList<>();
        cvmToSave.add(cvmSaved);
        cvmToSave.add(cvmSaved2);

        doReturn(customerSaved).when(customerDao).createCustomer(customerNew);
        doReturn(customerSaved2).when(customerDao).createCustomer(customerNew2);
        doReturn(customerSaved).when(customerDao).getCustomer(1);
        doReturn(customerSaved2).when(customerDao).getCustomer(2);
        doReturn(customerToSave).when(customerDao).getAllCustomers();
        doNothing().when(customerDao).updateCustomer(customerArgumentCaptor.capture());
        doNothing().when(customerDao).deleteCustomer(integerArgumentCaptor.capture());
    }

    @Test
    public void expectCvmWithIdOnAdd() {
        CustomerViewModel cvmNew = new CustomerViewModel(
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        CustomerViewModel cvmSaved = new CustomerViewModel(
                1,
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        CustomerViewModel checkCvm = serviceLayer.addCustomer(cvmNew);

        assertEquals(cvmSaved, checkCvm);

    }

    @Test
    public void expectCvmOnFindById() {
        CustomerViewModel cvmSaved = new CustomerViewModel(
                1,
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        CustomerViewModel checkCvm = serviceLayer.findCustomer(1);

        assertEquals(cvmSaved, checkCvm);
    }

    @Test
    public void findAllCustomers() {
        CustomerViewModel cvmSaved = new CustomerViewModel(
                1,
                "Lorem",
                "Ipsum",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        CustomerViewModel cvmSaved2 = new CustomerViewModel(
                2,
                "Dolor",
                "Sit",
                "123 Dolor Sit Ln.",
                "Atlanta",
                "30301",
                "dolorsit@email.com",
                "404-987-6543"
        );

        List<CustomerViewModel> cvmExpected = new ArrayList<>();
        cvmExpected.add(cvmSaved);
        cvmExpected.add(cvmSaved2);

        List<CustomerViewModel> checkList = serviceLayer.findAllCustomers();

        assertEquals(cvmExpected, checkList);
    }

    @Test
    public void updateCustomer() {
        CustomerViewModel cvmUpdate = new CustomerViewModel(
                1,
                "John",
                "Smith",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        Customer customerUpdate = new Customer(
                1,
                "John",
                "Smith",
                "123 Lorem Ipsum Dr.",
                "Atlanta",
                "30301",
                "loremipsum@email.com",
                "404-123-4567"
        );

        serviceLayer.updateCustomer(cvmUpdate);

        verify(customerDao,times(1)).updateCustomer(customerUpdate);
        assertEquals(customerUpdate,customerArgumentCaptor.getValue());
    }

    @Test
    public void removeCustomer() {

        serviceLayer.removeCustomer(1);
        int argCap = integerArgumentCaptor.getValue();

        verify(customerDao, times(1)).deleteCustomer(1);
        assertEquals(1,argCap);
    }
}