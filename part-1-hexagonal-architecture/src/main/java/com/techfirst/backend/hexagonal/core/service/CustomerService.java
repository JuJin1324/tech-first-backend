package com.techfirst.backend.hexagonal.core.service;

import com.techfirst.backend.hexagonal.core.domain.Customer;
import com.techfirst.backend.hexagonal.core.port.in.CustomerUseCase;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerUseCase {

    @Override
    public Customer getCustomer(Long id) {
        // Mock implementation
        return new Customer(id, "Tech-First User", "user@example.com");
    }
}