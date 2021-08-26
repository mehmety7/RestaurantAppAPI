package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BranchDtoConverter implements GenericConverter<BranchEntity, BranchDto> {

    @Override
    public BranchDto convert(final BranchEntity branchEntity){
        if(branchEntity == null){
            return null;
        }

        BranchDto branchDto = new BranchDto();

        if(Objects.nonNull(branchEntity.getId())){
            branchDto.setId(branchEntity.getId());
        }        branchDto.setName(branchEntity.getName());
        if (Objects.nonNull(branchEntity.getMenuEntity())) {
            branchDto.setMenuId(branchEntity.getMenuEntity().getId());
        }
        else {
            branchDto.setMenuId(null);
        }
        if(Objects.nonNull(branchEntity.getRestaurantEntity()) && Objects.nonNull(branchEntity.getRestaurantEntity().getId())) {
            branchDto.setRestaurantId(branchEntity.getRestaurantEntity().getId());
        }

        return branchDto;
    }

}
