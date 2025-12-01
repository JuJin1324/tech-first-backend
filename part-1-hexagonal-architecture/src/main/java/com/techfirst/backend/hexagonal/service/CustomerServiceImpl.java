package com.techfirst.backend.hexagonal.service;

import com.techfirst.backend.hexagonal.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomer(Long id) {
        // Mock implementation
        return new Customer(id, "Tech-First User", "user@example.com");
    }
}