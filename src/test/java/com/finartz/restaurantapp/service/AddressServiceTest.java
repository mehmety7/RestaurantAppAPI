package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dto.AddressDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.AddressRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.impl.AddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
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

    @Mock
    private AddressDtoConverter addressDtoConverter;

    @Mock
    private AddressRequestToEntityConverter addressRequestToEntityConverter;


    @Test
    public void whenFetchAll_thenReturnAllAddress() {
        AddressEntity addressEntity = AddressEntity.builder().id(1l).name(NAME_EV).build();
        List<AddressEntity> addressEntities = Arrays.asList(addressEntity);
        List<AddressDto> addresses = new ArrayList<>();

        AddressDto address = AddressDto.builder().id(2l).name(NAME_IS).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        addressEntities.forEach(addressEntity1 -> {
            addresses.add(addressDtoConverter.convert(addressEntity1));
        });

        Mockito.when(addressRepository.findAll()).thenReturn(addressEntities);

        List<AddressDto> fetchedList = addressService.getAddresses();

        assertEquals(addressEntities.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnAddress() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        Mockito.when(addressRepository.getById(1L)).thenReturn(addressEntity);

        address = addressService.getAddress(1L);

        assertEquals(addressEntity.getId(), address.getId());
    }

//    @Test
//    public void whenAddAddress_thenReturnSavedAddress() {
//        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).build();
//
//        Mockito.doReturn(addressEntity).when(addressRepository).save(addressEntity);
//
//        AddressEntity returnedAddressEntity = addressService.createAddress(addressEntity);
//
//        assertEquals(addressEntity.getName(), returnedAddressEntity.getName());
//    }
//
//    @Test
//    public void whenUpdateAddress_thenReturnUpdatedAddress(){
//        AddressEntity foundAddressEntity = AddressEntity.builder().id(1l).name(NAME_EV).build();
//        AddressEntity modifyAddressEntity = AddressEntity.builder().id(1l).name(NAME_IS).build();
//
//        Mockito.when(addressRepository.getById(1l)).thenReturn(foundAddressEntity);
//        Mockito.when(addressRepository.save(modifyAddressEntity)).thenReturn(modifyAddressEntity);
//
//        AddressEntity updatedAddressEntity = addressService.updateAddress(modifyAddressEntity);
//
//        Assertions.assertNotEquals(updatedAddressEntity.getName(), NAME_EV);
//        Assertions.assertEquals(updatedAddressEntity.getName(), NAME_IS);
//
//    }

}
