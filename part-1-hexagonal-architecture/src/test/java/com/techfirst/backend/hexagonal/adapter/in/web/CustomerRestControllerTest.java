package com.techfirst.backend.hexagonal.adapter.in.web;

import com.techfirst.backend.hexagonal.core.domain.Customer;
import com.techfirst.backend.hexagonal.core.port.in.CustomerUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerRestController.class)
class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerUseCase customerUseCase;

    @Test
    void getCustomer_ShouldReturnCustomerJson() throws Exception {
        // given
        long customerId = 1L;
        var customer = new Customer(customerId, "Tech First", "tech@first.com");

        given(customerUseCase.getCustomer(customerId)).willReturn(customer);

        // when & then
        mockMvc.perform(get("/api/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId))
                .andExpect(jsonPath("$.name").value("Tech First"))
                .andExpect(jsonPath("$.email").value("tech@first.com"))
                .andDo(print());
    }
}
