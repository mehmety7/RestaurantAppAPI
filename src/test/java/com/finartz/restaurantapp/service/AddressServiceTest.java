package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.impl.AddressServiceImpl;
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
public class AddressServiceTest {

    private static final String NAME_EV = "Ev";
    private static final String NAME_IS = "İş";
    private static final String DISTRICT_MERKEZ = "Merkez";

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;


    @Test
    public void whenFetchAll_thenReturnAllAddress() {
        Address address1 = Address.builder().id(1l).name(NAME_EV).build();
        Address address2 = Address.builder().id(2l).name(NAME_IS).build();
        List<Address> addressList = Arrays.asList(address1, address2);

        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        List<Address> fetchedList = addressService.getAll();

        assertEquals(addressList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnAddress() {
        Address address = Address.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressRepository.getById(1L)).thenReturn(address);

        Address fetchedAddress = addressService.getById(1L);

        assertEquals(address.getId(), fetchedAddress.getId());
    }

    @Test
    public void whenAddAddress_thenReturnSavedAddress() {
        Address address = Address.builder().name(NAME_EV).build();

        Mockito.doReturn(address).when(addressRepository).save(address);

        Address returnedAddress = addressService.create(address);

        assertEquals(address.getName(), returnedAddress.getName());
    }

    @Test
    public void whenUpdateAddress_thenReturnUpdatedAddress(){
        Address foundAddress = Address.builder().id(1l).name(NAME_EV).build();
        Address modifyAddress = Address.builder().id(1l).name(NAME_IS).build();

        Mockito.when(addressRepository.getById(1l)).thenReturn(foundAddress);
        Mockito.when(addressRepository.save(modifyAddress)).thenReturn(modifyAddress);

        Address updatedAddress = addressService.update(modifyAddress);

        Assertions.assertNotEquals(updatedAddress.getName(), NAME_EV);
        Assertions.assertEquals(updatedAddress.getName(), NAME_IS);

    }

}
