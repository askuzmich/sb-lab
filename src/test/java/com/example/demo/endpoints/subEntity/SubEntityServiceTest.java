package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.headObject.HeadObject;
import com.example.demo.endpoints.subEntity.exception.SubEntityNotFoundException;
import com.example.demo.utis.UUID;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubEntityServiceTest {

    @Mock
    SubEntityRepository subEntityRepository;

    @Mock
    UUID uuid;

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
        assertThat(resultOfMockTransaction.getId())
                .isEqualTo(subEntity.getId());
        assertThat(resultOfMockTransaction.getName())
                .isEqualTo(subEntity.getName());
        assertThat(resultOfMockTransaction.getDescription())
                .isEqualTo(subEntity.getDescription());
        assertThat(resultOfMockTransaction.getImgUrl())
                .isEqualTo(subEntity.getImgUrl());

        verify(subEntityRepository, times(1))
                .findById("12234");
    }

    @Test
    void testFindByIdNotFound() {
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

    @Test
    void testAdd() {
        SubEntity subEntity = new SubEntity();
        subEntity.setName("entity");
        subEntity.setDescription("entity of the head");
        subEntity.setImgUrl("http://fakeurl.com");



        given(uuid.getId())
                .willReturn("12234");

        given(subEntityRepository.save(subEntity))
                .willReturn(subEntity);



        SubEntity candidate = subEntityService.add(subEntity);

        // Compare
        assertThat(candidate.getId())
                .isEqualTo("12234");
        assertThat(candidate.getName())
                .isEqualTo(subEntity.getName());
        assertThat(candidate.getDescription())
                .isEqualTo(subEntity.getDescription());
        assertThat(candidate.getImgUrl())
                .isEqualTo(subEntity.getImgUrl());

        verify(subEntityRepository, times(1))
                .save(subEntity);
    }

    @Test
    void testUpdate() {
        SubEntity mockExistingSubEntity = new SubEntity();
        mockExistingSubEntity.setId("110066");
        mockExistingSubEntity.setName("MockExistingSubEntity");
        mockExistingSubEntity.setDescription("woo-hoo mockExistingSubEntity");
        mockExistingSubEntity.setImgUrl("https://fakeImageUrl.com/se5");

        SubEntity candidateToUpdate = new SubEntity();
        candidateToUpdate.setId("110066");
        candidateToUpdate.setName("MockExistingSubEntity");
        candidateToUpdate.setDescription("updating... candidateToUpdate");
        candidateToUpdate.setImgUrl("https://fakeImageUrl.com/se5");


        given(subEntityRepository.findById("110066"))
                .willReturn(Optional.of(mockExistingSubEntity));

        given(subEntityRepository.save(mockExistingSubEntity))
                .willReturn(mockExistingSubEntity);

        SubEntity update = subEntityService.update("110066", candidateToUpdate);

        assertThat(update.getId())
                .isEqualTo(mockExistingSubEntity.getId());
        assertThat(update.getName())
                .isEqualTo(mockExistingSubEntity.getName());
        assertThat(update.getDescription())
                .isEqualTo(candidateToUpdate.getDescription());
        assertThat(update.getImgUrl())
                .isEqualTo(mockExistingSubEntity.getImgUrl());

        verify(subEntityRepository, times(1))
                .findById("110066");

        verify(subEntityRepository, times(1))
                .save(mockExistingSubEntity);
    }

    @Test
    void testUpdateNotFound() {
        SubEntity mockExistingSubEntity = new SubEntity();
//        mockExistingSubEntity.setId("110066");
        mockExistingSubEntity.setName("MockExistingSubEntity");
        mockExistingSubEntity.setDescription("woo-hoo mockExistingSubEntity");
        mockExistingSubEntity.setImgUrl("https://fakeImageUrl.com/se5");

        given(subEntityRepository.findById("110066"))
                .willReturn(Optional.empty());

        assertThrows(
            SubEntityNotFoundException.class,
            () -> {
                subEntityService.update("110066", mockExistingSubEntity);
            }
        );

        verify(subEntityRepository, times(1))
                .findById("110066");
    }
}