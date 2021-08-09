package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;

public interface AddressService {

    AddressDto getAddress(Long id);

    AddressDto createAddress(AddressCreateRequest addressCreateRequest);

}
