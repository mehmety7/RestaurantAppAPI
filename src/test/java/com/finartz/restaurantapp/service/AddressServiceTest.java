package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.repository.AddressRepository;
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
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
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
    public void whenFetchById_thenReturnAddress() {
        Address address = Address.builder().name("Ev").district("Merkez Mahallesi").build();

        Mockito.when(addressRepository.getById(1L)).thenReturn(address);

        Address fetchedAddress = addressService.getById(1L);

        assertEquals(address.getId(), fetchedAddress.getId());
    }

    @Test
    public void whenAddAddress_thenReturnSavedAddress() {
        Address address = Address.builder().name("Ev").build();

        Mockito.doReturn(address).when(addressRepository).save(address);

        Address returnedAddress = addressService.create(address);

        assertEquals(address.getName(), returnedAddress.getName());
    }

    @Test
    public void whenUpdateAddress_thenReturnUpdatedAddress(){
        Address address = Address.builder().name("Ev").build();

        Mockito.when(addressRepository.save(address)).thenReturn(address);

        Address updatedAddress = addressService.update(address);

        assertEquals(address , updatedAddress);

    }

}
