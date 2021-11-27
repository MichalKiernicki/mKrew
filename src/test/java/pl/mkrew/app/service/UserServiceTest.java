package pl.mkrew.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mkrew.app.domain.Appointment;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.domain.UserEntity;
import pl.mkrew.app.dto.UserDto;
import pl.mkrew.app.mapper.UserMapper;
import pl.mkrew.app.repository.UserRepository;


import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static pl.mkrew.app.domain.BloodGroup.A_RH_p;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EmailService emailService;


    @InjectMocks
    private UserService userService;


    @Test
    void addingUserTest() {

        UserEntity user = new UserEntity(1L, "login", "Str0ng.Passw0rd",
                "zaspamuj@wp.pl", "name", "surname", "999-999-999",
                A_RH_p, RCKiK.builder().build(), true, UUID.randomUUID(), LocalDateTime.now(), new HashSet<>(), new ArrayList<>());

        UserDto userDto = new UserDto(1L, "login", "Str0ng.Passw0rd",
                "zaspamuj@wp.pl", "name", "surname", "999-999-999",
                "A_RH_p");

        when(userMapper.mapToUser(userDto)).thenReturn(user);

        userService.addUser(userDto);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getAllUsersTest() {

        List<UserEntity> list = new ArrayList();
        //given
        list.add(new UserEntity(1L, "login", "Str0ng.Passw0rd",
                "zaspamuj@wp.pl", "name", "surname", "999-999-999",
                A_RH_p, RCKiK.builder().build(), true, UUID.randomUUID(), LocalDateTime.now(), new HashSet<>(), new ArrayList<>()));
        list.add(new UserEntity(2L, "login", "Str0ng.Passw0rd",
                "zaspamuj@wp.pl", "name", "surname", "999-999-999",
                A_RH_p, RCKiK.builder().build(), true, UUID.randomUUID(), LocalDateTime.now(), new HashSet<>(), new ArrayList<>()));
        list.add(new UserEntity(3L, "login", "Str0ng.Passw0rd",
                "zaspamuj@wp.pl", "name", "surname", "999-999-999",
                A_RH_p, RCKiK.builder().build(), true, UUID.randomUUID(), LocalDateTime.now(), new HashSet<>(), new ArrayList<>()));
        UserDto userDto = new UserDto(1L, "login", "Str0ng.Passw0rd",
                "zaspamuj@wp.pl", "name", "surname", "999-999-999",
                "A_RH_p");
        UserDto userDto2 = new UserDto(2L, "login", "Str0ng.Passw0rd",
                "zaspamuj@wp.pl", "name", "surname", "999-999-999",
                "A_RH_p");
        UserDto userDto3 = new UserDto(3L, "login", "Str0ng.Passw0rd",
                "zaspamuj@wp.pl", "name", "surname", "999-999-999",
                "A_RH_p");

        when(userMapper.mapToUser(userDto)).thenReturn();
        when(userRepository.findAll()).thenReturn(list);
        //when
        List<UserDto> expected = userService.getAllUsers();
        //then
        verify(userService, times(1)).save(user);

    }

    @Test
    void findUserByIdTest() {

        //given
        Long id = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setLogin("Login");
        //when
        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        final Optional<UserDto> expected = userService.getUser(id);
        //then
        assertThat(expected).isNotNull();
    }
}