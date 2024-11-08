package com.example.demo.endpoints.sbUser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.CustomNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "development")
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    List<SbUser> sbUsers;

    @BeforeEach
    void setUp() {
        SbUser user1 = new SbUser();
        user1.setId(1);
        user1.setName("Alexander");
        user1.setPassword("Alexander");
        user1.setEnabled(true);
        user1.setRoles("ADMIN USER");

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

        this.sbUsers = new ArrayList<>();
        this.sbUsers.add(user1);
        this.sbUsers.add(user2);
        this.sbUsers.add(user3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindById() {
        // define the behavior of the method
        given(userRepository.findById(1))
                .willReturn(Optional.of(this.sbUsers.get(0)));

        SbUser user = this.userService.findById(1);

        // Compare
        assertThat(user.getId())
                .isEqualTo(this.sbUsers.get(0).getId());
        assertThat(user.getName())
                .isEqualTo(this.sbUsers.get(0).getName());
        assertThat(user.isEnabled())
                .isEqualTo(this.sbUsers.get(0).isEnabled());
        assertThat(user.getRoles())
                .isEqualTo(this.sbUsers.get(0).getRoles());
        assertThat(user.getPassword())
                .isEqualTo(this.sbUsers.get(0).getPassword());

        verify(userRepository, times(1))
                .findById(1);
    }

    @Test
    void testFindByIdNotFound() {

        given(userRepository.findById(Mockito.any(Integer.class)))
                .willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> {
            SbUser resultOfMockTransaction = userService.findById(1);
        });

        assertThat(thrown)
                .isInstanceOf(CustomNotFoundException.class)
                .hasMessage("Not find User with ID: 1");

        verify(userRepository, times(1))
                .findById(1);
    }

    @Test
    void testGetAll() {
        // define the behavior of the method
        given(userRepository.findAll())
                .willReturn(this.sbUsers);

        List<SbUser> users = this.userService.getAll();

        assertThat(users.size())
                .isEqualTo(this.sbUsers.size());

        // called once
        verify(userRepository, times(1)).findAll();

    }

    @Test
    void testAdd() {

        given(this.passwordEncoder.encode(this.sbUsers.get(0).getPassword()))
            .willReturn("sasdasd");

        given(userRepository.save(this.sbUsers.get(0)))
                .willReturn(this.sbUsers.get(0));


        SbUser candidate = userService.add(this.sbUsers.get(0));

        // Compare
        assertThat(candidate.getId())
                .isEqualTo(this.sbUsers.get(0).getId());
        assertThat(candidate.getName())
                .isEqualTo(this.sbUsers.get(0).getName());
        assertThat(candidate.getPassword())
                .isEqualTo("sasdasd");
        assertThat(candidate.isEnabled())
                .isEqualTo(this.sbUsers.get(0).isEnabled());
        assertThat(candidate.getRoles())
                .isEqualTo(this.sbUsers.get(0).getRoles());

        verify(userRepository, times(1))
                .save(this.sbUsers.get(0));
    }

    @Test
    void testUpdate() {
        SbUser candidateToUpdate = new SbUser();
        candidateToUpdate.setName("UpdatedName");
        candidateToUpdate.setRoles("USER");
        candidateToUpdate.setPassword("111");


        given(userRepository.findById(1))
                .willReturn(Optional.of(this.sbUsers.get(0)));

        given(userRepository.save(this.sbUsers.get(0)))
                .willReturn(this.sbUsers.get(0));

        SbUser update = userService.update(1, candidateToUpdate);

        assertThat(update.getId())
                .isEqualTo(1);
        assertThat(update.getName())
                .isEqualTo(candidateToUpdate.getName());
        assertThat(update.getRoles())
                .isEqualTo(candidateToUpdate.getRoles());

        verify(userRepository, times(1))
                .findById(1);

        verify(userRepository, times(1))
                .save(this.sbUsers.get(0));
    }

    @Test
    void testUpdateNotFound() {
        given(userRepository.findById(1))
            .willReturn(Optional.empty());

        assertThrows(
            CustomNotFoundException.class,
            () -> {
                userService.update(1, this.sbUsers.get(0));
            }
        );

        verify(userRepository, times(1))
                .findById(1);
    }

    @Test
    void testDelete() {
        given(userRepository.findById(1))
                .willReturn(Optional.of(this.sbUsers.get(0)));

        doNothing().when(userRepository).deleteById(1);

        userService.delete(1);

        verify(userRepository, times(1))
            .deleteById(1);
    }

    @Test
    void testDeleteNotSuccess() {
        given(userRepository.findById(1))
            .willReturn(Optional.empty());



        // When...
        Throwable thrown = assertThrows(
            CustomNotFoundException.class,
            () -> {
                userService.delete(1);
            }
        );

        assertThat(thrown)
            .isInstanceOf(CustomNotFoundException.class)
                .hasMessage("Not find User with ID: 1");


        // Then... assertion part
        verify(userRepository, times(1))
            .findById(1);
    }
}