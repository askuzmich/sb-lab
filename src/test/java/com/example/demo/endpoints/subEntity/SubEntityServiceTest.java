package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.headObject.HeadObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SubEntityServiceTest {

    @Mock
    SubEntityRepository subEntityRepository;

    @InjectMocks
    SubEntityService subEntityService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
        // Behavior DATA
        // {"id": "12234", "name": "entity1", "description": "entity of the head", "imageUrl": "http://fakeurl.com"}
        SubEntity subEntity = new SubEntity();
        subEntity.setId("12234");
        subEntity.setName("entity1");
        subEntity.setDescription("entity of the head");
        subEntity.setImgUrl("http://fakeurl.com");

        HeadObject headObject = new HeadObject();
        headObject.setId(1);
        headObject.setName("HeadObject1");

        subEntity.setOwner(headObject);

        // Behavior itself
        given(subEntityRepository.findById("12234")).willReturn(Optional.of(subEntity));

        SubEntity resultOfFakeTransaction = subEntityService.findById("12234");

        // Compare
        assertThat(resultOfFakeTransaction.getId()).isEqualTo(subEntity.getId());
        assertThat(resultOfFakeTransaction.getName()).isEqualTo(subEntity.getName());
        assertThat(resultOfFakeTransaction.getDescription()).isEqualTo(subEntity.getDescription());
        assertThat(resultOfFakeTransaction.getImgUrl()).isEqualTo(subEntity.getImgUrl());
        verify(subEntityRepository, times(1)).findById("12234");
    }

    @Test
    void testFindBiIdNotFound() {
        // Behavior itself
        given(subEntityRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> {
            SubEntity resultOfFakeTransaction = subEntityService.findById("12234");
        });

        assertThat(thrown)
                .isInstanceOf(SubEntityNotFoundException.class)
                .hasMessage("Not find subEntity with ID: 12234");
        verify(subEntityRepository, times(1)).findById("12234");
    }
}