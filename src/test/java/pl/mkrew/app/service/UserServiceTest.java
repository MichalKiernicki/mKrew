package pl.mkrew.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mkrew.app.domain.UserEntity;
import pl.mkrew.app.dto.UserDto;
import pl.mkrew.app.repository.UserRepository;


import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static pl.mkrew.app.domain.BloodGroup.A_RH_p;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;

    @Test
    void getAllUsersTest() {

    }

    @Test
    void findUserByIdTest() {

        Long id = 1L;
        userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setLogin("Login");

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));

        final Optional<UserDto> expected = userService.getUser(id);

        assertThat(expected).isNotNull();
    }


}