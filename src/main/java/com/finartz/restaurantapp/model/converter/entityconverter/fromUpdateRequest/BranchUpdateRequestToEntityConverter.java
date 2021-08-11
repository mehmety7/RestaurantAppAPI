package com.finartz.restaurantapp.model.converter.entityconverter.fromUpdateRequest;

import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.request.update.BranchUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchUpdateRequestToEntityConverter {

    public BranchEntity convert(final BranchUpdateRequest branchUpdateRequest,
                                final BranchEntity branchExisted) {

        if(branchUpdateRequest == null)
            return null;

        if(branchUpdateRequest.getStatus() != null)
            branchExisted.setStatus(branchUpdateRequest.getStatus());

        return branchExisted;
    }

}