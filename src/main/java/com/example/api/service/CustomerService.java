package com.example.api.service;


import com.example.api.domain.Customer;
import com.example.api.dto.CustomerDTO;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<CustomerDTO> findAll(Pageable pageable) {
		List<CustomerDTO> list = repository.findAll(pageable).stream().map(CustomerDTO::create).collect(Collectors.toList());

		//List<CustomerDTO> list = repository.findAllByOrderByNameAsc().stream().map(CustomerDTO::create).collect(Collectors.toList());
		//return repository.findAllByOrderByNameAsc();
		return list;
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public Customer insert(Customer customer) {
		
		Assert.isNull(customer.getId(), "Não foi possivel Criar novo Cliente");
		
		return repository.save(customer);
	}
	
	public Customer update(Customer customer, Long id) {
				
		Optional<Customer> optional = repository.findById(id);
		
		if (optional.isPresent()) {
			Customer db = optional.get();
			
			db.setName(customer.getName());
			db.setEmail(customer.getEmail());
			System.out.println("Customer id" + db.getId());
			
			repository.save(db);
			
			return db;
		}else {
			throw new RuntimeException("Não foi possivel Atualizar o registro"); 
		}	
	}
		
	public void delete(Long id) {
			repository.deleteById(id);
	}

}
