package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidStatusException;
import com.finartz.restaurantapp.model.converter.dtoconverter.AddressDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.AddressCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.impl.AddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    private static final String NAME_EV = "Ev";
    private static final String DISTRICT_MERKEZ = "Merkez";
    private static final String OTHER_CONTENT_SOKAK_NO = "10. Sokak No5 D1";

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressDtoConverter addressDtoConverter;

    @Mock
    private AddressCreateRequestToEntityConverter addressCreateRequestToEntityConverter;

    @Mock
    private Validator validator;

    @Mock
    private TokenService tokenService;

    @Test
    public void whenFetchByValidId_thenReturnAddress() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        Mockito.when(addressRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(addressEntity));

        AddressDto result = addressService.getAddress(1L);

        assertEquals(result.getId(), address.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(addressRepository.findById(anyLong())).thenReturn(Optional.empty());
        addressService.getAddress(anyLong());

    }

    @Test
    public void whenFetchByUserId_thenReturnUserAddresses() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        Mockito.when(addressRepository.getAddressEntitiesByUserEntityId(1L)).thenReturn(Collections.singletonList(addressEntity));

        List<AddressDto> result = addressService.getUserAddresses(1L);

        assertEquals(result.get(0).getId(), address.getId());
    }

    @Test
    public void whenFetchByBranchId_thenReturnBranchAddress() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        Mockito.when(addressRepository.getAddressEntityByBranchEntityId(1L)).thenReturn(addressEntity);

        AddressDto result = addressService.getBranchAddress(1L);

        assertEquals(result.getId(), address.getId());
    }

    @Test
    public void givenValidCreatingArguments_whenAddAddress_thenReturnSavedAddress() {
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).userEntity(userEntity).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).userId(1L).build();
        AddressCreateRequest addressCreateRequest = AddressCreateRequest
                .builder()
                .name(NAME_EV)
                .district(DISTRICT_MERKEZ)
                .cityId(1L)
                .countyId(1L)
                .branchId(null)
                .otherContent(OTHER_CONTENT_SOKAK_NO)
                .userId(1L)
                .isFirst(false)
                .build();

        Mockito.doNothing().when(tokenService).checkRequestOwnerAuthoritative(anyLong());
        Mockito.doReturn(addressEntity).when(addressRepository).save(addressEntity);
        Mockito.doReturn(addressEntity).when(addressCreateRequestToEntityConverter).convert(addressCreateRequest);
        Mockito.doReturn(address).when(addressDtoConverter).convert(addressEntity);

        AddressEntity existingActiveAddressEntity = AddressEntity.builder().userEntity(userEntity).enable(true).build();
        Mockito.doReturn(existingActiveAddressEntity).when(addressRepository).getActiveAddressByUserId(1L);

        Set<ConstraintViolation<AddressCreateRequest>> violations = Collections.emptySet();
        Mockito.when(validator.validate(addressCreateRequest)).thenReturn(violations);

        AddressDto result = addressService.createAddress(addressCreateRequest);

        assertEquals(address.getName(), result.getName());
    }

    // to do - ask it how create constraint violation instance
    @Test(expected = InvalidStatusException.class)
    public void givenMissingCreatingArguments_whenAddInitialAddress_thenThrowMissingArgumentsException() {
        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder().userId(1L).isFirst(true).build();
        addressService.createAddress(addressCreateRequest);
    }

    @Test
    public void givenValidId_whenSetActiveAddress_thenReturnNothing(){
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        AddressEntity newActiveAddressEntity = AddressEntity.builder().id(1L).userEntity(userEntity).build();
        AddressEntity existActiveAddressEntity = AddressEntity.builder().id(2L).userEntity(userEntity).build();

        Mockito.doReturn(Optional.ofNullable(newActiveAddressEntity)).when(addressRepository).findById(1L);
        Mockito.doReturn(existActiveAddressEntity).when(addressRepository).getActiveAddressByUserId(1L);
        Mockito.doReturn(newActiveAddressEntity).when(addressRepository).save(Objects.requireNonNull(newActiveAddressEntity));
        Mockito.doReturn(existActiveAddressEntity).when(addressRepository).save(existActiveAddressEntity);

        addressService.setActiveAddress(1L);
        Mockito.verify(addressRepository, Mockito.atLeastOnce()).save(any());
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidId_whenSetActiveAddress_thenThrowEntityNotFoundException(){

        Mockito.when(addressRepository.findById(anyLong())).thenReturn(Optional.empty());
        addressService.setActiveAddress(anyLong());

    }


}
