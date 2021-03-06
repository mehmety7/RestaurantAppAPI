package com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BranchCreateRequestToEntityConverterTest {

    @Spy
    @InjectMocks
    private BranchCreateRequestToEntityConverter branchCreateRequestToEntityConverter;

    @Test
    public void whenPassValidBranchCreateRequest_thenReturnBranchEntity(){

        AddressCreateRequest addressCreateRequest = AddressCreateRequest.builder().build();

        BranchCreateRequest branchCreateRequest = BranchCreateRequest.builder()
                .name("Branch")
                .restaurantId(1L)
                .addressCreateRequest(addressCreateRequest)
                .build();

        BranchEntity branchEntity = branchCreateRequestToEntityConverter.convert(branchCreateRequest);

        Assertions.assertEquals(branchEntity.getName(), branchCreateRequest.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullBranchCreateRequest_thenThrowEntityNotFoundException(){
        branchCreateRequestToEntityConverter.convert(null);
    }

}