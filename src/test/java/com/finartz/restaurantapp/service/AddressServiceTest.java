package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.repository.AddressRepository;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @Test
    public void whenFetchAll_thenReturnAllAddress() {
        Address address1 = Address.builder().id(1l).name("Ev").build();
        Address address2 = Address.builder().id(2l).name("İş").build();

        List<Address> addressList = Arrays.asList(address1, address2);
        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        List<Address> fetchedList = addressService.getAll();
        assertEquals(addressList.size(), fetchedList.size());
    }

    @Test
    public void whenAddAddress_thenReturnSavedRecord() {
        Address address = Address.builder().name("Ev").build();
        Mockito.doReturn(address).when(addressRepository).save(ArgumentMatchers.any());

        Address returnedAddress = addressService.create(address);

        assertEquals(address.getName(), returnedAddress.getName());
    }

}
