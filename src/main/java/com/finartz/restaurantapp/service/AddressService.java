package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address create(Address address){
        Address save = addressRepository.save(address);
        return save;
    }

    public List<Address> getAll(){
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    public Address getById(Long id){
        Address address = addressRepository.getById(id);
        return address;
    }

    public Address update(Address address){
        Address update = addressRepository.getById(address.getId());
        if(update != null) {
            addressRepository.save(address);
            return update;
        }
        return address;
    }

    public Address deleteById(Long id){
        Address address = addressRepository.getById(id);
        if (address != null) {
            addressRepository.deleteById(id);
            return address;
        }
        return address;
    }
}
