package com.example.demo.endpoints.headObject;

import com.example.demo.endpoints.headObject.DTO.HeadObjectDto;
//import com.example.demo.endpoints.headObject.exc.HeadObjectNotFoundException;
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
@AutoConfigureMockMvc
class HeadObjectControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    HeadObjectService headObjectService;

    @Autowired
    ObjectMapper objectMapper;

    List<HeadObject> headObjects;

    @Value("${api.endpoint.base-url}")
    String baseUrl;


    @BeforeEach
    void setUp() {
        HeadObject ho1 = new HeadObject();
        ho1.setId(1);
        ho1.setName("ho1");

        HeadObject ho2 = new HeadObject();
        ho2.setId(2);
        ho2.setName("ho2");

        HeadObject ho3 = new HeadObject();
        ho3.setId(3);
        ho3.setName("ho3");

        this.headObjects = new ArrayList<>();

        this.headObjects.add(ho1);
        this.headObjects.add(ho2);
        this.headObjects.add(ho3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindHeadObjectById() throws Exception {

        given(this.headObjectService.findById(3))
                .willReturn(this.headObjects.get(2));

        this.mockMvc.perform(
            MockMvcRequestBuilders.get(baseUrl + "/headObjects/3")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(true))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
        .andExpect(jsonPath("$.message").value("Transaction is Ok"))
        .andExpect(jsonPath("$.data.id").value(3))
        .andExpect(jsonPath("$.data.name").value("ho3"))
        .andExpect(jsonPath("$.data.hasSubEntities").value(0));
    }

    @Test
    void testFindHeadObjectByIdNotFound() throws Exception {
        given(this.headObjectService.findById(3))
            .willThrow(new CustomNotFoundException("Head Object", 3));

        this.mockMvc.perform(
            MockMvcRequestBuilders.get(baseUrl + "/headObjects/3")
                    .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(false))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
        .andExpect(jsonPath("$.message").value("Not find Head Object with ID: 3"))
        .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testGetAll() throws Exception {
        given(this.headObjectService.getAll())
            .willReturn(this.headObjects);

        this.mockMvc.perform(
            MockMvcRequestBuilders.get(baseUrl + "/headObjects")
                    .accept(MediaType.APPLICATION_JSON)
        )

        .andExpect(jsonPath("$.isSuccess").value(true))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
        .andExpect(jsonPath("$.message").value("Transaction is Ok"))
        .andExpect(jsonPath("$.data", Matchers.hasSize(this.headObjects.size())))
        .andExpect(jsonPath("$.data[0].id").value(1));

    }

    @Test
    void testAdd() throws Exception {
        HeadObjectDto headObjectDto = new HeadObjectDto(
            null,
            "ho4",
            null
        );

        String jsonString = this.objectMapper.writeValueAsString(headObjectDto);

        HeadObject headObject = new HeadObject();
        headObject.setId(4);
        headObject.setName("ho4");

        given(this.headObjectService.add(Mockito.any(HeadObject.class)))
                .willReturn(headObject);


        this.mockMvc.perform(
            MockMvcRequestBuilders.post(baseUrl + "/headObjects")
                // send
                .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonString)
                // receive
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.id").value(4))
            .andExpect(jsonPath("$.data.name").value(headObject.getName()));
    }

    @Test
    void testUpdate() throws Exception {
        HeadObjectDto hoDto = new HeadObjectDto(
            12,
            "Head Object #12",
            null
        );

        String json = this.objectMapper.writeValueAsString(hoDto);

        HeadObject shouldBeReturned = new HeadObject();
        shouldBeReturned.setId(12);
        shouldBeReturned.setName("UPDATED Head Object #12");

        given(
            this.headObjectService.update(
                eq(12),
                Mockito.any(HeadObject.class)
            )
        ).willReturn(shouldBeReturned);

        this.mockMvc.perform(
            MockMvcRequestBuilders.put(baseUrl + "/headObjects/12")
                // send
                .contentType(MediaType.APPLICATION_JSON).content(json)
                // receive
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(true))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
        .andExpect(jsonPath("$.message").value("Transaction is Ok"))
        .andExpect(jsonPath("$.data.id").isNotEmpty())
        .andExpect(jsonPath("$.data.id").value(12))
        .andExpect(jsonPath("$.data.name").value(shouldBeReturned.getName()));
    }

    @Test
    void testUpdateNotFound() throws Exception {

        HeadObjectDto hoDto = new HeadObjectDto(
                12,
                "Head Object #12",
                null
        );

        String json = this.objectMapper.writeValueAsString(hoDto);

        given(
            this.headObjectService.update(
                eq(12),
                Mockito.any(HeadObject.class)
            )
        ).willThrow(new CustomNotFoundException("Head Object", 12));


        this.mockMvc.perform(
            MockMvcRequestBuilders.put(baseUrl + "/headObjects/12")
                // send
                .contentType(MediaType.APPLICATION_JSON).content(json)
                // receive
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(false))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
        .andExpect(jsonPath("$.message").value("Not find Head Object with ID: 12"))
        .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDelete() throws Exception {
        doNothing()
            .when(headObjectService)
                .delete(1);


        this.mockMvc.perform(
            MockMvcRequestBuilders.delete(baseUrl + "/headObjects/1")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(true))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
        .andExpect(jsonPath("$.message").value("Transaction is Ok"))
        .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDeleteNotSuccess() throws Exception {
        doThrow(new CustomNotFoundException("Head Object", 1))
            .when(headObjectService)
                .delete(1);

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete(baseUrl + "/headObjects/1")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(false))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
        .andExpect(jsonPath("$.message").value("Not find Head Object with ID: 1"))
        .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testAssignSubEntity() throws Exception {
        // data
        doNothing()
            .when(this.headObjectService)
                .assignmentSubEntity(110022, "110022");

        // if then
        this.mockMvc.perform(
            MockMvcRequestBuilders.put(baseUrl + "/headObjects/11022/subEntities/11022")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(true))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
        .andExpect(jsonPath("$.message").value("Assignment is Ok"))
        .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testAssignSubEntityNotFoundOfHeadObj() throws Exception {
        // data
        doThrow(new CustomNotFoundException("Head Object", 110022))
            .when(this.headObjectService)
                .assignmentSubEntity(110022, "110022");

        // if then
        this.mockMvc.perform(
            MockMvcRequestBuilders.put(baseUrl + "/headObjects/110022/subEntities/110022")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(false))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
        .andExpect(jsonPath("$.message").value("Not find Head Object with ID: " + 110022))
        .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testAssignSubEntityNotFoundOfSubEnt() throws Exception {
        // data
        doThrow(new CustomNotFoundException("Sub Entity", "110022"))
            .when(this.headObjectService)
                .assignmentSubEntity(110022, "110022");

        // if then
        this.mockMvc.perform(
            MockMvcRequestBuilders.put(baseUrl + "/headObjects/110022/subEntities/110022")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("$.isSuccess").value(false))
        .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
        .andExpect(jsonPath("$.message").value("Not find Sub Entity with ID: " + "110022"))
        .andExpect(jsonPath("$.data").isEmpty());
    }
}