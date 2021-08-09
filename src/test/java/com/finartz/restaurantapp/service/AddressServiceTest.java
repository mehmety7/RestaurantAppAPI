package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dto.AddressDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.fromCreateRequest.AddressCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.impl.AddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
    private AddressCreateRequestToEntityConverter addressCreateRequestToEntityConverter;

    @Test
    public void whenFetchById_thenReturnAddress() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        Mockito.when(addressRepository.getById(1L)).thenReturn(addressEntity);

        AddressDto result = addressService.getAddress(1L);

        assertEquals(result.getId(), address.getId());
    }

    @Test
    public void whenAddAddress_thenReturnSavedAddress() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).build();
        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder().build();

        Mockito.doReturn(addressEntity).when(addressRepository).save(addressEntity);
        Mockito.doReturn(addressEntity).when(addressCreateRequestToEntityConverter).convert(addressCreateRequest);
        Mockito.doReturn(address).when(addressDtoConverter).convert(addressEntity);

        AddressDto result = addressService.createAddress(addressCreateRequest);

        assertEquals(address.getName(), result.getName());
    }



}
