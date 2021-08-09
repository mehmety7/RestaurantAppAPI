package com.finartz.restaurantapp.model.converter.entity.fromUpdateRequest;

import com.finartz.restaurantapp.model.converter.entity.fromDto.MenuDtoToEntityConverter;
import com.finartz.restaurantapp.model.dto.MenuDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.MenuEntity;
import com.finartz.restaurantapp.model.request.update.BranchUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchUpdateRequestToEntityConverter {

    private final MenuDtoToEntityConverter menuDtoToEntityConverter;

    public BranchEntity convert(final BranchUpdateRequest branchUpdateRequest,
                                final BranchEntity branchExisted) {

        if(branchUpdateRequest == null)
            return null;

        if(branchUpdateRequest.getStatus() != null)
            branchExisted.setStatus(branchUpdateRequest.getStatus());

        if(branchUpdateRequest.getMenu() != null)
            branchExisted.setMenuEntity(convert(branchUpdateRequest.getMenu()));

        return branchExisted;
    }

    private MenuEntity convert(final MenuDto menu){
        return menuDtoToEntityConverter.convert(menu);
    }
}
