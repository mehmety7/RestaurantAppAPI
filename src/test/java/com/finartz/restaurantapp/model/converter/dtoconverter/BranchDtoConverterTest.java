package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BranchDtoConverterTest {

    @Spy
    private BranchDtoConverter branchDtoConverter;

    @Test
    public void whenPassValidBranchEntity_thenReturnBranchDto(){
        BranchEntity branchEntity = BranchEntity.builder()
                .id(1L)
                .name("Branch")
                .restaurantEntity(RestaurantEntity.builder().id(1L).build())
                .menuEntity(MenuEntity.builder().id(1L).build())
                .build();

        BranchDto branchDto = branchDtoConverter.convert(branchEntity);

        Assertions.assertEquals(branchDto.getName(), branchEntity.getName());

    }

    @Test
    public void whenPassValidBranchEntityWithoutMenuId_thenReturnBranchDto(){
        BranchEntity branchEntity = BranchEntity.builder()
                .id(1L)
                .name("Branch")
                .restaurantEntity(RestaurantEntity.builder().id(1L).build())
                .menuEntity(null)
                .build();

        BranchDto branchDto = branchDtoConverter.convert(branchEntity);

        Assertions.assertEquals(branchDto.getName(), branchEntity.getName());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullAddressEntity_thenThrowEntityNotFoundException(){

        branchDtoConverter.convert(null);

    }

}
