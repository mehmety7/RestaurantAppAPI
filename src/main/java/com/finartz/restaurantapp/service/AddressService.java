package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.request.AddressRequest;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAddresses();

    AddressDto getAddress(Long id);

    AddressDto createAddress(AddressRequest addressRequest);

    AddressDto updateAddress(AddressRequest addressRequest);

    AddressDto deleteAddress(Long id);

}
