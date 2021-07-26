package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Basket;
import com.finartz.restaurantapp.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    public Basket create(Basket basket){
        Basket save = basketRepository.save(basket);
        return save;
    }

    public List<Basket> getAll(){
        List<Basket> baskets = basketRepository.findAll();
        return baskets;
    }

    public Basket getById(Long id){
        Basket basket = basketRepository.getById(id);
        return basket;
    }

    public Basket update(Basket basket){
        Basket update = basketRepository.getById(basket.getId());
        if(update != null) {
            basketRepository.save(basket);
            return update;
        }
        return basket;
    }

    public Basket deleteById(Long id){
        Basket basket = basketRepository.getById(id);
        if (basket != null) {
            basketRepository.deleteById(id);
            return basket;
        }
        return basket;
    }
}
