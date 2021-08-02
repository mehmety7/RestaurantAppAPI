package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.CreditCard;
import com.finartz.restaurantapp.repository.CreditCardRepository;
import com.finartz.restaurantapp.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard create(CreditCard creditCard){
        CreditCard save = creditCardRepository.save(creditCard);
        return save;
    }

    @Override
    public List<CreditCard> getAll(){
        List<CreditCard> creditCards = creditCardRepository.findAll();
        return creditCards;
    }

    @Override
    public CreditCard getById(Long id){
        CreditCard creditCard = creditCardRepository.getById(id);
        return creditCard;
    }

    @Override
    public CreditCard update(CreditCard creditCard){
        CreditCard foundCreditCard = creditCardRepository.getById(creditCard.getId());

        if(creditCard.getNickname() != null)
            foundCreditCard.setNickname(creditCard.getNickname());
        if(creditCard.getName() != null)
            foundCreditCard.setName(creditCard.getName());
        if(creditCard.getCardNo() != null)
            foundCreditCard.setCardNo(creditCard.getCardNo());
        if(creditCard.getExpMonth() != null)
            foundCreditCard.setExpMonth(creditCard.getExpMonth());
        if(creditCard.getExpYear() != null)
            foundCreditCard.setExpYear(creditCard.getExpYear());
        if(creditCard.getCcv() != null)
            foundCreditCard.setCcv(creditCard.getCcv());
        if(creditCard.getUser() != null)
            foundCreditCard.setUser(creditCard.getUser());

        return creditCardRepository.save(foundCreditCard);

    }

    @Override
    public CreditCard deleteById(Long id){
        CreditCard creditCard = creditCardRepository.getById(id);
        if (creditCard != null) {
            creditCardRepository.deleteById(id);
            return creditCard;
        }
        return creditCard;
    }
}
