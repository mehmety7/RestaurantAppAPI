package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.repository.MenuRepository;
import com.finartz.restaurantapp.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuEntity> getMenus(){
        return menuRepository.findAll();
    }

    @Override
    public MenuEntity getMenu(Long id){
        return menuRepository.getById(id);
    }

    @Override
    public MenuEntity createMenu(MenuEntity menuEntity){
        return menuRepository.save(menuEntity);
    }

    @Override
    public MenuEntity updateMenu(MenuEntity menuEntity) {
        MenuEntity foundMenuEntity = menuRepository.getById(menuEntity.getId());

        if (menuEntity.getBranchEntity() != null)
            foundMenuEntity.setBranchEntity(menuEntity.getBranchEntity());
        if (menuEntity.getMealEntities() != null)
            foundMenuEntity.setMealEntities(menuEntity.getMealEntities());

        return menuRepository.save(foundMenuEntity);
    }

    @Override
    public MenuEntity deleteMenu(Long id){
        MenuEntity menuEntity = menuRepository.getById(id);
        if (menuEntity != null) {
            menuRepository.deleteById(id);
            return menuEntity;
        }
        return menuEntity;
    }
}

