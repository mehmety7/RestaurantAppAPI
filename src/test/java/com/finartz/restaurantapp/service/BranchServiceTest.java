package com.finartz.restaurantapp.service;


import com.finartz.restaurantapp.model.converter.dtoconverter.BranchDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.BranchCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromUpdateRequest.BranchUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.model.request.update.BranchUpdateRequest;
import com.finartz.restaurantapp.repository.BranchRepository;
import com.finartz.restaurantapp.service.impl.BranchServiceImpl;
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
    private BranchDtoConverter branchDtoConverter;

    @Mock
    private BranchCreateRequestToEntityConverter branchCreateRequestToEntityConverter;

    @Mock
    private BranchUpdateRequestToEntityConverter branchUpdateRequestToEntityConverter;

    @Test
    public void whenFetchById_thenReturnBranch() {
        BranchEntity branchEntity = BranchEntity.builder().name(NAME_KB_UMRANIYE).build();
        BranchDto branch = BranchDto.builder().id(1L).name(NAME_KB_UMRANIYE).build();

        Mockito.when(branchDtoConverter.convert(branchEntity)).thenReturn(branch);
        Mockito.when(branchRepository.getById(1L)).thenReturn(branchEntity);

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
    public void whenFetchedByStatus_thenReturnSomeBranches() {
        BranchEntity branchEntity = BranchEntity.builder().status(Status.WAITING).build();
        BranchDto branch = BranchDto.builder().status(Status.WAITING).build();

        List<BranchEntity> branchEntityList = Arrays.asList(branchEntity);
        List<BranchDto> branches = Arrays.asList(branch);

        Mockito.when(branchDtoConverter.convert(branchEntity)).thenReturn(branch);
        Mockito.when(branchRepository.getBranchEntitiesByStatus(Status.WAITING)).thenReturn(branchEntityList);

        List<BranchDto> resultBranches = branchService.getBranches(Status.WAITING);

        assertEquals(branches, resultBranches);
    }

    @Test
    public void whenAddBranch_thenReturnSavedBranch() {
        BranchEntity branchEntity = BranchEntity.builder().name(NAME_KB_UMRANIYE).build();
        BranchDto branch = BranchDto.builder().name(NAME_KB_UMRANIYE).build();
        BranchCreateRequest branchCreateRequest = BranchCreateRequest.builder().build();

        Mockito.when(branchCreateRequestToEntityConverter.convert(branchCreateRequest)).thenReturn(branchEntity);
        Mockito.when(branchDtoConverter.convert(branchEntity)).thenReturn(branch);
        Mockito.when(branchRepository.save(branchEntity)).thenReturn(branchEntity);

        BranchDto resultBranch = branchService.createBranch(branchCreateRequest);

        assertEquals(branchEntity.getName(), resultBranch.getName());
    }

    @Test
    public void whenUpdateBranch_thenReturnUpdatedBranch(){
        BranchEntity branchEntity = BranchEntity.builder().id(1l).status(Status.WAITING).build();
        BranchEntity branchEntityUpdated = BranchEntity.builder().id(1l).status(Status.APPROVED).build();
        BranchDto branch = BranchDto.builder().status(Status.WAITING).build();
        BranchDto branchUpdated = BranchDto.builder().status(Status.APPROVED).build();
        BranchUpdateRequest branchUpdateRequest = BranchUpdateRequest.builder().build();

        Mockito.when(branchRepository.getById(1l)).thenReturn(branchEntity);
        Mockito.when(branchUpdateRequestToEntityConverter.convert(branchUpdateRequest, branchEntity)).thenReturn(branchEntityUpdated);
        Mockito.when(branchRepository.save(branchEntityUpdated)).thenReturn(branchEntityUpdated);
        Mockito.when(branchDtoConverter.convert(branchEntityUpdated)).thenReturn(branchUpdated);

        BranchDto resultBranch = branchService.updateBranch(1L, branchUpdateRequest);

        Assertions.assertNotEquals(branch.getStatus(), Status.APPROVED);
        Assertions.assertEquals(resultBranch.getStatus(), Status.APPROVED);

    }

}
