package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.repository.AddressRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @Test
    public void whenFetchAll_thenReturnAllAddress() throws Exception {
        Address address1 = Address.builder().id(1l).name("Ev").build();
        Address address2 = Address.builder().id(2l).name("İş").build();

        List<Address> addressList = Arrays.asList(address1, address1);
        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        List<Address> fetchedList = addressService.getAll();
        Assertions.assertEquals(addressList.size(), fetchedList.size());
    }

}
