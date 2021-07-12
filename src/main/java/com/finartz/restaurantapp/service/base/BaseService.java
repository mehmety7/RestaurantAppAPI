package com.finartz.restaurantapp.service.base;

import com.finartz.restaurantapp.repository.BaseRepository;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<Entity, Repository extends BaseRepository<Entity>> {

    public abstract Repository getRepository();

    public ServiceResult<List<Entity>> getAll(){
        List<Entity> entities = getRepository().findAll();
        return new ServiceResult<>(entities);
    }

    public ServiceResult<Entity> getById(Long id){
        Optional<Entity> entity = getRepository().findById(id);
        if(entity != null)
            return new ServiceResult<>(entity.get());
        else
            return new ServiceResult<>(HttpStatus.NOT_FOUND);
    }

    public ServiceResult<Entity> create(Entity entity){
        Entity save = getRepository().save(entity);
        return new ServiceResult<>(save);
    }

    public ServiceResult<Entity> update(Entity entity){
        if(entity != null){
            Entity update = getRepository().save(entity);
            return new ServiceResult<>(entity);
        }
        return new ServiceResult<>(HttpStatus.NOT_FOUND);
    }

    public ServiceResult<Entity> deleteById(Entity entity){
        if(entity != null){
            getRepository().delete(entity);
            return new ServiceResult<>(HttpStatus.NO_CONTENT);
        }
        return new ServiceResult<>(HttpStatus.NOT_FOUND);
    }


}
