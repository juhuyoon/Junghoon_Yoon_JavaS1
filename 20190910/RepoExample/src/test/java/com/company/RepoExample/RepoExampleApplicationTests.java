package com.company.RepoExample;

import com.company.RepoExample.dao.CustomerDAO;
import com.company.RepoExample.dao.NoteDAO;
import com.company.RepoExample.dto.Customer;
import com.company.RepoExample.dto.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoExampleApplicationTests {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private NoteDAO noteDAO;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createTest() {
		customerDAO.deleteAll();

		Customer customer = new Customer();

		customer.setFirstName("Jung");
		customer.setLastName("Yoon");
		customer.setPhone("111-223-3333");
		customer.setCompany("YEET");
		customerDAO.save(customer);

		List<Customer> customerList = customerDAO.findAll();
		assertEquals(1, customerList.size());

		Note note = new Note();
		note.setContent("Working on Coding");
		note.setCustomerId(customer.getId());

		Note note2 = new Note();
		note2.setContent("Another note for Coding");
		note2.setCustomerId(customer.getId());

		noteDAO.save(note);
		noteDAO.save(note2);

		List<Customer> customerList1 = customerDAO.findAll();
		assertEquals(1, customerList1.size());

		Set<Note> noteSet = customerList1.get(0).getNotes();
		assertEquals(2, noteSet.size());
	}

	@Test
	public void findCustomerByLastNameAndCompany() {
		customerDAO.deleteAll();

		Customer customer = new Customer();

		customer.setFirstName("Jung");
		customer.setLastName("Yoon");
		customer.setPhone("111-223-3333");
		customer.setCompany("YEET");
		customerDAO.save(customer);

		List<Customer> cList1 = customerDAO.findByLastNameAndCompany("Yoon", "YEET");
		assertEquals(1, cList1.size());
	}

}
