package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
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
                .id(1l)
                .name("Branch")
                .build();

        BranchDto branchDto = branchDtoConverter.convert(branchEntity);

        Assertions.assertEquals(branchDto.getName(), branchEntity.getName());

    }

}
