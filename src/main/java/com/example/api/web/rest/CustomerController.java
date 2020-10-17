package com.example.api.web.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}
	
	@PostMapping
	public ResponseEntity post(@RequestBody Customer customer) {
		Customer c = service.insert(customer);

		URI location = getUri(c.getId());

		return ResponseEntity.created(location).build();
		//return "Novo cliente criado com successo!" + c.getId()
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Customer customer ) {
		Customer c = service.update(customer, id);
		
		return "Novo cliente atualizado com successo!" + c.getId();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return ResponseEntity.ok().build();
		
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}
	
}
