package com.techfirst.backend.hexagonal.adapter.in.soap;

import com.techfirst.backend.hexagonal.adapter.in.soap.dto.GetCustomerRequest;
import com.techfirst.backend.hexagonal.adapter.in.soap.dto.GetCustomerResponse;
import com.techfirst.backend.hexagonal.core.domain.Customer;
import com.techfirst.backend.hexagonal.core.port.in.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CustomerSoapEndpoint {

    private static final String NAMESPACE_URI = "http://techfirst.com/backend/hexagonal/soap";
    private final CustomerUseCase customerUseCase;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerRequest")
    @ResponsePayload
    public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
        Customer customer = customerUseCase.getCustomer(request.getId());

        GetCustomerResponse response = new GetCustomerResponse();
        response.setId(customer.id());
        response.setName(customer.name());
        response.setEmail(customer.email());

        return response;
    }
}