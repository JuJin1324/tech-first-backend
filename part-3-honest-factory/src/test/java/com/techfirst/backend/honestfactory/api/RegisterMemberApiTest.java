package com.techfirst.backend.honestfactory.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techfirst.backend.honestfactory.domain.ErrorCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("회원 등록 API (POST /members)")
class RegisterMemberApiTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("[Happy Path] 유효한 정보로 요청 시 회원 등록에 성공하고 200 OK를 반환한다.")
    void happyPath() throws Exception {
        // given
        var request = new MemberController.RegisterMemberRequest("Jujin", 30);

        // when & then
        mockMvc.perform(
                        post("/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jujin"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    @DisplayName("[Edge Case] 이름이 비어있으면 400 Bad Request와 에러 메시지를 반환한다.")
    void edgeCase_InvalidName() throws Exception {
        // given
        var request = new MemberController.RegisterMemberRequest("", 30);

        // when & then
        mockMvc.perform(
                        post("/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ErrorCode.INVALID_NAME.getMessage()));
    }

    @Test
    @DisplayName("[Edge Case] 나이가 범위를 벗어나면 400 Bad Request와 에러 메시지를 반환한다.")
    void edgeCase_InvalidAge() throws Exception {
        // given
        var request = new MemberController.RegisterMemberRequest("Jujin", 200);

        // when & then
        mockMvc.perform(
                        post("/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ErrorCode.AGE_LIMIT_EXCEEDED.getMessage()));
    }
}
