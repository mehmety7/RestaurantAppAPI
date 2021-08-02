package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.repository.AddressRepository;
import com.finartz.restaurantapp.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.getById(id);
    }

    @Override
    public Address update(Address address) {
        Address foundAddress = addressRepository.getById(address.getId());

        if (address.getCity() != null)
            foundAddress.setCity(address.getCity());
        if (address.getCounty() != null)
            foundAddress.setCounty(address.getCounty());
        if (address.getName() != null)
            foundAddress.setName(address.getName());
        if (address.getDistrict() != null)
            foundAddress.setDistrict(address.getDistrict());
        if (address.getOther_content() != null)
            foundAddress.setOther_content(address.getOther_content());
        if (address.getEnable() != null)
            foundAddress.setEnable(address.getEnable());

        return addressRepository.save(foundAddress);

    }

    @Override
    public Address deleteById(Long id) {
        Address address = addressRepository.getById(id);
        if (address != null) {
            addressRepository.deleteById(id);
            return address;
        }
        return address;
    }
}
