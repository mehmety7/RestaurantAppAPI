package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dto.AddressDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.fromCreateRequest.AddressCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressDtoConverter addressDtoConverter;
    private final AddressCreateRequestToEntityConverter addressCreateRequestToEntityConverter;

    @Override
    public AddressDto getAddress(Long id) {
        AddressEntity addressEntity = addressRepository.getById(id);
        return addressDtoConverter.convert(addressEntity);
    }

    @Override
    public AddressDto createAddress(AddressCreateRequest addressCreateRequest) {
        AddressEntity addressEntity = addressCreateRequestToEntityConverter.convert(addressCreateRequest);
        return addressDtoConverter.convert(addressRepository.save(addressEntity));
    }

}
