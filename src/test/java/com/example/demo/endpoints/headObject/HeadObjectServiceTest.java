package com.example.demo.endpoints.headObject;

//import com.example.demo.endpoints.headObject.exc.HeadObjectNotFoundException;

import com.example.demo.endpoints.subEntity.SubEntity;
import com.example.demo.endpoints.subEntity.SubEntityRepository;
import com.example.demo.exception.CustomNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "development")
class HeadObjectServiceTest {

    @Mock
    HeadObjectRepository headObjectRepository;

    @Mock
    SubEntityRepository subEntityRepository;

    @InjectMocks
    HeadObjectService headObjectService;

    List<HeadObject> headObjects;

    @BeforeEach
    void setUp() {
        HeadObject ho1 = new HeadObject();
        ho1.setId(110022);
        ho1.setName("Head Object 1");

        HeadObject ho2 = new HeadObject();
        ho2.setId(110033);
        ho2.setName("Head Object 2");

        HeadObject ho3 = new HeadObject();
        ho3.setId(110044);
        ho3.setName("Head Object 3");

        HeadObject ho4 = new HeadObject();
        ho4.setId(110055);
        ho4.setName("Head Object 4");

        HeadObject ho5 = new HeadObject();
        ho5.setId(110066);
        ho5.setName("Head Object 5");

        this.headObjects = new ArrayList<>();

        headObjects.add(ho1);
        headObjects.add(ho2);
        headObjects.add(ho3);
        headObjects.add(ho4);
        headObjects.add(ho5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindById() {
        // data
        HeadObject headObject = new HeadObject();
        headObject.setId(1);
        headObject.setName("Head Object 1");

        // if
        given(headObjectRepository.findById(1))
                .willReturn(Optional.of(headObject));

        HeadObject resultOfMockTransaction = headObjectService.findById(1);

        // Compare
        assertThat(resultOfMockTransaction.getId())
                .isEqualTo(headObject.getId());
        assertThat(resultOfMockTransaction.getName())
                .isEqualTo(headObject.getName());

        verify(headObjectRepository, times(1))
                .findById(1);
    }

    @Test
    void testFindByIdNotFound() {
        given(headObjectRepository.findById(Mockito.any(Integer.class)))
                .willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> {
            HeadObject resultOfNotFound = headObjectService.findById(12);
        });

        assertThat(thrown)
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessage("Not find Head Object with ID: 12");

        verify(headObjectRepository, times(1))
                .findById(12);
    }

    @Test
    void testGetAll() {
        given(headObjectRepository.findAll())
                .willReturn(this.headObjects);

        List<HeadObject> mockTransactionResult = headObjectService.getAll();

        assertThat(mockTransactionResult.size())
                .isEqualTo(this.headObjects.size());

        verify(headObjectRepository, times(1)).findAll();
    }

    @Test
    void testAdd() {
        // data

        // if
        given(headObjectRepository.save(this.headObjects.get(0)))
                .willReturn(this.headObjects.get(0));

        HeadObject candidate = headObjectService.add(this.headObjects.get(0));

        // Compare
        assertThat(candidate.getId())
                .isEqualTo(110022);
        assertThat(candidate.getName())
                .isEqualTo(this.headObjects.get(0).getName());

        verify(headObjectRepository, times(1))
                .save(this.headObjects.get(0));
    }

    @Test
    void testUpdate() {
        HeadObject mockExistingHeadObject = new HeadObject();
        mockExistingHeadObject.setId(12);
        mockExistingHeadObject.setName("MockExistingSubEntity");


        HeadObject candidateToUpdate = new HeadObject();
        candidateToUpdate.setName("UpdatedExistingSubEntity");

        given(headObjectRepository.findById(12))
                .willReturn(Optional.of(mockExistingHeadObject));

        given(headObjectRepository.save(mockExistingHeadObject))
                .willReturn(mockExistingHeadObject);

        HeadObject update = headObjectService.update(12, candidateToUpdate);

        assertThat(update.getId())
                .isEqualTo(12);
        assertThat(update.getName())
                .isEqualTo(candidateToUpdate.getName());

        verify(headObjectRepository, times(1))
                .save(mockExistingHeadObject);

        verify(headObjectRepository, times(1))
                .findById(12);
    }

    @Test
    void testUpdateNotFound() {
        HeadObject mockExistingHeadObject = new HeadObject();
        mockExistingHeadObject.setName("MockExistingSubEntity");

        given(headObjectRepository.findById(12))
            .willReturn(Optional.empty());

        assertThrows(
            CustomNotFoundException.class,
            () -> headObjectService.update(12, mockExistingHeadObject)
        );

        verify(headObjectRepository, times(1))
                .findById(12);
    }

    @Test
    void testDelete() {
        given(headObjectRepository.findById(1))
            .willReturn(Optional.of(this.headObjects.get(0)));

        doNothing()
            .when(headObjectRepository)
                .deleteById(1);

        headObjectService.delete(1);

        verify(headObjectRepository, times(1))
                .deleteById(1);
    }

    @Test
    void testDeleteNotSuccess() {
        given((headObjectRepository.findById(1)))
                .willReturn(Optional.empty());

        assertThrows(
            CustomNotFoundException.class,
            () -> {
                headObjectService.delete(1);
            }
        );

        verify(headObjectRepository, times(1))
                .findById(1);
    }

    @Test
    void testAssignmentSubEntity() {
        // data [code example]
        SubEntity subEntity1 = new SubEntity();
        subEntity1.setId("110022");
        subEntity1.setName("Sub Entity 1");
        subEntity1.setDescription("SE Description");
        subEntity1.setImgUrl("https://very-fake-url.com/img/1.jpg");

//        SubEntity subEntity2 = new SubEntity();
//        subEntity2.setId("110033");
//        subEntity2.setName("Sub Entity 2");
//        subEntity2.setDescription("SE Description");
//        subEntity2.setImgUrl("https://very-fake-url.com/img/2.jpg");
//
//        SubEntity subEntity3 = new SubEntity();
//        subEntity3.setId("110044");
//        subEntity3.setName("Sub Entity 3");
//        subEntity3.setDescription("SE Description");
//        subEntity3.setImgUrl("https://very-fake-url.com/img/3.jpg");
//
//        SubEntity subEntity4 = new SubEntity();
//        subEntity4.setId("110055");
//        subEntity4.setName("Sub Entity 4");
//        subEntity4.setDescription("SE Description");
//        subEntity4.setImgUrl("https://very-fake-url.com/img/4.jpg");
//
//        SubEntity subEntity5 = new SubEntity();
//        subEntity5.setId("110066");
//        subEntity5.setName("Sub Entity 5");
//        subEntity5.setDescription("SE Description");
//        subEntity5.setImgUrl("https://very-fake-url.com/img/5.jpg");

        headObjects.get(2).addSubEntity(subEntity1);

        // we should assign headObjects.get(3) to subEntity2
        given(this.subEntityRepository.findById("110022"))
                .willReturn(Optional.of(subEntity1));

        given(this.headObjectRepository.findById(headObjects.get(3).getId()))
                .willReturn(Optional.of(headObjects.get(3)));

        // if
        this.headObjectService.assignmentSubEntity(headObjects.get(3).getId(), "110022");

        // then
        assertThat(subEntity1.getOwner().getId())
                .isEqualTo(headObjects.get(3).getId());

        assertThat(headObjects.get(3).getSubEntities())
                .contains(subEntity1);
    }

    @Test
    void testAssignmentHeadObjectNotFound() {
        // data
        SubEntity subEntity1 = new SubEntity();
        subEntity1.setId("110022");
        subEntity1.setName("Sub Entity 1");
        subEntity1.setDescription("SE Description");
        subEntity1.setImgUrl("https://very-fake-url.com/img/1.jpg");

        headObjects.get(2).addSubEntity(subEntity1);

        // we should assign headObjects.get(3) to subEntity2
        given(this.subEntityRepository.findById("110022"))
                .willReturn(Optional.of(subEntity1));

        given(this.headObjectRepository.findById(headObjects.get(3).getId()))
                .willReturn(Optional.empty());

        // if
        Throwable thrown = assertThrows(CustomNotFoundException.class, () -> {
            this.headObjectService.assignmentSubEntity(headObjects.get(3).getId(), "110022");
        });

        // then
        assertThat(thrown)
                .isInstanceOf(CustomNotFoundException.class)
                        .hasMessage("Not find Head Object with ID: " + headObjects.get(3).getId());

        // *** headObjects.get(2) instead of get(3) ***
        assertThat(subEntity1.getOwner().getId())
                .isEqualTo(headObjects.get(2).getId());
    }

    @Test
    void testAssignmentSubEntityNotFound() {
        // we should assign headObjects.get(3) to subEntity2
        given(this.subEntityRepository.findById("110022"))
                .willReturn(Optional.empty());

        // if
        Throwable thrown = assertThrows(CustomNotFoundException.class, () -> {
            this.headObjectService.assignmentSubEntity(headObjects.get(3).getId(), "110022");
        });

        // then
        assertThat(thrown)
            .isInstanceOf(CustomNotFoundException.class)
            .hasMessage("Not find SubEntity with ID: " + "110022");
    }
}