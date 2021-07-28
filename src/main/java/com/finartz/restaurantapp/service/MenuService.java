package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Menu;
import com.finartz.restaurantapp.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu create(Menu menu){
        return menuRepository.save(menu);
    }

    public List<Menu> getAll(){
        return menuRepository.findAll();
    }

    public Menu getById(Long id){
        return menuRepository.getById(id);
    }

    public Menu update(Menu menu){
        Menu update = menuRepository.getById(menu.getId());
        if(update != null) {
            menuRepository.save(menu);
            return update;
        }
        return menu;
    }

    public Menu deleteById(Long id){
        Menu menu = menuRepository.getById(id);
        if (menu != null) {
            menuRepository.deleteById(id);
            return menu;
        }
        return menu;
    }
}

