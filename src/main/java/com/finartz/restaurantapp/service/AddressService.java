package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;

import java.util.List;

public interface AddressService {

    AddressDto getAddress(Long id);

    List<AddressDto> getUserAddresses(Long userId);

    AddressDto getBranchAddress(Long branchId);

    AddressDto createAddress(AddressCreateRequest addressCreateRequest);

    void setActiveAddress(Long id);

}
