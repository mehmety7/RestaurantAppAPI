package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.CreditCard;

import java.util.List;

public interface CreditCardService {

    public CreditCard create(CreditCard creditCard);

    public List<CreditCard> getAll();

    public CreditCard getById(Long id);

    public CreditCard update(CreditCard creditCard);

    public CreditCard deleteById(Long id);
}
