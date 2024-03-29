package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.exception.InvalidStatusException;
import com.finartz.restaurantapp.exception.MissingArgumentsException;
import com.finartz.restaurantapp.model.converter.dtoconverter.AddressDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.AddressCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.request.create.AddressCreateRequest;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.AddressService;
import com.finartz.restaurantapp.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressDtoConverter addressDtoConverter;
    private final AddressCreateRequestToEntityConverter addressCreateRequestToEntityConverter;
    private final Validator validator;
    private final TokenService tokenService;

    @Override
    public AddressDto getAddress(Long id) {
        return addressDtoConverter.convert(addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Address with id:" + id)
        ));
    }

    @Override
    public List<AddressDto> getUserAddresses(Long userId) {
        List<AddressEntity> addressEntities = addressRepository.getAddressEntitiesByUserEntityId(userId);
        List<AddressDto> addresses = new ArrayList<>();
        addressEntities.forEach(addressEntity -> addresses.add(addressDtoConverter.convert(addressEntity)));
        return addresses;
    }

    @Override
    public AddressDto getBranchAddress(Long branchId) {
        return addressDtoConverter.convert(addressRepository.getAddressEntityByBranchEntityId(branchId));
    }

    @Override
    @Transactional
    public AddressDto createAddress(AddressCreateRequest addressCreateRequest) {
        if (addressCreateRequest.isFirst()) {
            throw new InvalidStatusException("Attempted to invalid creating address operation!");
        }
        tokenService.checkRequestOwnerAuthoritative(addressCreateRequest.getUserId());
        if(Objects.nonNull(addressCreateRequest.getUserId())){
            AddressEntity existingActiveAddress = addressRepository.getActiveAddressByUserId(addressCreateRequest.getUserId());
            if(Objects.nonNull(existingActiveAddress)){
                existingActiveAddress.setEnable(false);
                addressRepository.save(existingActiveAddress);
            }
        }

        Set<ConstraintViolation<AddressCreateRequest>> violations = validator.validate(addressCreateRequest);
        if (!violations.isEmpty()) {
            throw new MissingArgumentsException("Address fields may not be null");
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
