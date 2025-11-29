package com.techfirst.backend.hexagonal.core.port.in;

import com.techfirst.backend.hexagonal.core.domain.Customer;

public interface CustomerUseCase {
    Customer getCustomer(Long id);
}