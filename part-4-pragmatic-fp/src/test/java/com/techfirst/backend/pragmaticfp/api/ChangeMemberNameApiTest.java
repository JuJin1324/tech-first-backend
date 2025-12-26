package com.techfirst.backend.pragmaticfp.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("회원 이름 변경 API (PUT /members/{id}/name)")
class ChangeMemberNameApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[Happy Path] 유효한 이름으로 변경 요청 시 200 OK와 변경된 정보를 반환한다")
    void happyPath() throws Exception {
        mockMvc.perform(put("/members/1/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Charlie\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Charlie"))
                .andExpect(jsonPath("$.age").value(30)); // Mock 데이터 기준
    }

    @Test
    @DisplayName("[Edge Case] 이름이 유효하지 않으면 400 Bad Request를 반환한다")
    void edgeCase_InvalidName() throws Exception {
        mockMvc.perform(put("/members/1/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"C\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}
