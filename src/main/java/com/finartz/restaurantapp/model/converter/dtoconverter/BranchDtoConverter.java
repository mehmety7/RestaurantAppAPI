package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchDtoConverter implements GenericConverter<BranchEntity, BranchDto> {

    @Override
    public BranchDto convert(final BranchEntity branchEntity){
        if(branchEntity == null){
            return null;
        }

        BranchDto branchDto = new BranchDto();

        branchDto.setId(branchEntity.getId());
        branchDto.setName(branchEntity.getName());
        branchDto.setMenuId(branchEntity.getMenuEntity().getId());
        branchDto.setRestaurantId(branchEntity.getRestaurantEntity().getId());

        return branchDto;
    }

}
