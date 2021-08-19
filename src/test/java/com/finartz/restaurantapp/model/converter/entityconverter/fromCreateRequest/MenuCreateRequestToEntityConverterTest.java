package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.create.MenuCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MenuCreateRequestToEntityConverterTest {

    @Spy
    private MenuCreateRequestToEntityConverter menuCreateRequestToEntityConverter;

    @Test
    public void whenPassValidMenuCreateRequest_thenReturnMenuEntity(){
        MenuCreateRequest menuCreateRequest = MenuCreateRequest.builder()
                .branchId(1l)
                .build();

        MenuEntity menuEntity = menuCreateRequestToEntityConverter.convert(menuCreateRequest);

        Assertions.assertEquals(menuEntity.getBranchEntity().getId(), menuCreateRequest.getBranchId());
    }

}
