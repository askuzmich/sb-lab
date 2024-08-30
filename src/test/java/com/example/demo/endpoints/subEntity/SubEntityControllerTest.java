package com.example.demo.endpoints.subEntity;

import com.example.demo.returnDataObject.CustomStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SubEntityControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SubEntityService subEntityService;

    List<SubEntity> subEntities;

    @BeforeEach
    void setUp() {
        SubEntity se1 = new SubEntity();
        se1.setId("110022");
        se1.setName("se1");
        se1.setDescription("woo-hoo se1");
        se1.setImgUrl("https://fakeImageUrl.com/se1");

        SubEntity se2 = new SubEntity();
        se2.setId("110033");
        se2.setName("se2");
        se2.setDescription("woo-hoo se2");
        se2.setImgUrl("https://fakeImageUrl.com/se2");

        SubEntity se3 = new SubEntity();
        se3.setId("110044");
        se3.setName("se3");
        se3.setDescription("woo-hoo se3");
        se3.setImgUrl("https://fakeImageUrl.com/se3");

        this.subEntities = new ArrayList<>();

        this.subEntities.add(se1);
        this.subEntities.add(se2);
        this.subEntities.add(se3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findSubEntityById() throws Exception {
        given(this.subEntityService.findById("110044"))
                .willReturn(this.subEntities.get(2));

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/subEntities/110044")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Transaction is Ok"))
                .andExpect(jsonPath("$.data.id").value("110044"))
                .andExpect(jsonPath("$.data.name").value("se3"))
                .andExpect(jsonPath("$.data.description").value("woo-hoo se3"))
                .andExpect(jsonPath("$.data.imgUrl").value("https://fakeImageUrl.com/se3"));
    }

    @Test
    void findSubEntityByIdNotFound() throws Exception {
        given(this.subEntityService.findById("110044"))
                .willThrow(new SubEntityNotFoundException("110044"));

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/subEntities/110044")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.isSuccess").value(false))
                .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Not find subEntity with ID: 110044"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}