package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Menu;
import com.finartz.restaurantapp.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu create(Menu menu){
        Menu save = menuRepository.save(menu);
        return save;
    }

    public List<Menu> getAll(){
        List<Menu> menus = menuRepository.findAll();
        return menus;
    }

    public Menu getById(Long id){
        Menu menu = menuRepository.getById(id);
        return menu;
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

