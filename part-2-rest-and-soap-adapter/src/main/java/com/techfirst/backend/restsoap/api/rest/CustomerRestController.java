package com.techfirst.backend.restsoap.api.rest;

import com.techfirst.backend.restsoap.domain.Customer;
import com.techfirst.backend.restsoap.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/api/customers/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }
}