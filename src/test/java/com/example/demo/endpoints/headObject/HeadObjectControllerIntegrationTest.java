package com.example.demo.endpoints.headObject;

import com.example.demo.endpoints.headObject.DTO.HeadObjectDto;
import com.example.demo.returnDataObject.CustomStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
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
@DisplayName("HeadObject Integration API TESTS")
@Tag("integration")
class HeadObjectControllerIntegrationTest {

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

        HeadObjectDto headObjectDto = new HeadObjectDto(
            null,
            "New Head Object",
            null
        );

        String jsonString = this.objectMapper.writeValueAsString(headObjectDto);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .post(baseUrl + "/headObjects")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", this.token)
                    .content(jsonString)
                    .accept(MediaType.APPLICATION_JSON) // should receive
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.name").value("New Head Object"));



        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .get(baseUrl + "/headObjects")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", this.token)
                    .accept(MediaType.APPLICATION_JSON) // should receive
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data", Matchers.hasSize(4))); // +1

    }

    @Test
    void testFindAll() throws Exception {

        this.mockMvc.perform(
            MockMvcRequestBuilders
                .get(baseUrl + "/headObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", this.token)
                .accept(MediaType.APPLICATION_JSON) // should receive
        )
        .andExpect(jsonPath("$.isSuccess").value(true))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
        .andExpect(jsonPath("$.message").value("Transaction is Ok"))
        .andExpect(jsonPath("$.data", Matchers.hasSize(4)));

    }

    @Test
    void testUpdate() throws Exception {
        HeadObjectDto headObjectDto = new HeadObjectDto(
            null,
            "UPDATED Head Object",
            null
        );

        String body = this.objectMapper.writeValueAsString(headObjectDto);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .put(baseUrl + "/headObjects/2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", this.token)
                    .content(body)
                    .accept(MediaType.APPLICATION_JSON) // receive
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.id").value(2))
            .andExpect(jsonPath("$.data.name").value("UPDATED Head Object"));
    }


    @Test
    void testDelete() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .delete(baseUrl + "/headObjects/1")
                    .header("Authorization", this.token)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data").isEmpty());


        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .get(baseUrl + "/headObjects")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", this.token)
                    .accept(MediaType.APPLICATION_JSON) // should receive
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data", Matchers.hasSize(3))); // -1
    }

}