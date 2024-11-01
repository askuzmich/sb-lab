package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.subEntity.DTO.SubEntityDto;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("SubEntity Integration API TESTS")
@Tag("integration")
@ActiveProfiles(value = "development")
class SubEntityControllerIntegrationTest {

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

        SubEntityDto subEntityDto = new SubEntityDto(
            null,
            "se10",
            "woo-hoo se10",
            "https://fakeImageUrl.com/se10",
            null
        );

        String body = this.objectMapper.writeValueAsString(subEntityDto);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .post(baseUrl + "/subEntities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", this.token)
                    .content(body)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.name").value("se10"))
            .andExpect(jsonPath("$.data.description").value("woo-hoo se10"))
            .andExpect(jsonPath("$.data.imgUrl").value("https://fakeImageUrl.com/se10"));


        this.mockMvc.perform(
                MockMvcRequestBuilders.get(baseUrl + "/subEntities")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            // +1
            .andExpect(jsonPath("$.data", Matchers.hasSize(6)));
    }

    @Test
    void testFindAll() throws Exception {

        this.mockMvc.perform(
            MockMvcRequestBuilders
                .get(baseUrl + "/subEntities")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data", Matchers.hasSize(6)));
    }

    @Test
//    @Order(3)
    void testUpdate() throws Exception {
        SubEntityDto dto = new SubEntityDto(
            null,
            "new name",
            "new woo-hoo description",
            "https://newFakeImageUrl.com/se10",
            null
        );

        String body = this.objectMapper.writeValueAsString(dto);


        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .put(baseUrl + "/subEntities/110022")
                    .header("Authorization", this.token)
                    .contentType(MediaType.APPLICATION_JSON).content(body)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.id").value("110022"))
            .andExpect(jsonPath("$.data.name").value("new name"))
            .andExpect(jsonPath("$.data.description").value("new woo-hoo description"))
            .andExpect(jsonPath("$.data.imgUrl").value("https://newFakeImageUrl.com/se10"));
    }



    @Test
    void testDelete() throws Exception {

        this.mockMvc.perform(
            MockMvcRequestBuilders
                .delete(baseUrl + "/subEntities/110066")
                .header("Authorization", this.token)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(true))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
        .andExpect(jsonPath("$.message").value("Transaction is Ok"))
        .andExpect(jsonPath("$.data").isEmpty());


        this.mockMvc.perform(
                MockMvcRequestBuilders.get(baseUrl + "/subEntities")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            // -1
            .andExpect(jsonPath("$.data", Matchers.hasSize(5)));
    }

}