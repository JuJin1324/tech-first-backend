package com.techfirst.backend.hexagonal.service;

import com.techfirst.backend.hexagonal.domain.Customer;

public interface CustomerService {
    Customer getCustomer(Long id);
}