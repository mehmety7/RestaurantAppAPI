package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommentDtoConverter implements GenericConverter<CommentEntity, CommentDto> {

    @Override
    public CommentDto convert(final CommentEntity commentEntity){
        if(Objects.isNull(commentEntity)){
            return null;
        }

        CommentDto commentDto = new CommentDto();

        if(Objects.nonNull(commentEntity.getId())){
            commentDto.setId(commentEntity.getId());
        }
        if(Objects.nonNull(commentEntity.getComment())){
            commentDto.setComment(commentEntity.getComment());
        }
        if(Objects.nonNull(commentEntity.getSpeedPoint())) {
            commentDto.setSpeedPoint(commentEntity.getSpeedPoint());
        }
        if(Objects.nonNull(commentEntity.getTastePoint())) {
            commentDto.setTastePoint(commentEntity.getTastePoint());
        }
        if(Objects.nonNull(commentEntity.getUserEntity())) {
            commentDto.setUserId(commentEntity.getUserEntity().getId());
        }
        if(Objects.nonNull(commentEntity.getBranchEntity())) {
            commentDto.setBranchId(commentEntity.getBranchEntity().getId());
        }

        return commentDto;
    }

}
