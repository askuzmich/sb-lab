package com.example.demo.endpoints.sbUser;

import com.example.demo.endpoints.sbUser.DTO.UserDto;
import com.example.demo.endpoints.subEntity.DTO.SubEntityDto;
import com.example.demo.endpoints.subEntity.SubEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;




import com.example.demo.endpoints.headObject.DTO.HeadObjectDto;
//import com.example.demo.endpoints.headObject.exc.HeadObjectNotFoundException;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.returnDataObject.CustomStatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    List<SbUser> sbUsers;

    @Value("/api/v1")
    String baseUrl;

    @BeforeEach
    void setUp() {
        this.sbUsers = new ArrayList<>();

        SbUser user1 = new SbUser();
        user1.setId(1);
        user1.setName("Alexander");
        user1.setPassword("Alexander");
        user1.setEnabled(true);
        user1.setRoles("ADMIN");

        SbUser user2 = new SbUser();
        user2.setId(2);
        user2.setName("Sergey");
        user2.setPassword("Sergey");
        user2.setEnabled(true);
        user2.setRoles("USER");

        SbUser user3 = new SbUser();
        user3.setId(3);
        user3.setName("Everybody");
        user3.setPassword("Everybody");
        user3.setEnabled(false);
        user3.setRoles("USER");

        this.sbUsers.add(user1);
        this.sbUsers.add(user2);
        this.sbUsers.add(user3);
    }

    @AfterEach
    void testTearDown() {
    }


    @Test
    void testFindUserById() throws Exception {
        given(this.userService.findById(1))
            .willReturn(this.sbUsers.get(0));

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(baseUrl + "/users/1")
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
    void testFindUserByIdNotFound() throws Exception {
        given(this.userService.findById(1))
            .willThrow(new CustomNotFoundException("User", 1));

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(baseUrl + "/users/1")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(false))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
            .andExpect(jsonPath("$.message").value("Not find User with ID: 1"))
            .andExpect(jsonPath("$.data").isEmpty());

    }

    @Test
    void testGetAll() throws Exception {
        given(this.userService.getAll())
            .willReturn(this.sbUsers);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get(baseUrl + "/users")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data", Matchers.hasSize(this.sbUsers.size())))
            .andExpect(jsonPath("$.data[0].id").value(1));
    }

    @Test
    void testAdd() throws Exception {

        String jsonString = this.objectMapper.writeValueAsString(this.sbUsers.get(0));

        given(this.userService.add(Mockito.any(SbUser.class)))
            .willReturn(this.sbUsers.get(0));

        this.mockMvc.perform(
                MockMvcRequestBuilders.post(baseUrl + "/users")
                    // send
                    .contentType(MediaType.APPLICATION_JSON).content(jsonString)
                    // receive
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.name").value(this.sbUsers.get(0).getName()));

    }

    @Test
    void testUpdate() throws Exception {
        UserDto dto = new UserDto(
            1,
            "Updated Name",
            "USER",
            false
        );

        String jsonString = this.objectMapper.writeValueAsString(dto);

        SbUser shouldBeReturned = new SbUser();
        shouldBeReturned.setId(1);
        shouldBeReturned.setName("Updated Name");
        shouldBeReturned.setRoles("USER");
        shouldBeReturned.setEnabled(false);

        given(
            this.userService.update(
                eq(1),
                Mockito.any(SbUser.class)
            )
        ).willReturn(shouldBeReturned);


        this.mockMvc.perform(
                MockMvcRequestBuilders.put(baseUrl + "/users/1")
                    // send
                    .contentType(MediaType.APPLICATION_JSON).content(jsonString)
                    // receive
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(true))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.SUCCESS))
            .andExpect(jsonPath("$.message").value("Transaction is Ok"))
            .andExpect(jsonPath("$.data.id").isNotEmpty())
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.name").value(shouldBeReturned.getName()))
            .andExpect(jsonPath("$.data.enabled").value(shouldBeReturned.isEnabled()))
            .andExpect(jsonPath("$.data.roles").value(shouldBeReturned.getRoles()));

    }

    @Test
    void testUpdateNotFound() throws Exception {
        UserDto dto = new UserDto(
            1,
            "Updated Name",
            "USER",
            false
        );

        String jsonString = this.objectMapper.writeValueAsString(dto);

        given(this.userService.update(eq(1), Mockito.any(SbUser.class)))
            .willThrow(new CustomNotFoundException("User", 1));


        this.mockMvc.perform(
                MockMvcRequestBuilders.put(baseUrl + "/users/1")
                    // send
                    .contentType(MediaType.APPLICATION_JSON).content(jsonString)
                    // receive
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(false))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
            .andExpect(jsonPath("$.message").value("Not find User with ID: 1"))
            .andExpect(jsonPath("$.data").isEmpty());

    }

    @Test
    void testDelete() throws Exception {
        doNothing()
            .when(userService)
            .delete(1);

        // When... Then... assertion part
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete(baseUrl + "/users/1")
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
    void testDeleteNotFound() throws Exception {
        doThrow(new CustomNotFoundException("User", "1"))
            .when(userService).delete(1);

        // When... Then... assertion part
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete(baseUrl + "/users/1")
                    // client send nothing
                    // client receive
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(jsonPath("$.isSuccess").value(false))
            .andExpect(jsonPath("$.statusCode").value(CustomStatusCode.NOT_FOUND))
            .andExpect(jsonPath("$.message").value("Not find User with ID: 1"))
            .andExpect(jsonPath("$.data").isEmpty());

    }
}