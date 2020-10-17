package com.example.api.dto;

import com.example.api.domain.Customer;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;

    /*public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.nome = customer.getNome();
        this.email = customer.getEmail();
    }*/

    public static CustomerDTO create(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
