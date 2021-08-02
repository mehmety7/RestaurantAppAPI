package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Address;

import java.util.List;

public interface AddressService {

    public Address create(Address address);

    public List<Address> getAll();

    public Address getById(Long id);

    public Address update(Address address);

    public Address deleteById(Long id);

}
