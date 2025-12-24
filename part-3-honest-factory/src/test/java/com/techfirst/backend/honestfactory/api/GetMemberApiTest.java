package com.techfirst.backend.honestfactory.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techfirst.backend.honestfactory.domain.ErrorCode;
import com.techfirst.backend.honestfactory.service.MemberDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("회원 조회 API (GET /members/{id})")
class GetMemberApiTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("[Happy Path] 존재하는 ID로 조회 시 회원 정보를 반환하고 200 OK를 응답한다.")
    void happyPath() throws Exception {
        // given: 먼저 회원을 등록하여 ID를 확보
        var registerRequest = new MemberController.RegisterMemberRequest("ExistingMember", 25);

        String responseContent =
                mockMvc.perform(
                                post("/members")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(registerRequest)))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        // 등록된 회원의 ID 추출
        var registeredMember = objectMapper.readValue(responseContent, MemberDto.class);
        Long memberId = registeredMember.id();

        // when & then: 추출한 ID로 조회
        mockMvc.perform(get("/members/" + memberId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ExistingMember"))
                .andExpect(jsonPath("$.age").value(25));
    }

    @Test
    @DisplayName("[Edge Case] 존재하지 않는 ID로 조회 시 404 Not Found를 반환한다.")
    void edgeCase_NotFound() throws Exception {
        // given
        long nonExistentId = 9999L;

        // when & then
        mockMvc.perform(get("/members/" + nonExistentId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
    }
}
