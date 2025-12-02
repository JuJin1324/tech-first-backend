package com.techfirst.backend.restsoap.api.soap;

import com.techfirst.backend.restsoap.api.soap.dto.GetCustomerRequest;
import com.techfirst.backend.restsoap.api.soap.dto.GetCustomerResponse;
import com.techfirst.backend.restsoap.domain.Customer;
import com.techfirst.backend.restsoap.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CustomerSoapEndpoint {

    private static final String NAMESPACE_URI = "http://techfirst.com/backend/restsoap/soap";
    private final CustomerService customerService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerRequest")
    @ResponsePayload
    public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
        Customer customer = customerService.getCustomer(request.getId());

        GetCustomerResponse response = new GetCustomerResponse();
        response.setId(customer.id());
        response.setName(customer.name());
        response.setEmail(customer.email());

        return response;
    }
}