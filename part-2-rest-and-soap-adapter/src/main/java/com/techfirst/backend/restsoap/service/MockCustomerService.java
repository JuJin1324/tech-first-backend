package com.techfirst.backend.restsoap.service;

import com.techfirst.backend.restsoap.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class MockCustomerService implements CustomerService {

    @Override
    public Customer getCustomer(Long id) {
        // Mock implementation
        return new Customer(id, "Tech-First User", "user@example.com");
    }
}
