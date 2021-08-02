package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.Basket;
import com.finartz.restaurantapp.repository.BasketRepository;
import com.finartz.restaurantapp.service.BasketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public Basket create(Basket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public List<Basket> getAll() {
        return basketRepository.findAll();
    }

    @Override
    public Basket getById(Long id) {
        return basketRepository.getById(id);
    }

    @Override
    public Basket update(Basket basket) {
        Basket foundBasket = basketRepository.getById(basket.getId());
        if (basket.getTotalPrice() != null)
            foundBasket.setTotalPrice(basket.getTotalPrice());
        if (basket.getUser() != null)
            foundBasket.setUser(basket.getUser());
        if (basket.getBasketMealList() != null)
            foundBasket.setBasketMealList(basket.getBasketMealList());

        return basketRepository.save(foundBasket);

    }

    @Override
    public Basket deleteById(Long id) {
        Basket basket = basketRepository.getById(id);
        if (basket != null) {
            basketRepository.deleteById(id);
            return basket;
        }
        return basket;
    }
}
