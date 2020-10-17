package com.example.api;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTests {
	@Autowired
	private CustomerService customerService;

	@Test
	public void should_insert_dado() {
		Customer customer = new Customer();

        customer.setName("Cinco");
        customer.setEmail("cinco@email.com");

      	Customer insertDado = customerService.insert(customer);

		Assertions.assertNotNull(insertDado);

	}

	@Test
	public void should_id_not_null(){
		Customer customer = new Customer();

		customer.setName("Identification");
		customer.setEmail("Identification@email.com");

		Customer insertDado = customerService.insert(customer);

		Long id = insertDado.getId();
		Assertions.assertNotNull(id);

	}

	@Test
	public void should_dado_optional() {
		Customer customer = new Customer();

		customer.setName("Cinco");
		customer.setEmail("cinco@email.com");

		Customer insertDado = customerService.insert(customer);

		Long id = insertDado.getId();

		Optional<Customer> optional = customerService.findById(id);
		Assertions.assertTrue(optional.isPresent());

		customer = optional.get();
		Assertions.assertEquals("Cinco",customer.getName());
		Assertions.assertEquals("cinco@email.com",customer.getEmail());
	}

	@Test
	public void should_delete_dado() {
		Customer customer = new Customer();

		customer.setName("Cinco");
		customer.setEmail("cinco@email.com");

		Customer insertDado = customerService.insert(customer);

		Long id = insertDado.getId();

		Optional<Customer> optional = customerService.findById(id);
		Assertions.assertTrue(optional.isPresent());

		customerService.delete(id);

		Assertions.assertFalse(customerService.findById(id).isPresent());
	}

	@Test
	public void should_list_dado() {

		List<Customer> customers = customerService.findAll();

		Assertions.assertEquals(3, customers.size());

	}

	@Test
	public void should_get_dado_by_id() {

		Optional<Customer> optional = customerService.findById(1L);

		Assertions.assertTrue(optional.isPresent());

		Customer customer = optional.get();

		Assertions.assertEquals("Mariazinha",customer.getName());
		Assertions.assertEquals("mariazinha@email.com",customer.getEmail());

	}


}
