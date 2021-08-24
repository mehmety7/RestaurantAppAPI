package com.finartz.restaurantapp.service;


import com.finartz.restaurantapp.model.converter.dtoconverter.BranchDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.BranchCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.repository.BranchRepository;
import com.finartz.restaurantapp.service.impl.AddressServiceImpl;
import com.finartz.restaurantapp.service.impl.BranchServiceImpl;
import com.finartz.restaurantapp.service.impl.RestaurantServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceTest {

    private static final String NAME_KB_UMRANIYE = "Kral Burger Ümraniye";
    private static final String NAME_KB_AVCILAR = "Kral Burger Avcılar";

    @InjectMocks
    private BranchServiceImpl branchService;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private RestaurantServiceImpl restaurantService;

    @Mock
    private AddressServiceImpl addressService;

    @Mock
    private BranchDtoConverter branchDtoConverter;

    @Mock
    private BranchCreateRequestToEntityConverter branchCreateRequestToEntityConverter;

    @Test
    public void whenFetchById_thenReturnBranch() {
        BranchEntity branchEntity = BranchEntity.builder().id(1l).name(NAME_KB_UMRANIYE).build();
        BranchDto branch = BranchDto.builder().id(1L).name(NAME_KB_UMRANIYE).build();

        Mockito.when(branchDtoConverter.convert(branchEntity)).thenReturn(branch);
        Mockito.when(branchRepository.findById(1L)).thenReturn(Optional.ofNullable(branchEntity));

        BranchDto result = branchService.getBranch(1L);

        assertEquals(branch.getId(), result.getId());
    }

    @Test
    public void whenFetchedByAddressEntity_CountyEntity_Id_thenReturnSomeBranches() {
        BranchEntity branchEntity = BranchEntity.builder().build();
        BranchDto branch = BranchDto.builder().build();

        List<BranchEntity> branchEntities = Arrays.asList(branchEntity);
        List<BranchDto> branches = Arrays.asList(branch);

        Mockito.when(branchDtoConverter.convert(branchEntity)).thenReturn(branch);
        Mockito.when(branchRepository.getBranchEntitiesByAddressEntity_CountyEntity_Id(anyLong())).thenReturn(branchEntities);

        List<BranchDto> result = branchService.getBranches(anyLong());

        assertEquals(result, branches);
    }

    @Test
    public void whenAddBranch_thenReturnSavedBranch() {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().id(1l).build();
        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder().build();
        AddressDto addressDto = AddressDto.builder().build();
        BranchEntity branchEntity = BranchEntity
                .builder()
                .name(NAME_KB_UMRANIYE)
                .restaurantEntity(restaurantEntity)
                .build();
        BranchDto branch = BranchDto
                .builder()
                .name(NAME_KB_UMRANIYE)
                .menuId(null)
                .restaurantId(restaurantEntity.getId())
                .build();
        BranchCreateRequest branchCreateRequest = BranchCreateRequest
                .builder()
                .restaurantId(restaurantEntity.getId())
                .addressCreateRequest(addressCreateRequest)
                .name(NAME_KB_UMRANIYE)
                .build();

        Mockito.when(branchCreateRequestToEntityConverter.convert(branchCreateRequest)).thenReturn(branchEntity);
        Mockito.when(branchDtoConverter.convert(branchEntity)).thenReturn(branch);
        Mockito.when(branchRepository.save(branchEntity)).thenReturn(branchEntity);
        Mockito.when(restaurantService.isRestaurantApproved(1l)).thenReturn(true);
        Mockito.doReturn(addressDto).when(addressService).createAddress(addressCreateRequest);

        BranchDto resultBranch = branchService.createBranch(branchCreateRequest, "jwt");

        assertEquals(branchEntity.getName(), resultBranch.getName());
    }

}
