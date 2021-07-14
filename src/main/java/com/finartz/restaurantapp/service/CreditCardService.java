package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.CreditCard;
import com.finartz.restaurantapp.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard create(CreditCard creditCard){
        CreditCard save = creditCardRepository.save(creditCard);
        return save;
    }

    public List<CreditCard> getAll(){
        List<CreditCard> creditCards = creditCardRepository.findAll();
        return creditCards;
    }

    public CreditCard getById(Long id){
        CreditCard creditCard = creditCardRepository.getById(id);
        return creditCard;
    }

    public CreditCard update(CreditCard creditCard){
        CreditCard update = creditCardRepository.getById(creditCard.getId());
        if(update != null) {
            creditCardRepository.save(creditCard);
            return update;
        }
        return creditCard;
    }

    public CreditCard deleteById(Long id){
        CreditCard creditCard = creditCardRepository.getById(id);
        if (creditCard != null) {
            creditCardRepository.deleteById(id);
            return creditCard;
        }
        return creditCard;
    }
}
