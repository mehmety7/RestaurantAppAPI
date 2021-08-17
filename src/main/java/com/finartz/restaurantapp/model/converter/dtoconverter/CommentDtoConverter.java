package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDtoConverter implements GenericConverter<CommentEntity, CommentDto> {

    @Override
    public CommentDto convert(final CommentEntity commentEntity){
        if(commentEntity == null){
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId(commentEntity.getId());
        commentDto.setComment(commentEntity.getComment());
        commentDto.setSpeedPoint(commentEntity.getSpeedPoint());
        commentDto.setTastePoint(commentEntity.getTastePoint());
        commentDto.setUserId(commentEntity.getUserEntity().getId());
        commentDto.setUserName(commentEntity.getUserEntity().getName());
        commentDto.setBranchId(commentEntity.getBranchEntity().getId());
        commentDto.setBranchName(commentEntity.getBranchEntity().getName());

        return commentDto;
    }

}
