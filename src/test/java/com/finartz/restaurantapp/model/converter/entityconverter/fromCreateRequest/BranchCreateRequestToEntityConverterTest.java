package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BranchCreateRequestToEntityConverterTest {

    @Spy
    @InjectMocks
    private BranchCreateRequestToEntityConverter branchCreateRequestToEntityConverter;

    @Mock
    private AddressCreateRequestToEntityConverter addressCreateRequestToEntityConverter;

    @Test
    public void whenPassValidBranchCreateRequest_thenReturnBranchEntity(){
        BranchCreateRequest branchCreateRequest = BranchCreateRequest.builder()
                .name("Branch")
                .build();

        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder().build();
        AddressEntity addressEntity = AddressEntity.builder().build();

        Mockito.when(addressCreateRequestToEntityConverter.convert(addressCreateRequest)).thenReturn(addressEntity);
        BranchEntity branchEntity = branchCreateRequestToEntityConverter.convert(branchCreateRequest);

        Assertions.assertEquals(branchEntity.getName(), branchCreateRequest.getName());
    }

}