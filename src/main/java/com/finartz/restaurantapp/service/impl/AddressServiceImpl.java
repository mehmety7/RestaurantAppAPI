package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dto.AddressDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.AddressDtoToEntityConverter;
import com.finartz.restaurantapp.model.converter.entity.AddressRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.request.AddressRequest;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressDtoConverter addressDtoConverter;
    private final AddressRequestToEntityConverter addressRequestToEntityConverter;
    private final AddressDtoToEntityConverter addressDtoToEntityConverter;

    @Override
    public List<AddressDto> getAddresses() {
        List<AddressEntity> addressEntities = addressRepository.findAll();
        List<AddressDto> addresses = new ArrayList<>();
        addressEntities.forEach(addressEntity -> {
            addresses.add(addressDtoConverter.convert(addressEntity));
        });
        return addresses;
    }

    @Override
    public AddressDto getAddress(Long id) {
        AddressEntity addressEntity = addressRepository.getById(id);
        return addressDtoConverter.convert(addressEntity);
    }

    @Override
    public AddressDto createAddress(AddressRequest addressRequest) {
        AddressEntity addressEntity = addressRepository.save(addressRequestToEntityConverter.convert(addressRequest));
        return addressDtoConverter.convert(addressEntity);
    }

    @Override
    public AddressDto updateAddress(AddressRequest addressRequest) {
        AddressEntity addressEntity = addressRequestToEntityConverter.convert(addressRequest);
        AddressEntity foundAddressEntity = addressRepository.getById(addressEntity.getId());

        if (addressEntity.getCityEntity() != null)
            foundAddressEntity.setCityEntity(addressEntity.getCityEntity());
        if (addressEntity.getCountyEntity() != null)
            foundAddressEntity.setCountyEntity(addressEntity.getCountyEntity());
        if (addressEntity.getName() != null)
            foundAddressEntity.setName(addressEntity.getName());
        if (addressEntity.getDistrict() != null)
            foundAddressEntity.setDistrict(addressEntity.getDistrict());
        if (addressEntity.getOtherContent() != null)
            foundAddressEntity.setOtherContent(addressEntity.getOtherContent());
        if (addressEntity.getEnable() != null)
            foundAddressEntity.setEnable(addressEntity.getEnable());

        addressRepository.save(addressEntity);

        return addressDtoConverter.convert(addressEntity);

    }

    @Override
    public AddressDto deleteAddress(Long id) {
        AddressEntity addressEntity = addressRepository.getById(id);
        if (addressEntity != null) {
            addressRepository.deleteById(id);
            return addressDtoConverter.convert(addressEntity);
        }
        return null;
    }
}
