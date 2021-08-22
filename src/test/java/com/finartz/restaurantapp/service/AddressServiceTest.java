package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dtoconverter.AddressDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.AddressCreateRequestToEntityConverter;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void whenFetchById_thenReturnAddress() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        Mockito.when(addressRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(addressEntity));

        AddressDto result = addressService.getAddress(1L);

        assertEquals(result.getId(), address.getId());
    }

    @Test
    public void whenFetchByUserId_thenReturnUserAddresses() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        Mockito.when(addressRepository.getAddressEntitiesByUserEntity_Id(1L)).thenReturn(Arrays.asList(addressEntity));

        List<AddressDto> result = addressService.getUserAddresses(1L);

        assertEquals(result.get(0).getId(), address.getId());
    }

    @Test
    public void whenFetchByBranchId_thenReturnBranchAddress() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);
        Mockito.when(addressRepository.getAddressEntityByBranchEntity_Id(1L)).thenReturn(addressEntity);

        AddressDto result = addressService.getBranchAddress(1L);

        assertEquals(result.getId(), address.getId());
    }

    @Test
    public void whenAddAddress_thenReturnSavedAddress() {
        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).build();
        AddressCreateRequest addressCreateRequest = AddressCreateRequest
                .builder()
                .name(NAME_EV)
                .district(DISTRICT_MERKEZ)
                .cityId(1l)
                .countyId(1l)
                .branchId(null)
                .otherContent(OTHER_CONTENT_SOKAK_NO)
                .userId(1l)
                .build();

        Mockito.doReturn(addressEntity).when(addressRepository).save(addressEntity);
        Mockito.doReturn(addressEntity).when(addressCreateRequestToEntityConverter).convert(addressCreateRequest);
        Mockito.doReturn(address).when(addressDtoConverter).convert(addressEntity);

        AddressEntity existingActiveAddressEntity = AddressEntity.builder().enable(true).build();
        Mockito.doReturn(existingActiveAddressEntity).when(addressRepository).getActiveAddressByUserId(1L);

        AddressDto result = addressService.createAddress(addressCreateRequest);

        assertEquals(address.getName(), result.getName());
    }

    @Test
    public void whenSetActiveAddress_thenReturnNothing(){
        UserEntity userEntity = UserEntity.builder().id(1l).build();
        AddressEntity newActiveAddressEntity = AddressEntity.builder().id(1l).userEntity(userEntity).build();
        AddressEntity existActiveAddressEntity = AddressEntity.builder().id(2l).userEntity(userEntity).build();

        Mockito.doReturn(Optional.ofNullable(newActiveAddressEntity)).when(addressRepository).findById(1l);
        Mockito.doReturn(existActiveAddressEntity).when(addressRepository).getActiveAddressByUserId(1l);
        Mockito.doReturn(newActiveAddressEntity).when(addressRepository).save(newActiveAddressEntity);
        Mockito.doReturn(existActiveAddressEntity).when(addressRepository).save(existActiveAddressEntity);

        addressService.setActiveAddress(1l);
    }


}
