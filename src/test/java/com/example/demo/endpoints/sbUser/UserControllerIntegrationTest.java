package com.example.demo.endpoints.sbUser;

import com.example.demo.endpoints.sbUser.DTO.UserDto;
import com.example.demo.returnDataObject.CustomStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User Integration API TESTS")
class UserControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${api.endpoint.base-url}")
    String baseUrl;

    String token;

    @BeforeEach
    void setUp() throws Exception {

        ResultActions result = this.mockMvc.perform(
        MockMvcRequestBuilders
            .post(
                this.baseUrl + "/users/login"
            )
            .with(httpBasic("Alexander", "Alexander")));

        MvcResult mvcResult = result.andDo(print()).andReturn();

        /**
         * {"isSuccess": true, "statusCode": 200, "message": "...
         */
        String contentAsString = mvcResult.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(contentAsString);

        this.token = "Bearer " + jsonObject
            .getJSONObject("data")
            .getString("token");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testAdd() throws Exception {
        SbUser user = new SbUser();
        user.setName("Newman");
        user.setRoles("USER");
        user.setPassword("Newman");
        user.setEnabled(true);

        String body = this.objectMapper.writeValueAsString(user);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .post(baseUrl + "/users")
                    .header("Authorization", this.token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.name").value("Newman"));

    }


    @Test
    void testFindUserById() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .get(baseUrl + "/users/1")
                    .header("Authorization", this.token)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.name").value("Alexander"))
            .andExpect(jsonPath("$.data.enabled").value(true))
            .andExpect(jsonPath("$.data.roles").value("ADMIN"));

    }

    @Test
    void testGetAll() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .get(baseUrl + "/users")
                    .header("Authorization", this.token)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data", Matchers.hasSize(3)))
            .andExpect(jsonPath("$.data[0].id").value(1));
    }

    @Test
    void testUpdate() throws Exception {
        UserDto dto = new UserDto(
            null,
            "Updated Name",
            "USER",
            false
        );

        String jsonString = this.objectMapper.writeValueAsString(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .put(baseUrl + "/users/2")
                    .header("Authorization", this.token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonString)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.id").value(2))
            .andExpect(jsonPath("$.data.name").value("Updated Name"))
            .andExpect(jsonPath("$.data.enabled").value(false))
            .andExpect(jsonPath("$.data.roles").value("USER"));

    }

    @Test
    void testDelete() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .delete(baseUrl + "/users/3")
                    .header("Authorization", this.token)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data").isEmpty());

    }

}