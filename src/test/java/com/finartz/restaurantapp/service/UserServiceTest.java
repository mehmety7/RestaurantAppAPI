package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.repository.UserRepository;
import com.finartz.restaurantapp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

    private final static String NAME_ALI_AKAY = "Ali Akay";
    private final static String NAME_VELI_KAZAK = "Veli Kazak";
    private final static String EMAIL_ALI_GMAIL_COM = "ali@gmail.com";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void whenFetchAll_thenReturnAllUser() {
        User user1 = User.builder().id(1l).name(NAME_ALI_AKAY).build();
        User user2 = User.builder().id(2l).name(NAME_VELI_KAZAK).build();
        List<User> userList = Arrays.asList(user1, user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> fetchedList = userService.getAll();

        assertEquals(userList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnUser() {
        User user = User.builder().name(NAME_ALI_AKAY).build();

        Mockito.when(userRepository.getById(1L)).thenReturn(user);

        User fetchedUser = userService.getById(1L);

        assertEquals(user.getId(), fetchedUser.getId());
    }

    @Test
    public void whenAddUser_thenReturnSavedUser() {
        User user = User.builder().name(NAME_ALI_AKAY).build();

        Mockito.doReturn(user).when(userRepository).save(user);

        User returnedUser = userService.create(user);

        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void whenFetchByEmail_thenReturnUser() {
        User user = User.builder().email(EMAIL_ALI_GMAIL_COM).build();

        Mockito.doReturn(user).when(userRepository).findByEmail(EMAIL_ALI_GMAIL_COM);

        User fetchedUser = userService.findByEmail(EMAIL_ALI_GMAIL_COM);

        assertEquals(user, fetchedUser);
    }

    @Test
    public void whenUpdateUser_thenReturnUpdatedUser(){
        User foundUser = User.builder().id(1l).name(NAME_ALI_AKAY).build();
        User modifyUser = User.builder().id(1l).name(NAME_VELI_KAZAK).build();

        Mockito.when(userRepository.getById(1l)).thenReturn(foundUser);
        Mockito.when(userRepository.save(modifyUser)).thenReturn(modifyUser);

        User updatedUser = userService.update(modifyUser);

        Assertions.assertNotEquals(updatedUser.getName(), NAME_ALI_AKAY);
        Assertions.assertEquals(updatedUser.getName(), NAME_VELI_KAZAK);

    }
}