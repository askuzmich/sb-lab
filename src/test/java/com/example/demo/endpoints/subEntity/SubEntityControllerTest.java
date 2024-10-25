package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.subEntity.DTO.SubEntityDto;
//import com.example.demo.endpoints.subEntity.exception.SubEntityNotFoundException;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.returnDataObject.CustomStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // security switch off
class SubEntityControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SubEntityService subEntityService;

    @Autowired
    ObjectMapper objectMapper;

    List<SubEntity> subEntities;

    @Value("${api.endpoint.base-url}")
    String baseUrl;

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
    void testFindSubEntityById() throws Exception {
        given(this.subEntityService.findById("110044"))
                .willReturn(this.subEntities.get(2));

        this.mockMvc.perform(
            MockMvcRequestBuilders.get(baseUrl + "/subEntities/110044")
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
    void testFindSubEntityByIdNotFound() throws Exception {
        given(this.subEntityService.findById("110044"))
            .willThrow(new CustomNotFoundException("subEntity", "110044"));

        this.mockMvc.perform(
            MockMvcRequestBuilders.get(baseUrl + "/subEntities/110044")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(jsonPath("$.isSuccess").value(false))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
            .andExpect(jsonPath("$.message").value("Not find subEntity with ID: 110044"))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testFindAll() throws Exception {
        given(this.subEntityService.getAll())
                .willReturn(this.subEntities);

        this.mockMvc.perform(
            MockMvcRequestBuilders.get(baseUrl + "/subEntities")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data", Matchers.hasSize(this.subEntities.size())))
            .andExpect(jsonPath("$.data[0].id").value("110022"));
    }

    @Test
    void testAdd() throws Exception {
        SubEntityDto subEntityDto = new SubEntityDto(
            null,
            "se10",
            "woo-hoo se10",
            "https://fakeImageUrl.com/se10",
            null
        );

        String jsonString = this.objectMapper.writeValueAsString(subEntityDto);

        SubEntity se1 = new SubEntity();
        se1.setId("110022");
        se1.setName("se10");
        se1.setDescription("woo-hoo se10");
        se1.setImgUrl("https://fakeImageUrl.com/se10");

        given(this.subEntityService.add(Mockito.any(SubEntity.class)))
                .willReturn(se1);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post(baseUrl + "/subEntities")
                // send
                .contentType(MediaType.APPLICATION_JSON).content(jsonString)
                // receive
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.id").value("110022"))
            .andExpect(jsonPath("$.data.name").value(se1.getName()));
    }

    @Test
    void testUpdate() throws Exception {
        SubEntityDto dto = new SubEntityDto(
            "110022",
            "se10",
            "woo-hoo se10",
            "https://fakeImageUrl.com/se10",
            null
        );

        String jsonString = this.objectMapper.writeValueAsString(dto);

        SubEntity shouldBeReturnedSE = new SubEntity();
        shouldBeReturnedSE.setId("110022");
        shouldBeReturnedSE.setName("shouldBeReturnedSE0");
        shouldBeReturnedSE.setDescription("woo-hoo shouldBeReturnedSE0");
        shouldBeReturnedSE.setImgUrl("https://fakeImageUrl.com/se10");

        given(
            this.subEntityService.update(
                eq("110022"),
                Mockito.any(SubEntity.class)
            )
        ).willReturn(shouldBeReturnedSE);


        this.mockMvc.perform(
                MockMvcRequestBuilders.put(baseUrl + "/subEntities/110022")
                    // send
                    .contentType(MediaType.APPLICATION_JSON).content(jsonString)
                    // receive
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.id").value("110022"))
            .andExpect(jsonPath("$.data.name").value(shouldBeReturnedSE.getName()))
            .andExpect(jsonPath("$.data.description").value(shouldBeReturnedSE.getDescription()))
            .andExpect(jsonPath("$.data.imgUrl").value(shouldBeReturnedSE.getImgUrl()));
    }


    @Test
    void testUpdateNotFound() throws Exception {

        SubEntityDto dto = new SubEntityDto(
                "110022",
                "se10",
                "woo-hoo se10",
                "https://fakeImageUrl.com/se10",
                null
        );

        String jsonString = this.objectMapper.writeValueAsString(dto);

        given(this.subEntityService.update(eq("110022"), Mockito.any(SubEntity.class)))
                .willThrow(new CustomNotFoundException("subEntity", "110022"));


        this.mockMvc.perform(
                MockMvcRequestBuilders.put(baseUrl + "/subEntities/110022")
                    // send
                    .contentType(MediaType.APPLICATION_JSON).content(jsonString)
                    // receive
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(false))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
            .andExpect(jsonPath("$.message").value("Not find subEntity with ID: 110022"))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDelete() throws Exception {
        doNothing()
            .when(subEntityService)
                .delete("110066");

        // When... Then... assertion part
        this.mockMvc.perform(
            MockMvcRequestBuilders.delete(baseUrl + "/subEntities/110066")
                // client send nothing
                // client receive
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(true))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
        .andExpect(jsonPath("$.message").value("Transaction is Ok"))
        .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDeleteNotSuccess() throws Exception {
        doThrow(new CustomNotFoundException("subEntity", "110066"))
                .when(subEntityService).delete("110066");

        // When... Then... assertion part
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete(baseUrl + "/subEntities/110066")
                    // client send nothing
                    // client receive
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(false))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
            .andExpect(jsonPath("$.message").value("Not find subEntity with ID: 110066"))
            .andExpect(jsonPath("$.data").isEmpty());
    }
}