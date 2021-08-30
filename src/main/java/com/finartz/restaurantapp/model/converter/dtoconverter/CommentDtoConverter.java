package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
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
        if (Objects.isNull(commentEntity)){
            throw new EntityNotFoundException("Not found comment entity");
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId(commentEntity.getId());
        commentDto.setComment(commentEntity.getComment());
        commentDto.setSpeedPoint(commentEntity.getSpeedPoint());
        commentDto.setTastePoint(commentEntity.getTastePoint());

        if(Objects.nonNull(commentEntity.getUserEntity())) {
            commentDto.setUserId(commentEntity.getUserEntity().getId());
        }
        if(Objects.nonNull(commentEntity.getBranchEntity())) {
            commentDto.setBranchId(commentEntity.getBranchEntity().getId());
        }

        return commentDto;
    }

}
