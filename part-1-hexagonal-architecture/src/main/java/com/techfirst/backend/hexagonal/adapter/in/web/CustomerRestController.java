package com.techfirst.backend.hexagonal.adapter.in.web;

import com.techfirst.backend.hexagonal.core.domain.Customer;
import com.techfirst.backend.hexagonal.core.port.in.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerUseCase customerUseCase;

    @GetMapping("/api/customers/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerUseCase.getCustomer(id);
    }
}