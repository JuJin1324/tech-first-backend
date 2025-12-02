package com.techfirst.backend.restsoap.service;

import com.techfirst.backend.restsoap.domain.Customer;

public interface CustomerService {
    Customer getCustomer(Long id);
}