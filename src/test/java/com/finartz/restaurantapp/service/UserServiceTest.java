package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void whenFetchAll_thenReturnAllUser() {
        User user1 = User.builder().id(1l).name("Ali Akay").build();
        User user2 = User.builder().id(2l).name("Avcılar").build();
        List<User> userList = Arrays.asList(user1, user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> fetchedList = userService.getAll();

        assertEquals(userList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnUser() {
        User user = User.builder().name("Ali Akay").build();

        Mockito.when(userRepository.getById(1L)).thenReturn(user);

        User fetchedUser = userService.getById(1L);

        assertEquals(user.getId(), fetchedUser.getId());
    }

    @Test
    public void whenAddUser_thenReturnSavedUser() {
        User user = User.builder().name("Ali Akay").build();

        Mockito.doReturn(user).when(userRepository).save(user);

        User returnedUser = userService.create(user);

        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void whenFetchByEmail_thenReturnUser() {
        User user = User.builder().email("ali@gmail.com").build();

        Mockito.doReturn(user).when(userRepository).findByEmail("ali@gmail.com");

        User fetchedUser = userService.findByEmail("ali@gmail.com");

        assertEquals(user, fetchedUser);
    }

}