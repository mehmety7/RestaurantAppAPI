package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.AddressDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.AddressCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressDtoConverter addressDtoConverter;
    private final AddressCreateRequestToEntityConverter addressCreateRequestToEntityConverter;

    @Override
    public AddressDto getAddress(Long id) {
        return addressDtoConverter.convert(addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Address with id:" + id)
        ));
    }

    @Override
    public List<AddressDto> getUserAddress(Long user_id) {
        List<AddressEntity> addressEntities = addressRepository.getAddressEntitiesByUserEntity_Id(user_id);
        List<AddressDto> addresses = new ArrayList<>();
        addressEntities.forEach(addressEntity -> {
            addresses.add(addressDtoConverter.convert(addressEntity));
        });
        return addresses;
    }

    @Override
    public AddressDto getBranchAddress(Long branch_id) {
        return addressDtoConverter.convert(addressRepository.getAddressEntityByBranchEntity_Id(branch_id));
    }

    @Override
    @Transactional
    public AddressDto createAddress(AddressCreateRequest addressCreateRequest) {
        if(addressCreateRequest.getUserId() != null){
            AddressEntity existingActiveAddress = addressRepository.getActiveAddressByUserId(addressCreateRequest.getUserId());
            existingActiveAddress.setEnable(false);
            addressRepository.save(existingActiveAddress);
        }

        AddressEntity addressEntity = addressRepository.save(addressCreateRequestToEntityConverter.convert(addressCreateRequest));
        return addressDtoConverter.convert(addressEntity);
    }

    @Override
    @Transactional
    public void setActiveAddress(Long id) {
        AddressEntity newActiveAddress = addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Address with id: " + id)
        );

        AddressEntity existingActiveAddress = addressRepository.getActiveAddressByUserId(newActiveAddress.getUserEntity().getId());
        existingActiveAddress.setEnable(false);
        addressRepository.save(existingActiveAddress);

        newActiveAddress.setEnable(true);

        addressRepository.save(newActiveAddress);
    }

}
