package com.eazybytes.springsecurity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eazybytes.springsecurity.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	List<Customer> findByEmail(String email);

}
