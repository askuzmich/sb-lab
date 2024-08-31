package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.headObject.HeadObject;
import com.example.demo.endpoints.subEntity.exception.SubEntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SubEntityServiceTest {

    @Mock
    SubEntityRepository subEntityRepository;

    @InjectMocks
    SubEntityService subEntityService;

    List<SubEntity> subEntities;

    @BeforeEach
    void setUp() {
        SubEntity se1 = new SubEntity();
        se1.setId("110022");
        se1.setName("SubEntity 1");
        se1.setDescription("woo-hoo se1");
        se1.setImgUrl("https://fakeImageUrl.com/se1");

        SubEntity se2 = new SubEntity();
        se2.setId("110033");
        se2.setName("SubEntity 2");
        se2.setDescription("woo-hoo se2");
        se2.setImgUrl("https://fakeImageUrl.com/se2");

        SubEntity se3 = new SubEntity();
        se3.setId("110044");
        se3.setName("SubEntity 3");
        se3.setDescription("woo-hoo se3");
        se3.setImgUrl("https://fakeImageUrl.com/se3");

        SubEntity se4 = new SubEntity();
        se4.setId("110055");
        se4.setName("SubEntity 4");
        se4.setDescription("woo-hoo se4");
        se4.setImgUrl("https://fakeImageUrl.com/se4");

        SubEntity se5 = new SubEntity();
        se5.setId("110066");
        se5.setName("SubEntity 5");
        se5.setDescription("woo-hoo se5");
        se5.setImgUrl("https://fakeImageUrl.com/se5");

        this.subEntities = new ArrayList<>();

        subEntities.add(se1);
        subEntities.add(se2);
        subEntities.add(se3);
        subEntities.add(se4);
        subEntities.add(se5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindById() {
        // Behavior DATA
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

        SubEntity resultOfMockTransaction = subEntityService.findById("12234");

        // Compare
        assertThat(resultOfMockTransaction.getId()).isEqualTo(subEntity.getId());
        assertThat(resultOfMockTransaction.getName()).isEqualTo(subEntity.getName());
        assertThat(resultOfMockTransaction.getDescription()).isEqualTo(subEntity.getDescription());
        assertThat(resultOfMockTransaction.getImgUrl()).isEqualTo(subEntity.getImgUrl());
        verify(subEntityRepository, times(1)).findById("12234");
    }

    @Test
    void testFindBiIdNotFound() {
        // Behavior itself
        given(subEntityRepository.findById(Mockito.any(String.class)))
                .willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> {
            SubEntity resultOfMockTransaction = subEntityService.findById("12234");
        });

        assertThat(thrown)
                .isInstanceOf(SubEntityNotFoundException.class)
                .hasMessage("Not find subEntity with ID: 12234");

        verify(subEntityRepository, times(1))
                .findById("12234");
    }


    @Test
    void testFindAll() {

        // Behavior itself
        given(subEntityRepository.findAll())
                .willReturn(this.subEntities);

        List<SubEntity> resultOfMockTransaction = subEntityService.getAll();

        // Compare
        assertThat(resultOfMockTransaction.size())
                .isEqualTo(this.subEntities.size());

        // called once
        verify(subEntityRepository, times(1)).findAll();
    }
}